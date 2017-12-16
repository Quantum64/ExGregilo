package co.q64.exgregilo;

import java.text.DecimalFormat;

import javax.inject.Inject;

import co.q64.com.google.inject.Guice;
import co.q64.com.google.inject.Injector;
import co.q64.exgregilo.api.binders.ConstantBinders.Author;
import co.q64.exgregilo.api.binders.ConstantBinders.Name;
import co.q64.exgregilo.api.binders.ConstantBinders.Version;
import co.q64.exgregilo.api.config.ConfigManager;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.util.Logger;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.data.ModIds;
import co.q64.exgregilo.link.excompressum.ExCompressum;
import co.q64.exgregilo.proxy.CommonProxy;
import co.q64.exgregilo.util.BlockRegistration;
import co.q64.exgregilo.util.GregiloLogger;
import co.q64.exgregilo.util.ItemRegistration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;

@Mod(modid = ModData.MODID, version = ModData.VERSION, dependencies = "after:" + ModIds.EX_NIHILO_ID /*+ "; after:" + ModData.GREGTECH_ID */+ "; before:" + ModIds.EX_COMPRESSUM_ID)
public class ExGregilo {
	@SidedProxy(clientSide = "co.q64.exgregilo.proxy.ClientProxy", serverSide = "co.q64.exgregilo.proxy.ServerProxy")
	public static CommonProxy staticAccessProxy;

	private @Inject LinkManager linkManager;
	private @Inject ConfigManager configManager;
	private @Inject BlockRegistration blockRegistration;
	private @Inject ItemRegistration itemRegistration;

	private @Inject DecimalFormat df;
	private @Inject Logger logger;
	private @Inject CommonProxy proxy;
	private @Inject @Name String name;
	private @Inject @Version String version;
	private @Inject @Author String author;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		event.getModLog().info(GregiloLogger.PREFIX + "Creating injector... this can take some time.");
		long now = System.currentTimeMillis();
		Injector injector = Guice.createInjector(new ExGregiloModule(this, event, staticAccessProxy));
		injector.injectMembers(this);
		injector.injectMembers(proxy);
		String time = df.format((System.currentTimeMillis() - now) / 1000f);
		logger.info("Done! Injector creation took " + time + "s. This is " + name + " version " + version + " by " + author + ".");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		linkManager.loadLinks();
		blockRegistration.registerBlocks();
		itemRegistration.registerItems();
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		linkManager.enableLinks();
	}

	@EventHandler
	public void preServerStart(FMLServerAboutToStartEvent event) {
		if (linkManager.isEnabled(ExCompressum.class)) {
			linkManager.getLink(ExCompressum.class).fixThisModsDumbRegistrationTime();
		}
	}

	public CommonProxy getProxy() {
		return proxy;
	}
}
