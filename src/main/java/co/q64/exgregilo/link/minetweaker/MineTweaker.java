package co.q64.exgregilo.link.minetweaker;

import javax.inject.Inject;
import javax.inject.Singleton;

import minetweaker.MineTweakerAPI;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.data.ModIds;
import co.q64.exgregilo.link.minetweaker.machines.ZenAutoSieve;

@Singleton
@ModLink(modName = "MineTweaker", modId = ModIds.MINE_TWEAKER_ID)
public class MineTweaker extends LinkBase {
	private @Inject LinkManager linkManager;

	@Override
	public void initLink() {
		ZenAutoSieve.setLinkManager(linkManager);
	}

	@Override
	public void loadLink() {
		MineTweakerAPI.registerClass(ZenAutoSieve.class);
	}
}
