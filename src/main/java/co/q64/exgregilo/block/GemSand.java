package co.q64.exgregilo.block;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import co.q64.exgregilo.api.binders.ModDataBinders.GemSandBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.GemSandTex;
import co.q64.exgregilo.api.binders.ModDataBinders.ModId;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Singleton
public class GemSand extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon blockIcon;

	@Inject
	public GemSand(@ModId String modId, @GemSandBlockName String blockName, @GemSandTex String texName) {
		super(Material.sand);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(2.0f);
		setBlockTextureName(modId + ":" + texName);
		setBlockName(modId + "." + blockName);
	}
}
