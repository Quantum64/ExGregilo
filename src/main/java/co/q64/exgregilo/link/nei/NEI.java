package co.q64.exgregilo.link.nei;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.ModLink;
import codechicken.nei.api.ItemInfo;

@ModLink(modId = "NotEnoughItems", modName = "Not Enough Items")
public class NEI implements LinkBase {

	@Override
	public void loadLink() {}

	@Override
	public void postLoadLink() {}

	@Override
	public void afterPostLoadLink() {}

	public void addItemVariant(Item item, ItemStack variant) {
		ItemInfo.itemOverrides.put(item, variant);
	}
}
