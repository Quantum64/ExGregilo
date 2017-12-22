package co.q64.exgregilo.container.blocks;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import co.q64.exgregilo.api.binders.ModDataBinders.DustBlockName;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.block.Dust;
import co.q64.exgregilo.container.GregiloBlockContainer;

@Singleton
public class DustContainer extends GregiloBlockContainer {
	private @Inject Provider<Dust> provider;
	private @Inject @DustBlockName String name;

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
		return requireNone();
	}
}
