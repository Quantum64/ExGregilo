package co.q64.exgregilo.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class HeavySieveTile extends AbstractSieveTile {
	@Override
	public List<ItemStack> getRewards() {
		List<ItemStack> result = new ArrayList<ItemStack>();
		for (ItemStack is : getRegistry().getResult(getContent(), getLuck())) {
			result.add(new ItemStack(is.getItem(), 7, is.getItemDamage()));
		}
		return result;
	}
}
