package co.q64.exgregilo.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.types.GregiloBlocks;
import cpw.mods.fml.common.registry.GameRegistry;

@Singleton
public class BlockRegistration {
	private @Inject LinkManager linkManager;

	public void registerBlocks() {
		for (GregiloBlocks b : GregiloBlocks.class.getEnumConstants()) {
			for (Class<? extends LinkBase> link : b.getRequired()) {
				if (!linkManager.isEnabled(link)) {
					continue;
				}
			}
			b.setRealBlock(b.getBlock().newBlock());
			b.setRegistered(true);
			if (b.getItemBlock() == null) {
				GameRegistry.registerBlock(b.getRealBlock(), b.getName());
			} else {
				GameRegistry.registerBlock(b.getRealBlock(), b.getItemBlock(), b.getName());
			}
		}
	}
}
