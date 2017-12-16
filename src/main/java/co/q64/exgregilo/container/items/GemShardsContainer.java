package co.q64.exgregilo.container.items;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.minecraft.item.Item;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.container.GregiloItemContainer;
import co.q64.exgregilo.item.GemShards;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.GregTech;

@Singleton
public class GemShardsContainer extends GregiloItemContainer {
	private @Inject Provider<GemShards> provider;

	@Override
	public Provider<? extends Item> getItemProvider() {
		return provider;
	}

	@Override
	public String getName() {
		return "gem_shards";
	}

	@Override
	public List<Class<? extends LinkBase>> getRequired() {
		return require(GregTech.class).require(ExNihilo.class);
	}

}
