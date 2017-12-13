package co.q64.exgregilo.config;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraftforge.common.config.Configuration;
import co.q64.exgregilo.api.binders.ConstantBinders.ConfigFile;
import co.q64.exgregilo.api.config.ConfigManager;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.link.ModLink;

@Singleton
public class SimpleConfigManager implements ConfigManager {
	private @Inject LinkManager linkManager;
	private @Inject @ConfigFile File file;

	private Configuration config;
	private final String CORE_CAT = "core";

	@Inject
	public void init() {
		config = new Configuration(file);
	}

	@Override
	public int getInt(Class<? extends LinkBase> linkClass, String key, int def) {
		ModLink link = linkManager.getModLink(linkClass);
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
		ModLink link = linkManager.getModLink(linkClass);
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
