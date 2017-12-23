package co.q64.exgregilo.link.exnihilo.container;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import co.q64.exgregilo.api.binders.ModDataBinders.GregCrucibleBlockName;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.container.GregiloBlockContainer;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.exnihilo.block.GregCrucible;

@Singleton
public class GregCrucibleContainer extends GregiloBlockContainer {
	private @Inject Provider<GregCrucible> provider;
	private @Inject @GregCrucibleBlockName String name;

	@Override
	public Provider<? extends Block> getBlockProvider() {
		return provider;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Class<? extends LinkBase>> getRequired() {
		return require(ExNihilo.class);
	}
}
