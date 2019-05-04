package co.q64.exgregilo.render;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.api.render.SieveRenderPackage;
import co.q64.exgregilo.block.AbstractSieve;
import co.q64.exgregilo.block.HeavySieve;
import co.q64.exgregilo.tile.AbstractSieveTile;
import co.q64.exgregilo.tile.HeavySieveTile;
@Singleton
public class HeavySieveRenderPackage implements SieveRenderPackage {
	private @Inject HeavySieve sieve;
	private @Inject HeavySieveRender render;
	private @Inject HeavySieveItemRender itemRender;

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
		return HeavySieveTile.class;
	}
}
