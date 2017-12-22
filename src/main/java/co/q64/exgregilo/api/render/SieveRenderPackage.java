package co.q64.exgregilo.api.render;

import co.q64.exgregilo.block.AbstractSieve;
import co.q64.exgregilo.render.AbstractSieveItemRender;
import co.q64.exgregilo.render.AbstractSieveRender;
import co.q64.exgregilo.tile.AbstractSieveTile;

public interface SieveRenderPackage {
	public Class<? extends AbstractSieveTile> getTileClass();
	
	public AbstractSieve getSieve();

	public AbstractSieveRender getRender();

	public AbstractSieveItemRender getItemRender();
}
