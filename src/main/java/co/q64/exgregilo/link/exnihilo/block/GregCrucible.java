package co.q64.exgregilo.link.exnihilo.block;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import co.q64.exgregilo.api.binders.ModDataBinders.GregCrucibleBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.ModId;
import co.q64.exgregilo.link.exnihilo.tile.GregCrucibleTile;
import cpw.mods.fml.common.registry.GameRegistry;
import exnihilo.ENBlocks;
import exnihilo.blocks.tileentities.TileEntityCrucible;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@Singleton
public class GregCrucible extends BlockContainer {
	private @Inject Provider<GregCrucibleTile> tileProvider;

	@Inject
	public GregCrucible(@ModId String modId, @GregCrucibleBlockName String name) {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(2.0f);
		setBlockName(modId + "." + name);
		GameRegistry.registerTileEntity(GregCrucibleTile.class, this.getUnlocalizedName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = Blocks.stone.getIcon(0, 0);
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return tileProvider.get();
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntityCrucible te = (TileEntityCrucible) world.getTileEntity(x, y, z);
		return te.getLightLevel();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		return ENBlocks.Crucible.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
	}
}
