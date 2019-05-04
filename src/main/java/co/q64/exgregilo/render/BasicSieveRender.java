package co.q64.exgregilo.render;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import co.q64.exgregilo.api.binders.ModDataBinders.BasicSieveTex;
import co.q64.exgregilo.api.binders.ModDataBinders.BlockTexPath;
import co.q64.exgregilo.block.BasicSieve;
import co.q64.exgregilo.data.ModData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Singleton
@SideOnly(Side.CLIENT)
public class BasicSieveRender extends AbstractSieveRender {
	private @Inject @BasicSieveTex String tex;
	private @Inject @BlockTexPath String blockTexPath;
	private @Inject BasicSieve sieve;

	private ResourceLocation texture;

	@Inject
	public void init() {
		texture = new ResourceLocation(ModData.MODID, blockTexPath + tex);
	}

	@Override
	public ResourceLocation getTexture() {
		return texture;
	}

	@Override
	public IIcon getMeshTexture() {
		return sieve.getMeshTexture();
	}
}
