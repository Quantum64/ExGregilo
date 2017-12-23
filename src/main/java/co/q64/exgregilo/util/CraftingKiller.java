package co.q64.exgregilo.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.link.nei.NEI;

@Singleton
public class CraftingKiller {
	private @Inject LinkManager linkManager;

	public void remove(ItemStack output) {
		if (linkManager.isEnabled(GregTech.class)) {
			linkManager.getLink(GregTech.class).removeRecipe(output);
		}
		if (linkManager.isEnabled(NEI.class)) {
			linkManager.getLink(NEI.class).hideItem(output);
		}
	}
}
