package co.q64.exgregilo.api.links;

public interface LinkBase {
	public void preLoadLink();

	public void postLoadLink();

	public void afterPostLoadLink();
}
