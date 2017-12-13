package co.q64.exgregilo.item;

import gregtech.api.enums.GT_Values;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.data.ModData;

public class GemShards extends Item {
	public GemShards() {
		this.setUnlocalizedName(ModData.MODID + ".gemshards");
		this.setTextureName(ModData.MODID + ":" + "GEM_SHARDS");

		GT_Values.RA.addCompressorRecipe(new ItemStack(this, 9), new ItemStack(Items.diamond), 400, 2);
	}
}
