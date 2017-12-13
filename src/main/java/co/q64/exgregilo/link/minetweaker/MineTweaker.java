package co.q64.exgregilo.link.minetweaker;

import javax.inject.Inject;
import javax.inject.Singleton;

import minetweaker.MineTweakerAPI;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.link.minetweaker.machines.ZenAutoSieve;

@Singleton
@ModLink(modName = "MineTweaker", modId = ModData.MINE_TWEAKER_ID)
public class MineTweaker implements LinkBase {
	private @Inject LinkManager linkManager;

	@Override
	public void loadLink() {
		ZenAutoSieve.setLinkManager(linkManager);
	}

	@Override
	public void postLoadLink() {
		MineTweakerAPI.registerClass(ZenAutoSieve.class);
	}

	@Override
	public void afterPostLoadLink() {}
}
