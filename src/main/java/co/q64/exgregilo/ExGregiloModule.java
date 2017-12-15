package co.q64.exgregilo;

import java.io.File;

import org.apache.logging.log4j.Logger;

import co.q64.com.google.inject.AbstractModule;
import co.q64.com.google.inject.TypeLiteral;
import co.q64.com.google.inject.multibindings.Multibinder;
import co.q64.exgregilo.api.binders.ConstantBinders.Author;
import co.q64.exgregilo.api.binders.ConstantBinders.ConfigFile;
import co.q64.exgregilo.api.binders.ConstantBinders.Name;
import co.q64.exgregilo.api.config.ConfigManager;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.binders.ConstantPool;
import co.q64.exgregilo.config.SimpleConfigManager;
import co.q64.exgregilo.link.SimpleLinkManager;
import co.q64.exgregilo.link.dreamcraft.DreamCraft;
import co.q64.exgregilo.link.exastris.ExAstris;
import co.q64.exgregilo.link.excompressum.ExCompressum;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.link.minetweaker.MineTweaker;
import co.q64.exgregilo.link.nei.NEI;
import co.q64.exgregilo.proxy.CommonProxy;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class ExGregiloModule extends AbstractModule {
	private ExGregilo mod;
	private FMLPreInitializationEvent event;
	private CommonProxy proxy;

	protected ExGregiloModule(ExGregilo mod, FMLPreInitializationEvent event, CommonProxy proxy) {
		this.mod = mod;
		this.event = event;
		this.proxy = proxy;
	}

	@Override
	protected void configure() {
		bind(LinkManager.class).to(SimpleLinkManager.class);
		bind(ConfigManager.class).to(SimpleConfigManager.class);

		Multibinder<Class<? extends LinkBase>> links = Multibinder.newSetBinder(binder(), new TypeLiteral<Class<? extends LinkBase>>() {});

		links.addBinding().toInstance(ExNihilo.class);
		links.addBinding().toInstance(GregTech.class);
		links.addBinding().toInstance(NEI.class);
		links.addBinding().toInstance(ExAstris.class);
		links.addBinding().toInstance(ExCompressum.class);
		links.addBinding().toInstance(MineTweaker.class);
		links.addBinding().toInstance(DreamCraft.class);

		bind(Logger.class).toInstance(event.getModLog());
		bind(Side.class).toInstance(event.getSide());
		bind(ModMetadata.class).toInstance(event.getModMetadata());
		bind(ModState.class).toInstance(event.getModState());
		bind(File.class).annotatedWith(ConfigFile.class).toInstance(event.getSuggestedConfigurationFile());
		bind(ExGregilo.class).toInstance(mod);
		bind(CommonProxy.class).toInstance(proxy);

		bindConstant().annotatedWith(Name.class).to(ConstantPool.NAME);
		bindConstant().annotatedWith(Author.class).to(ConstantPool.AUTHOR);
	}

}
