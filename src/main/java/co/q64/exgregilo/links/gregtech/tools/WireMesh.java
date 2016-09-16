package co.q64.exgregilo.links.gregtech.tools;

import co.q64.exgregilo.links.gregtech.render.ItemTextures;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.common.tools.GT_Tool;

public class WireMesh extends GT_Tool {

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
		return 0.1F;
	}

	@Override
	public short[] getRGBa(boolean isToolHead, ItemStack stack) {
		return isToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(stack).mRGBa : null;
	}
}
