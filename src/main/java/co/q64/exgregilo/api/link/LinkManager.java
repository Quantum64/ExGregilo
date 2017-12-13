package co.q64.exgregilo.api.link;

public interface LinkManager {
	public boolean isEnabled(Class<? extends LinkBase> linkClass);

	public <T> T getLink(Class<T> linkClass);

	public ModLink getModLink(Class<? extends LinkBase> linkClass);

	public void enableLinks();

	public void loadLinks();
}
