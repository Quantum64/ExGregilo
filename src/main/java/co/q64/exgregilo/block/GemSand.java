package co.q64.exgregilo.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import co.q64.exgregilo.data.ModData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GemSand extends Block {
	public static final String BLOCK_NAME = "gem_sand";

	@SideOnly(Side.CLIENT)
	private IIcon blockIcon;

	public GemSand() {
		super(Material.sand);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(2.0f);
		setBlockTextureName(ModData.MODID + ":GEM_SAND");
		setBlockName(ModData.MODID + "." + BLOCK_NAME);
	}
}
