package co.q64.exgregilo.link.gregtech.tools;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.util.GT_ModHandler;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.link.gregtech.render.ItemTextures;
import co.q64.exgregilo.link.nei.NEI;

@Singleton
public class BasicMesh extends CustomMetaTool {
	public static final int MAX_SPEED = 14;

	private @Inject LinkManager linkManager;

	public void addCrafting() {
		boolean useNEI = linkManager.isEnabled(NEI.class);
		for (Materials material : Arrays.asList(Materials.Wood, Materials.Diamond, Materials.Iron, Materials.Gold)) {
			ItemStack result = linkManager.getLink(GregTech.class).getTools().getToolWithStats(MetaGeneratedTools.BASIC_MESH_ID, 1, material, material, null);
			//formatter:off
				boolean added = GT_ModHandler.addCraftingRecipe(result, GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{
				"III",
				"IDI", 
				"III", 
				Character.valueOf('I'), material == Materials.Wood ? OrePrefixes.plank.get(material) : OrePrefixes.gem.get(material),
				Character.valueOf('D'), material == Materials.Wood ? OrePrefixes.stick.get(material) : OrePrefixes.dust.get(material)});
				//formatter:on
			if (added) {
				if (useNEI) {
					linkManager.getLink(NEI.class).addItemVariant(linkManager.getLink(GregTech.class).getTools(), result);
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
		return ItemTextures.BASIC_MESH;
	}

	@Override
	public float getMaxDurabilityMultiplier() {
		return 0.1f;
	}

	@Override
	public float getSpeedMultiplier() {
		return 0.5f;
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
