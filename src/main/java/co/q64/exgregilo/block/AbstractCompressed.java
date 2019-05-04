package co.q64.exgregilo.block;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import co.q64.exgregilo.api.binders.ModDataBinders.CompressedBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.CompressedTex;
import co.q64.exgregilo.api.binders.ModDataBinders.DomainPath;
import co.q64.exgregilo.api.binders.ModDataBinders.ModId;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Singleton
public abstract class AbstractCompressed extends Block {
	private @Inject @ModId String modId;
	private @Inject @CompressedBlockName String blockName;
	private @Inject @DomainPath String domain;
	private @Inject @CompressedTex String tex;

	private IIcon[] icons = new IIcon[8];
	private String typeName;

	public AbstractCompressed(Material base, String typeName) {
		super(Material.sand);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(2.0f);
		this.typeName = typeName;
	}

	@Inject
	public void init() {
		setBlockName(modId + "." + blockName + typeName);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 8; i++) {
			this.icons[i] = reg.registerIcon(domain + tex + "_" + typeName.toLowerCase() + "_" + (i + 1) + "x");
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.icons[meta > 7 ? 0 : meta];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 8; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	public String getTypeName() {
		return typeName;
	}
}
