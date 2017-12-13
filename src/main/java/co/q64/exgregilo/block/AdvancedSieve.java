package co.q64.exgregilo.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.link.gregtech.tools.MetaGeneratedTools;
import co.q64.exgregilo.tile.AdvancedSieveTile;
import co.q64.exgregilo.tile.AdvancedSieveTile.SieveMode;
import cpw.mods.fml.common.registry.GameRegistry;
import exnihilo.registries.SieveRegistry;

public class AdvancedSieve extends BlockContainer {
	public static final String BLOCK_NAME = "advanced_sieve";

	private static final String MESH_TEXTURE = "SIEVE_MESH";
	public static IIcon meshIcon;

	public AdvancedSieve() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(2.0f);
		setBlockName(ModData.MODID + "." + BLOCK_NAME);
		GameRegistry.registerTileEntity(AdvancedSieveTile.class, ModData.MODID + "." + BLOCK_NAME);
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = Blocks.planks.getIcon(0, 0);
		meshIcon = register.registerIcon(ModData.DOMAIN_PATH + MESH_TEXTURE);
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new AdvancedSieveTile();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player == null) {
			return false;
		}
		AdvancedSieveTile sieve = (AdvancedSieveTile) world.getTileEntity(x, y, z);
		if (sieve.mode == SieveMode.EMPTY && player.getCurrentEquippedItem() != null) {
			ItemStack held = player.getCurrentEquippedItem();
			if (held.getItem() instanceof MetaGeneratedTools) {
				if (sieve.getMesh() != null) {
					sieve.dropMesh();
				}
				sieve.setMesh(held.copy());
				removeCurrentItem(player);
				return true;
			}
			if (sieve.getMesh() != null) {
				if (SieveRegistry.registered(Block.getBlockFromItem(held.getItem()), held.getItemDamage())) {
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
				sieve.processContents(false);
			} else {
				if (sieve.mode != SieveMode.EMPTY) {
					if (isHuman(player)) {
						sieve.processContents(false);
					}
				}
			}
		}
		return true;
	}

	private boolean isHuman(EntityPlayer player) {
		boolean isHuman = (player instanceof EntityPlayerMP);
		if (player.toString().contains("CoFH")) {
			isHuman = false;
		}
		return isHuman;
	}

	private void removeCurrentItem(EntityPlayer player) {
		ItemStack item = player.getCurrentEquippedItem();
		if (!player.capabilities.isCreativeMode) {
			item.stackSize -= 1;
			if (item.stackSize == 0) {
				item = null;
			}
		}

	}
}
