package co.q64.exgregilo.util;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.container.GregiloItemContainer;
import cpw.mods.fml.common.registry.GameRegistry;

@Singleton
public class ItemRegistration {
	private @Inject LinkManager linkManager;
	private @Inject Set<GregiloItemContainer> items;

	public void registerItems() {
		for (GregiloItemContainer i : items) {
			for (Class<? extends LinkBase> link : i.getRequired()) {
				if (!linkManager.isEnabled(link)) {
					continue;
				}
			}
			i.setItem(i.getItemProvider().get());
			i.setRegistered(true);
			GameRegistry.registerItem(i.getItem(), i.getName());
		}
	}
}
