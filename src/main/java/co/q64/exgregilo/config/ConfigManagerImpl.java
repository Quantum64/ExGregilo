package co.q64.exgregilo.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import co.q64.exgregilo.api.ExGregiloAPI;
import co.q64.exgregilo.api.config.ConfigManager;
import co.q64.exgregilo.api.links.LinkBase;
import co.q64.exgregilo.api.links.ModLink;

public class ConfigManagerImpl implements ConfigManager {
	private Configuration config;
	private final String CORE_CAT = "core";

	public void setConfigFile(File file) {
		config = new Configuration(file);
	}

	@Override
	public int getInt(Class<? extends LinkBase> linkClass, String key, int def) {
		ModLink link = ExGregiloAPI.getLinkManager().getModLink(linkClass);
		if (link == null) {
			return def;
		}
		return config.get(link.modId(), key, def).getInt();
	}

	@Override
	public int getInt(String key, int def) {
		return config.get(CORE_CAT, key, def).getInt();
	}

	@Override
	public boolean getBoolean(Class<? extends LinkBase> linkClass, String key, boolean def) {
		ModLink link = ExGregiloAPI.getLinkManager().getModLink(linkClass);
		if (link == null) {
			return def;
		}
		return config.get(link.modId(), key, def).getBoolean();
	}

	@Override
	public boolean getBoolean(String key, boolean def) {
		return config.get(CORE_CAT, key, def).getBoolean();
	}
}
