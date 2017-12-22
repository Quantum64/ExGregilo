package co.q64.exgregilo.container.blocks;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.block.GemSand;
import co.q64.exgregilo.container.GregiloBlockContainer;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.GregTech;

@Singleton
public class GemSandContainer extends GregiloBlockContainer {
	private @Inject Provider<GemSand> provider;

	@Override
	public Provider<? extends Block> getBlockProvider() {
		return provider;
	}

	@Override
	public String getName() {
		return GemSand.BLOCK_NAME;
	}

	@Override
	public List<Class<? extends LinkBase>> getRequired() {
		return require(GregTech.class).require(ExNihilo.class);
	}
}
