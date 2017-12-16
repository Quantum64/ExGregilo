package co.q64.exgregilo.link.gregtech.tools;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.link.gregtech.render.ItemTextures;
import co.q64.exgregilo.link.nei.NEI;

@Singleton
public class WireMesh extends CustomMetaTool {
	public static final int MAX_SPEED = 14;

	private @Inject LinkManager linkManager;

	public void addCrafting() {
		boolean useNEI = linkManager.isEnabled(NEI.class);
		for (Materials material : Materials.values()) {
			if (material.contains(SubTag.METAL) && material.mDurability > 0) {
				ItemStack result = linkManager.getLink(GregTech.class).getTools().getMeshWithStats(MetaGeneratedTools.WIRE_MESH_ID, 1, material, material, null);
				//formatter:off
				boolean added = GT_ModHandler.addCraftingRecipe(result, GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{
				"WWW", 
				"WGW", 
				"WWW", 
				Character.valueOf('W'), OrePrefixes.wireGt01.get(material), 
				Character.valueOf('G'), OrePrefixes.gearGtSmall.get(material)});
				//formatter:on
				if (added) {
					GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, material, 8), GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, material, 1), result, 400, 2);
					if (useNEI) {
						linkManager.getLink(NEI.class).addItemVariant(linkManager.getLink(GregTech.class).getTools(), result);
					}
				}
			}
		}
	}

	@Override
	public float getBaseDamage() {
		return 1.0f;
	}

	@Override
	public boolean isMinableBlock(Block block, byte metaData) {
		return false;
	}

	@Override
	public IIconContainer getIcon(boolean isToolHead, ItemStack stack) {
		return ItemTextures.WIRE_MESH;
	}

	@Override
	public float getMaxDurabilityMultiplier() {
		return 0.01f;
	}

	@Override
	public float getSpeedMultiplier() {
		return 1f;
	}

	@Override
	public short[] getRGBa(boolean isToolHead, ItemStack stack) {
		return GT_MetaGenerated_Tool.getPrimaryMaterial(stack).mRGBa;
	}

	@Override
	public Class<? extends IIconContainer> getTextureClass() {
		return ItemTextures.class;
	}
}
