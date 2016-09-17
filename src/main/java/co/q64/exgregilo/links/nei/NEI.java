package co.q64.exgregilo.links.nei;

import codechicken.nei.api.ItemInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.links.LinkBase;
import co.q64.exgregilo.api.links.ModLink;

@ModLink(modId = "NotEnoughItems", modName = "Not Enough Items")
public class NEI implements LinkBase {

	@Override
	public void loadLink() {
	}

	@Override
	public void postLoadLink() {
	}

	@Override
	public void afterPostLoadLink() {
	}

	public void addItemVariant(Item item, ItemStack variant) {
		ItemInfo.itemOverrides.put(item, variant);
	}
}
