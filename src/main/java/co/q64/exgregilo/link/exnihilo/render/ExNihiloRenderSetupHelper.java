package co.q64.exgregilo.link.exnihilo.render;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import co.q64.exgregilo.link.exnihilo.block.GregCrucible;
import co.q64.exgregilo.link.exnihilo.tile.GregCrucibleTile;
import cpw.mods.fml.client.registry.ClientRegistry;
import exnihilo.blocks.models.ModelCrucible;
import exnihilo.blocks.renderers.RenderCrucible;
import exnihilo.blocks.renderers.blockItems.ItemRenderCrucible;

@Singleton
public class ExNihiloRenderSetupHelper {
	private @Inject GregCrucible block;

	public void setup() {
		ModelCrucible mc = new ModelCrucible();
		ClientRegistry.bindTileEntitySpecialRenderer(GregCrucibleTile.class, new RenderCrucible(mc));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), new ItemRenderCrucible(mc));
	}
}
