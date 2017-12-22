package co.q64.exgregilo;

import java.io.File;
import java.text.DecimalFormat;

import co.q64.com.google.inject.TypeLiteral;
import co.q64.com.google.inject.multibindings.Multibinder;
import co.q64.exgregilo.api.ExGregiloModule;
import co.q64.exgregilo.api.binders.ConstantBinders.Author;
import co.q64.exgregilo.api.binders.ConstantBinders.ConfigFile;
import co.q64.exgregilo.api.binders.ConstantBinders.Name;
import co.q64.exgregilo.api.binders.ConstantBinders.Version;
import co.q64.exgregilo.api.binders.ModDataBinders.AdvancedSieveBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.AdvancedSieveMesh;
import co.q64.exgregilo.api.binders.ModDataBinders.AdvancedSieveTex;
import co.q64.exgregilo.api.binders.ModDataBinders.BasicSieveBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.BasicSieveMesh;
import co.q64.exgregilo.api.binders.ModDataBinders.BasicSieveTex;
import co.q64.exgregilo.api.binders.ModDataBinders.BlockTexPath;
import co.q64.exgregilo.api.binders.ModDataBinders.DomainPath;
import co.q64.exgregilo.api.binders.ModDataBinders.GemSandBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.GemSandTex;
import co.q64.exgregilo.api.binders.ModDataBinders.GtGUITexPath;
import co.q64.exgregilo.api.binders.ModDataBinders.ModId;
import co.q64.exgregilo.api.config.ConfigManager;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.render.SieveRenderPackage;
import co.q64.exgregilo.api.util.Logger;
import co.q64.exgregilo.binders.ConstantPool;
import co.q64.exgregilo.binders.ModDataPool;
import co.q64.exgregilo.config.SimpleConfigManager;
import co.q64.exgregilo.container.GregiloBlockContainer;
import co.q64.exgregilo.container.GregiloItemContainer;
import co.q64.exgregilo.container.blocks.AdvancedSieveContainer;
import co.q64.exgregilo.container.blocks.BasicSieveContainer;
import co.q64.exgregilo.container.blocks.GemSandContainer;
import co.q64.exgregilo.container.items.GemShardsContainer;
import co.q64.exgregilo.link.SimpleLinkManager;
import co.q64.exgregilo.link.dreamcraft.DreamCraft;
import co.q64.exgregilo.link.exastris.ExAstris;
import co.q64.exgregilo.link.excompressum.ExCompressum;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.link.minetweaker.MineTweaker;
import co.q64.exgregilo.link.nei.NEI;
import co.q64.exgregilo.proxy.CommonProxy;
import co.q64.exgregilo.render.AdvancedSieveRenderPackage;
import co.q64.exgregilo.render.BasicSieveRenderPackage;
import co.q64.exgregilo.util.GregiloLogger;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class DefaultGregiloModule extends ExGregiloModule {
	private ExGregilo mod;
	private FMLPreInitializationEvent event;
	private CommonProxy proxy;

	protected DefaultGregiloModule(ExGregilo mod, FMLPreInitializationEvent event, CommonProxy proxy) {
		this.mod = mod;
		this.event = event;
		this.proxy = proxy;
	}

	@Override
	protected void configure() {
		bind(LinkManager.class).to(SimpleLinkManager.class);
		bind(ConfigManager.class).to(SimpleConfigManager.class);
		bind(Logger.class).to(GregiloLogger.class);

		Multibinder<Class<? extends LinkBase>> links = Multibinder.newSetBinder(binder(), new TypeLiteral<Class<? extends LinkBase>>() {});
		links.addBinding().toInstance(ExNihilo.class);
		links.addBinding().toInstance(GregTech.class);
		links.addBinding().toInstance(NEI.class);
		links.addBinding().toInstance(ExAstris.class);
		links.addBinding().toInstance(ExCompressum.class);
		links.addBinding().toInstance(MineTweaker.class);
		links.addBinding().toInstance(DreamCraft.class);

		Multibinder<GregiloItemContainer> items = Multibinder.newSetBinder(binder(), GregiloItemContainer.class);
		items.addBinding().to(GemShardsContainer.class);

		Multibinder<GregiloBlockContainer> blocks = Multibinder.newSetBinder(binder(), GregiloBlockContainer.class);
		blocks.addBinding().to(AdvancedSieveContainer.class);
		blocks.addBinding().to(GemSandContainer.class);
		blocks.addBinding().to(BasicSieveContainer.class);

		Multibinder<SieveRenderPackage> sieveRenderPacks = Multibinder.newSetBinder(binder(), SieveRenderPackage.class);
		sieveRenderPacks.addBinding().to(BasicSieveRenderPackage.class);
		sieveRenderPacks.addBinding().to(AdvancedSieveRenderPackage.class);

		bind(FMLPreInitializationEvent.class).toInstance(event);
		bind(Side.class).toInstance(event.getSide());
		bind(ModMetadata.class).toInstance(event.getModMetadata());
		bind(ModState.class).toInstance(event.getModState());
		bind(File.class).annotatedWith(ConfigFile.class).toInstance(event.getSuggestedConfigurationFile());
		bind(ExGregilo.class).toInstance(mod);
		bind(CommonProxy.class).toInstance(proxy);
		bind(DecimalFormat.class).toInstance(new DecimalFormat("0.00"));

		bindConstant().annotatedWith(Name.class).to(ConstantPool.NAME);
		bindConstant().annotatedWith(Author.class).to(ConstantPool.AUTHOR);
		bindConstant().annotatedWith(Version.class).to(ConstantPool.VERSION);

		bindConstant().annotatedWith(ModId.class).to(ModDataPool.MODID);
		bindConstant().annotatedWith(GtGUITexPath.class).to(ModDataPool.GT_GUI_TEX_PATH);
		bindConstant().annotatedWith(DomainPath.class).to(ModDataPool.DOMAIN_PATH);
		bindConstant().annotatedWith(BlockTexPath.class).to(ModDataPool.BLOCK_TEX_PATH);

		bindConstant().annotatedWith(AdvancedSieveBlockName.class).to(ModDataPool.ADVANCED_SIEVE_BLOCK_NAME);
		bindConstant().annotatedWith(AdvancedSieveMesh.class).to(ModDataPool.ADVANCED_SIEVE_MESH);
		bindConstant().annotatedWith(AdvancedSieveTex.class).to(ModDataPool.ADVANCED_SIEVE_TEX);

		bindConstant().annotatedWith(BasicSieveBlockName.class).to(ModDataPool.BASIC_SIEVE_BLOCK_NAME);
		bindConstant().annotatedWith(BasicSieveMesh.class).to(ModDataPool.BASIC_SIEVE_MESH);
		bindConstant().annotatedWith(BasicSieveTex.class).to(ModDataPool.BASIC_SIEVE_TEX);

		bindConstant().annotatedWith(GemSandBlockName.class).to(ModDataPool.GEM_SAND_BLOCK_NAME);
		bindConstant().annotatedWith(GemSandTex.class).to(ModDataPool.GEM_SAND_TEX);
	}
}
