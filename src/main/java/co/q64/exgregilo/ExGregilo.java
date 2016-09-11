package co.q64.exgregilo;

import co.q64.exgregilo.proxy.CommonProxy;
import co.q64.exgregilo.util.GTSieveRegistration;
import co.q64.exgregilo.util.SieveRegistryCleaner;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExGregilo.MODID, version = ExGregilo.VERSION)
public class ExGregilo {
	public static final String MODID = "exgregilo";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide = "co.q64.exgregilo.proxy.ClientProxy", serverSide = "co.q64.exgregilo.proxy.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.setLogger(event.getModLog());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void init(FMLPostInitializationEvent event) {
		SieveRegistryCleaner.removeDefaultOres();
		GTSieveRegistration.addGTOres();
	}

	public static CommonProxy getProxy() {
		return proxy;
	}
}
