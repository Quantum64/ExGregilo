package co.q64.exgregilo.api.config;

import co.q64.exgregilo.api.link.LinkBase;

public interface ConfigManager {
	public int getInt(Class<? extends LinkBase> linkClass, String key, int def);

	public int getInt(String key, int def);

	public boolean getBoolean(Class<? extends LinkBase> linkClass, String key, boolean def);

	public boolean getBoolean(String key, boolean def);
}
