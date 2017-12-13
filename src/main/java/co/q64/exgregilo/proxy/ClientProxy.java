package co.q64.exgregilo.proxy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import scala.util.Random;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.render.AdvancedSieveItemRender;
import co.q64.exgregilo.render.AdvancedSieveMeshModel;
import co.q64.exgregilo.render.AdvancedSieveModel;
import co.q64.exgregilo.render.AdvancedSieveRender;
import co.q64.exgregilo.tile.AdvancedSieveTile;
import co.q64.exgregilo.types.GregiloBlocks;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Singleton
public class ClientProxy extends CommonProxy {
	private @Inject LinkManager linkManager;

	private static final int SPLASH_ID = 3;
	private List<String> splashes = new ArrayList<String>();
	private Random r = new Random();

	@Override
	public void init() {
		MinecraftForge.EVENT_BUS.register(this);
		if (GregiloBlocks.ADVANCED_SIEVE.isRegistered()) {
			AdvancedSieveModel sieve = new AdvancedSieveModel();
			AdvancedSieveMeshModel mesh = new AdvancedSieveMeshModel();
			ClientRegistry.bindTileEntitySpecialRenderer(AdvancedSieveTile.class, new AdvancedSieveRender(sieve, mesh));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GregiloBlocks.ADVANCED_SIEVE.getRealBlock()), new AdvancedSieveItemRender(sieve, mesh));
		}
		if (linkManager.isEnabled(GregTech.class)) {
			linkManager.getLink(GregTech.class).populateSplashList(splashes);
		}
		/*	
		try {
			ResourceLocation newSplashes = new ResourceLocation(ModData.MODID, "text/blank.txt");
			Field splashes = GuiMainMenu.class.getDeclaredFields()[15];
			splashes.setAccessible(true);
			splashes.set(null, newSplashes);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		*/
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
