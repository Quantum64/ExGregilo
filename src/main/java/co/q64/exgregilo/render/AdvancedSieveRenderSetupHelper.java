package co.q64.exgregilo.render;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import co.q64.exgregilo.block.AdvancedSieve;
import co.q64.exgregilo.tile.AdvancedSieveTile;
import cpw.mods.fml.client.registry.ClientRegistry;

@Singleton
public class AdvancedSieveRenderSetupHelper {
	private @Inject AdvancedSieveRender render;
	private @Inject AdvancedSieveItemRender itemRender;
	private @Inject AdvancedSieve sieve;
	
	public void setup() {
		ClientRegistry.bindTileEntitySpecialRenderer(AdvancedSieveTile.class, render);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(sieve), itemRender);
	}
}
