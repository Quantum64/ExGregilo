package co.q64.exgregilo.render;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.api.render.SieveRenderPackage;
import co.q64.exgregilo.block.AbstractSieve;
import co.q64.exgregilo.block.BasicSieve;
import co.q64.exgregilo.tile.AbstractSieveTile;
import co.q64.exgregilo.tile.BasicSieveTile;
@Singleton

public class BasicSieveRenderPackage implements SieveRenderPackage {
	private @Inject BasicSieve sieve;
	private @Inject BasicSieveRender render;
	private @Inject BasicSieveItemRender itemRender;

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
		return BasicSieveTile.class;
	}
}
