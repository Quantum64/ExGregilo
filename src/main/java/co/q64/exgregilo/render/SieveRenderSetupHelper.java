package co.q64.exgregilo.render;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import co.q64.exgregilo.api.render.SieveRenderPackage;
import cpw.mods.fml.client.registry.ClientRegistry;

@Singleton
public class SieveRenderSetupHelper {
	private @Inject Set<SieveRenderPackage> packages;

	public void setup() {
		for (SieveRenderPackage pack : packages) {
			ClientRegistry.bindTileEntitySpecialRenderer(pack.getTileClass(), pack.getRender());
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(pack.getSieve()), pack.getItemRender());
		}
	}
}
