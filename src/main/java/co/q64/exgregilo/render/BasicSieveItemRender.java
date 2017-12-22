package co.q64.exgregilo.render;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

@Singleton
public class BasicSieveItemRender extends AbstractSieveItemRender {
	private @Inject BasicSieveRender bsr;

	@Override
	public ResourceLocation getTexture() {
		return bsr.getTexture();
	}

	@Override
	public IIcon getMeshTexture() {
		return bsr.getMeshTexture();
	}
}
