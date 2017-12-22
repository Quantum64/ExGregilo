package co.q64.exgregilo.block;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import co.q64.exgregilo.api.binders.ModDataBinders.DustBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.DustTex;
import co.q64.exgregilo.api.binders.ModDataBinders.ModId;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Singleton
public class Dust extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon blockIcon;

	@Inject
	public Dust(@ModId String modId, @DustBlockName String blockName, @DustTex String texName) {
		super(Material.sand);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(2.0f);
		setBlockTextureName(modId + ":" + texName);
		setBlockName(modId + "." + blockName);
	}
}
