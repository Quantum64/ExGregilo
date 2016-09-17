package co.q64.exgregilo.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import co.q64.exgregilo.render.AdvancedSieveItemRender;
import co.q64.exgregilo.render.AdvancedSieveMeshModel;
import co.q64.exgregilo.render.AdvancedSieveModel;
import co.q64.exgregilo.render.AdvancedSieveRender;
import co.q64.exgregilo.tile.AdvancedSieveTile;
import co.q64.exgregilo.types.GregiloBlocks;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		if (GregiloBlocks.ADVANCED_SIEVE.isRegistered()) {
			AdvancedSieveModel sieve = new AdvancedSieveModel();
			AdvancedSieveMeshModel mesh = new AdvancedSieveMeshModel();
			ClientRegistry.bindTileEntitySpecialRenderer(AdvancedSieveTile.class, new AdvancedSieveRender(sieve, mesh));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GregiloBlocks.ADVANCED_SIEVE.getRealBlock()), new AdvancedSieveItemRender(sieve, mesh));
		}
	}
}
