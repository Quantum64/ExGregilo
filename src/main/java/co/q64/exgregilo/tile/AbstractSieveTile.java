package co.q64.exgregilo.tile;

import gregtech.api.interfaces.IToolStats;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import co.q64.exgregilo.link.gregtech.tools.AdvancedMesh;
import co.q64.exgregilo.link.gregtech.tools.MetaGeneratedTools;
import co.q64.exgregilo.render.SieveParticle;
import co.q64.exgregilo.util.SieveRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class AbstractSieveTile extends TileEntity {
	private static final float MIN_RENDER_CAPACITY = 0.70f;
	private static final float MAX_RENDER_CAPACITY = 0.9f;
	private static final float PROCESSING_INTERVAL = 0.075f;
	private static final int UPDATE_INTERVAL = 20;

	private SieveRegistry registry;
	public Block content;
	public int contentMeta = 0;
	private ItemStack mesh;
	private float volume = 0;
	public SieveMode mode = SieveMode.EMPTY;
	private int timer = 0;
	private boolean update = false;
	private boolean particleMode = false;
	private int timesClicked = 0;

	public enum SieveMode {
		EMPTY(0), FILLED(1);
		private SieveMode(int v) {
			this.value = v;
		}

		public int value;
	}

	public AbstractSieveTile() {
		mode = SieveMode.EMPTY;
	}

	public void addSievable(Block block, int blockMeta) {
		this.content = block;
		this.contentMeta = blockMeta;
		this.mode = SieveMode.FILLED;
		volume = 1.0f;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public void setMesh(ItemStack mesh) {
		this.mesh = mesh;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public void dropMesh() {
		if (mesh == null) {
			return;
		}
		if (!worldObj.isRemote) {
			EntityItem entityitem = new EntityItem(worldObj, (double) xCoord + 0.5D, (double) yCoord + 1.5D, (double) zCoord + 0.5D, mesh);
			double f3 = 0.05F;
			entityitem.motionX = worldObj.rand.nextGaussian() * f3;
			entityitem.motionY = (0.2d);
			entityitem.motionZ = worldObj.rand.nextGaussian() * f3;
			worldObj.spawnEntityInWorld(entityitem);
		}
		mesh = null;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote && particleMode) {
			spawnFX(content, contentMeta);
		}

		timer++;
		if (timer >= UPDATE_INTERVAL) {
			timesClicked = 0;

			timer = 0;
			disableParticles();

			if (update) {
				update();
			}
		}
	}

	public void processContents(SieveRegistry registry, boolean creative) {
		this.registry = registry;
		if (creative) {
			volume = 0;
		} else {
			timesClicked++;
			if (timesClicked <= 6) {
				if (mesh != null && mesh.getItem() instanceof MetaGeneratedTools) {
					MetaGeneratedTools tools = (MetaGeneratedTools) mesh.getItem();
					IToolStats stats = tools.getToolStats(mesh);
					float amount = 1f / (stats.getSpeedMultiplier() * (AdvancedMesh.MAX_SPEED - MetaGeneratedTools.getPrimaryMaterial(mesh).mToolSpeed));
					if (amount < 0) {
						volume = 0;
					} else {
						volume -= amount;
					}
				} else {
					volume -= PROCESSING_INTERVAL;
				}
			}
		}

		if (volume <= 0) {
			mode = SieveMode.EMPTY;
			//give rewards!
			if (!worldObj.isRemote) {
				if (mesh != null) {
					if (mesh.getItem() instanceof MetaGeneratedTools) {
						MetaGeneratedTools tool = (MetaGeneratedTools) mesh.getItem();
						tool.doDamage(mesh, 10);
						if (MetaGeneratedTools.getToolMaxDamage(mesh) - MetaGeneratedTools.getToolDamage(mesh) <= 0) {
							mesh = null;
							update();
						}
					}
				}
				List<ItemStack> rewards = getRewards();
				for (ItemStack reward : rewards) {
					EntityItem entityitem = new EntityItem(worldObj, (double) xCoord + 0.5D, (double) yCoord + 1.5D, (double) zCoord + 0.5D, reward);
					double f3 = 0.05F;
					entityitem.motionX = worldObj.rand.nextGaussian() * f3;
					entityitem.motionY = (0.2d);
					entityitem.motionZ = worldObj.rand.nextGaussian() * f3;
					worldObj.spawnEntityInWorld(entityitem);
				}
			}
		} else {
			particleMode = true;
		}

		update = true;
	}

	@SideOnly(Side.CLIENT)
	private void spawnFX(Block block, int blockMeta) {
		if (block != null) {
			IIcon icon = block.getIcon(0, blockMeta);

			for (int x = 0; x < 4; x++) {
				SieveParticle dust = new SieveParticle(worldObj, xCoord + 0.8d * worldObj.rand.nextFloat() + 0.15d, yCoord + 0.69d, zCoord + 0.8d * worldObj.rand.nextFloat() + 0.15d, 0.0d, 0.0d, 0.0d, icon);
				Minecraft.getMinecraft().effectRenderer.addEffect(dust);
			}
		}
	}

	public float getVolume() {
		return volume;
	}

	public float getAdjustedVolume() {
		float capacity = MAX_RENDER_CAPACITY - MIN_RENDER_CAPACITY;
		float adjusted = volume * capacity;
		adjusted += MIN_RENDER_CAPACITY;
		return adjusted;
	}

	private void update() {
		update = false;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	private void disableParticles() {
		particleMode = false;
	}

	public ItemStack getMesh() {
		return mesh;
	}

	public Block getContent() {
		return content;
	}

	public int getContentMeta() {
		return contentMeta;
	}

	public SieveRegistry getRegistry() {
		return registry;
	}

	public List<ItemStack> getRewards() {
		return registry.getResult(content, getLuck());
	}

	public int getLuck() {
		if (getMesh() == null || getMesh().getEnchantmentTagList() == null) {
			return 0;
		}
		for (int i = 0; i < getMesh().getEnchantmentTagList().tagCount(); i++) {
			NBTTagCompound tag = getMesh().getEnchantmentTagList().getCompoundTagAt(i);
			if (tag.getShort("id") == Enchantment.fortune.effectId) {
				return tag.getShort("lvl");
			}
		}
		return 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		switch (compound.getInteger("mode")) {
		case 0:
			mode = SieveMode.EMPTY;
			break;

		case 1:
			mode = SieveMode.FILLED;
			break;
		}
		if (!compound.getString("content").equals("")) {
			content = (Block) Block.blockRegistry.getObject(compound.getString("content"));
		} else {
			content = null;
		}
		contentMeta = compound.getInteger("contentMeta");
		volume = compound.getFloat("volume");
		particleMode = compound.getBoolean("particles");
		mesh = null;
		if (compound.hasKey("mesh")) {
			mesh = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("mesh"));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("mode", mode.value);
		//Should change later to not be dependent on DV, as Forge can now change them willy-nilly at startup
		if (content == null) {
			compound.setString("content", "");
		} else {
			compound.setString("content", Block.blockRegistry.getNameForObject(content));
		}
		compound.setInteger("contentMeta", contentMeta);
		compound.setFloat("volume", volume);
		compound.setBoolean("particles", particleMode);
		if (mesh != null) {
			NBTTagCompound meshTag = new NBTTagCompound();
			mesh.writeToNBT(meshTag);
			compound.setTag("mesh", meshTag);
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.func_148857_g();
		this.readFromNBT(tag);
	}
}
