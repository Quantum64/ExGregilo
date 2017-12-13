package co.q64.exgregilo.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.types.GregiloBlocks;
import co.q64.exgregilo.types.GregiloItems;
import cpw.mods.fml.common.registry.GameRegistry;

@Singleton
public class ItemRegistration {
	private @Inject LinkManager linkManager;

	public void registerItems() {
		for (GregiloItems i : GregiloItems.class.getEnumConstants()) {
			for (Class<? extends LinkBase> link : i.getRequired()) {
				if (!linkManager.isEnabled(link)) {
					continue;
				}
			}
			i.setRealItem(i.getItem().newItem());
			i.setRegistered(true);
			GameRegistry.registerItem(i.getRealItem(), i.getName());
		}
	}
}
