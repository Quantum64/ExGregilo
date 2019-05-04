package co.q64.exgregilo.block;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.link.gregtech.tools.MetaGeneratedTools;
import co.q64.exgregilo.tile.AbstractSieveTile;
import co.q64.exgregilo.tile.AbstractSieveTile.SieveMode;
import co.q64.exgregilo.util.SieveRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@Singleton
public abstract class AbstractSieve extends BlockContainer {
	private @Inject GregTech gt;
	private @Inject SieveRegistry registry;

	public AbstractSieve() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(2.0f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = Blocks.planks.getIcon(0, 0);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player == null) {
			return false;
		}
		AbstractSieveTile sieve = (AbstractSieveTile) world.getTileEntity(x, y, z);
		if (sieve.mode == SieveMode.EMPTY && player.getCurrentEquippedItem() != null) {
			ItemStack held = player.getCurrentEquippedItem();
			if (held.getItem() instanceof MetaGeneratedTools) {
				if (!held.getUnlocalizedName().equals(gt.getTools().getUnlocalizedName() + "." + getMeshId())) {
					return true;
				}
				if (sieve.getMesh() != null) {
					sieve.dropMesh();
				}
				sieve.setMesh(held.copy());
				removeCurrentItem(player);
				return true;
			}
			if (sieve.getMesh() != null) {
				if (registry.siftable(Block.getBlockFromItem(held.getItem()))) {
					sieve.addSievable(Block.getBlockFromItem(held.getItem()), held.getItemDamage());
					removeCurrentItem(player);
				}
			}
		} else {
			if (sieve.mode == SieveMode.EMPTY && player.getCurrentEquippedItem() == null && sieve.getMesh() != null) {
				sieve.dropMesh();
				return true;
			}
			if (world.isRemote) {
				sieve.processContents(registry, false);
			} else {
				if (sieve.mode != SieveMode.EMPTY) {
					if (isHuman(player)) {
						sieve.processContents(registry, false);
					}
				}
			}
		}
		return true;
	}

	protected boolean isHuman(EntityPlayer player) {
		boolean isHuman = (player instanceof EntityPlayerMP);
		if (player.toString().contains("CoFH")) {
			isHuman = false;
		}
		return isHuman;
	}

	protected void removeCurrentItem(EntityPlayer player) {
		ItemStack item = player.getCurrentEquippedItem();
		if (!player.capabilities.isCreativeMode) {
			item.stackSize -= 1;
			if (item.stackSize == 0) {
				item = null;
			}
		}

	}

	public abstract int getMeshId();

	public abstract IIcon getMeshTexture();
}
