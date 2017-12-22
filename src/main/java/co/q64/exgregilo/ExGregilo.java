package co.q64.exgregilo;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import co.q64.com.google.inject.AbstractModule;
import co.q64.com.google.inject.Guice;
import co.q64.com.google.inject.Injector;
import co.q64.com.google.inject.Module;
import co.q64.com.google.inject.util.Modules;
import co.q64.exgregilo.api.ExGregiloModule;
import co.q64.exgregilo.api.binders.ConstantBinders.Author;
import co.q64.exgregilo.api.binders.ConstantBinders.Name;
import co.q64.exgregilo.api.binders.ConstantBinders.Version;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.loader.InitLoader;
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
	private List<ExGregiloModule> modules = new CopyOnWriteArrayList<ExGregiloModule>();

	private @Inject LinkManager linkManager;
	private @Inject BlockRegistration blockRegistration;
	private @Inject ItemRegistration itemRegistration;

	private @Inject DecimalFormat df;
	private @Inject Logger logger;
	private @Inject CommonProxy proxy;
	private @Inject Injector injector;
	private @Inject @Name String name;
	private @Inject @Version String version;
	private @Inject @Author String author;

	private @Inject Set<InitLoader> loaders;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		event.getModLog().info(GregiloLogger.PREFIX + "Creating injector... this can take some time.");
		long now = System.currentTimeMillis();
		Injector injector = Guice.createInjector(getModule(event));
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
		for (InitLoader loader : loaders) {
			loader.load();
		}
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

	public void install(ExGregiloModule module) {
		if (modules.contains(module) || module == null) {
			return;
		}
		modules.add(module);
	}

	public void inject(Object object) {
		if (object == null) {
			return;
		}
		injector.injectMembers(object);
	}

	private Module getModule(FMLPreInitializationEvent event) {
		Module current = new DefaultGregiloModule(this, event, staticAccessProxy);
		for (AbstractModule module : modules) {
			current = Modules.override(current).with(module);
		}
		return current;
	}
}
