package co.q64.exgregilo.render;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.api.render.SieveRenderPackage;
import co.q64.exgregilo.block.AbstractSieve;
import co.q64.exgregilo.block.AdvancedSieve;
import co.q64.exgregilo.tile.AbstractSieveTile;
import co.q64.exgregilo.tile.AdvancedSieveTile;

@Singleton
public class AdvancedSieveRenderPackage implements SieveRenderPackage {
	private @Inject AdvancedSieve sieve;
	private @Inject AdvancedSieveRender render;
	private @Inject AdvancedSieveItemRender itemRender;

	@Override
	public AbstractSieve getSieve() {
		return sieve;
	}

	@Override
	public AbstractSieveRender getRender() {
		return render;
	}

	@Override
	public AbstractSieveItemRender getItemRender() {
		return itemRender;
	}

	@Override
	public Class<? extends AbstractSieveTile> getTileClass() {
		return AdvancedSieveTile.class;
	}
}
