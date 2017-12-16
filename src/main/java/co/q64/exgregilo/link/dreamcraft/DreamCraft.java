package co.q64.exgregilo.link.dreamcraft;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.data.ModIds;
import co.q64.exgregilo.link.gregtech.GregTech;

@Singleton
@ModLink(modName = "DreamCraft NH Core", modId = ModIds.DREAMCRAFT_ID)
public class DreamCraft extends LinkBase {
	private static final int NH_OFFSET = 10000;

	private @Inject LinkManager linkManager;

	@Override
	public void initLink() {
		if (linkManager.isEnabled(GregTech.class)) {
			GregTech gt = linkManager.getLink(GregTech.class);
			gt.setIdStart(gt.getIdStart() + NH_OFFSET);
		}
	}
}
