package co.q64.exgregilo;

import co.q64.exgregilo.api.ExGregiloAPI;
import co.q64.exgregilo.config.ConfigManagerImpl;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.links.LinkManagerImpl;
import co.q64.exgregilo.links.exnihilo.ExNihilo;
import co.q64.exgregilo.links.gregtech.GregTech;
import co.q64.exgregilo.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModData.MODID, version = ModData.VERSION)
public class ExGregilo {
	@SidedProxy(clientSide = "co.q64.exgregilo.proxy.ClientProxy", serverSide = "co.q64.exgregilo.proxy.ServerProxy")
	public static CommonProxy proxy;

	private static LinkManagerImpl linkManager;
	private static ConfigManagerImpl configManager;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.setLogger(event.getModLog());
		ExGregiloAPI.setMod(this);

		linkManager = new LinkManagerImpl();
		configManager = new ConfigManagerImpl();

		getConfigManager().setConfigFile(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		linkManager.registerLink(new ExNihilo());
		linkManager.registerLink(new GregTech());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		getLinkManager().enableLinks();
	}

	public CommonProxy getProxy() {
		return proxy;
	}

	public LinkManagerImpl getLinkManager() {
		return linkManager;
	}

	public ConfigManagerImpl getConfigManager() {
		return configManager;
	}
}
