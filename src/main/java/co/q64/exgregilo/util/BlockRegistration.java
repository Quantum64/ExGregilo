package co.q64.exgregilo.util;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.container.GregiloBlockContainer;
import cpw.mods.fml.common.registry.GameRegistry;

@Singleton
public class BlockRegistration {
	private @Inject LinkManager linkManager;
	private @Inject Set<GregiloBlockContainer> blocks;

	public void registerBlocks() {
		for (GregiloBlockContainer b : blocks) {
			for (Class<? extends LinkBase> link : b.getRequired()) {
				if (!linkManager.isEnabled(link)) {
					continue;
				}
			}
			b.setBlock(b.getBlockProvider().get());
			b.setRegistered(true);
			if (b.getItemBlock() == null) {
				GameRegistry.registerBlock(b.getBlock(), b.getName());
			} else {
				GameRegistry.registerBlock(b.getBlock(), b.getItemBlock(), b.getName());
			}
			b.afterRegister();
		}
	}
}
