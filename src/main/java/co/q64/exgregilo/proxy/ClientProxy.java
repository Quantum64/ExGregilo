package co.q64.exgregilo.proxy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import scala.util.Random;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.container.blocks.AdvancedSieveContainer;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.exnihilo.render.ExNihiloRenderSetupHelper;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.render.SieveRenderSetupHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Singleton
public class ClientProxy extends CommonProxy {
	private static final int SPLASH_ID = 3;

	private @Inject LinkManager linkManager;
	private @Inject AdvancedSieveContainer asc;
	private @Inject Provider<SieveRenderSetupHelper> ascRenderSetupHelper;
	private @Inject Provider<ExNihiloRenderSetupHelper> enRenderSetupHelper;

	private List<String> splashes = new ArrayList<String>();
	private Random r = new Random();

	@Override
	public void init() {
		MinecraftForge.EVENT_BUS.register(this);
		if (linkManager.isEnabled(GregTech.class)) {
			linkManager.getLink(GregTech.class).populateSplashList(splashes);
		}
		if (asc.isRegistered()) {
			ascRenderSetupHelper.get().setup();
		}
		if (linkManager.isEnabled(ExNihilo.class)) {
			enRenderSetupHelper.get().setup();
		}
	}

	@SubscribeEvent
	public void onGuiOpenEvent(GuiOpenEvent event) {
		if (splashes.size() <= 0) {
			return;
		}
		if (event.gui instanceof GuiMainMenu) {
			GuiMainMenu gui = (GuiMainMenu) event.gui;
			try {
				Field splash = GuiMainMenu.class.getDeclaredFields()[SPLASH_ID];
				splash.setAccessible(true);
				splash.set(gui, splashes.get(r.nextInt(splashes.size())));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}
}
