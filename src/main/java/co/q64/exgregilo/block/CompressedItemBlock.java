package co.q64.exgregilo.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class CompressedItemBlock extends ItemBlockWithMetadata {

	public CompressedItemBlock(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + (stack.getItemDamage() + 1) + "x";
	}
}
