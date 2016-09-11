package co.q64.exgregilo.api;

import co.q64.exgregilo.ExGregilo;
import co.q64.exgregilo.api.config.ConfigManager;
import co.q64.exgregilo.api.links.LinkManager;
import co.q64.exgregilo.api.proxy.GeneralProxy;

public class ExGregiloAPI {
	private static ExGregilo mod;
	private static GeneralProxy proxy;
	private static LinkManager linkManager;
	private static ConfigManager configManager;

	public static void setMod(ExGregilo mod) {
		if (getMod() != null) {
			getProxy().getLogger().error("The mod instance cannot be set more than once");
			return;
		}
		ExGregiloAPI.mod = mod;
	}

	private static ExGregilo getMod() {
		return mod;
	}

	public static GeneralProxy getProxy() {
		if (proxy == null) {
			return proxy = getMod().getProxy();
		}
		return proxy;
	}

	public static LinkManager getLinkManager() {
		if (linkManager == null) {
			linkManager = getMod().getLinkManager();
		}
		return linkManager;
	}

	public static ConfigManager getConfigManager() {
		if (configManager == null) {
			configManager = getMod().getConfigManager();
		}
		return configManager;
	}
}
