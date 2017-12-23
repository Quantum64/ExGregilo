package co.q64.exgregilo.render;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

@Singleton
public class HeavySieveItemRender extends AbstractSieveItemRender {
	private @Inject HeavySieveRender asr;

	@Override
	public ResourceLocation getTexture() {
		return asr.getTexture();
	}

	@Override
	public IIcon getMeshTexture() {
		return asr.getMeshTexture();
	}
}
