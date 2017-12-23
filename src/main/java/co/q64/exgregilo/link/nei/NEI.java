package co.q64.exgregilo.link.nei;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.ModLink;
import codechicken.nei.api.API;
import codechicken.nei.api.ItemInfo;

@ModLink(modId = "NotEnoughItems", modName = "Not Enough Items")
public class NEI extends LinkBase {
	public void addItemVariant(Item item, ItemStack variant) {
		ItemInfo.itemOverrides.put(item, variant);
	}

	public void hideItem(ItemStack item) {
		API.hideItem(item);
	}
}
