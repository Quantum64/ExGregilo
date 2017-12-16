package co.q64.exgregilo.link.gregtech.gui;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.api.binders.ModDataBinders.GtGUITexPath;

@Singleton
public class NEITextureHelper {
	private @Inject @GtGUITexPath String texPath;

	public String getNEITexture(String texture) {
		return texPath + texture;
	}
}
