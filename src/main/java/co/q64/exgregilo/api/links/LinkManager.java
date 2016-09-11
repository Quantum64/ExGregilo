package co.q64.exgregilo.api.links;

public interface LinkManager {
	public boolean isEnabled(Class<? extends LinkBase> linkClass);

	public <T> T getLink(Class<T> linkClass);

	public void registerLink(LinkBase base);

	public ModLink getModLink(Class<? extends LinkBase> linkClass);
}
