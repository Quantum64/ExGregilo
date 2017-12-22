package co.q64.exgregilo.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BasicSieveItemBlock extends ItemBlock {
	private static final String NAME = "exgregilo.basicsieve";

	public BasicSieveItemBlock(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return NAME;
	}
}