package co.q64.exgregilo.link.gregtech.tools;

import gregtech.api.enums.Materials;
import gregtech.api.enums.SubTag;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.util.GT_ModHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.link.gregtech.render.ItemTextures;
import co.q64.exgregilo.link.nei.NEI;

@Singleton
public class HeavyMesh extends CustomMetaTool {
	public static final int MAX_SPEED = 14;

	private @Inject LinkManager linkManager;

	public void addCrafting() {
		boolean useNEI = linkManager.isEnabled(NEI.class);
		for (Materials material : Materials.values()) {
			if (material.contains(SubTag.METAL) && material.mDurability > 0) {
				ItemStack result = linkManager.getLink(GregTech.class).getTools().getToolWithStats(MetaGeneratedTools.HEAVY_MESH_ID, 1, material, material, null);
				//formatter:off
				boolean added = GT_ModHandler.addCraftingRecipe(result, GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{
				"WWW", 
				"WWW", 
				"WWW", 
				Character.valueOf('W'), linkManager.getLink(GregTech.class).getTools().getToolWithStats(MetaGeneratedTools.WIRE_MESH_ID, 1, material, material, null)});
				//formatter:on
				if (added) {
					if (useNEI) {
						linkManager.getLink(NEI.class).addItemVariant(linkManager.getLink(GregTech.class).getTools(), result);
					}
				}
			}
		}
	}

	@Override
	public float getBaseDamage() {
		return 10.0f;
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
		return 0.1f;
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
