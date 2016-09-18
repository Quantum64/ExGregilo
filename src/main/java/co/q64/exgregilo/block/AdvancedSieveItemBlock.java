package co.q64.exgregilo.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class AdvancedSieveItemBlock extends ItemBlock {
	private static final String NAME = "exgregilo.advancedsieve";

	public AdvancedSieveItemBlock(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return NAME;
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}