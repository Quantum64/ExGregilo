package co.q64.exgregilo.links.exnihilo;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.ExGregiloAPI;
import co.q64.exgregilo.api.links.LinkBase;
import co.q64.exgregilo.api.links.ModLink;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.links.gregtech.GregTech;
import exnihilo.ENBlocks;
import exnihilo.ENItems;
import exnihilo.registries.SieveRegistry;

@ModLink(modName = "Ex Nihilo", modId = ModData.EX_NIHILO_ID)
public class ExNihilo implements LinkBase {

	@Override
	public void postLoadLink() {
		if (ExGregiloAPI.getConfigManager().getBoolean(ExNihilo.class, "removeDefaultSiftOres", true)) {
			SieveRegistryCleaner.removeDefaultOres();
		}
	}

	@Override
	public void afterPostLoadLink() {
		if (ExGregiloAPI.getLinkManager().isEnabled(GregTech.class)) {
			GregTech gt = ExGregiloAPI.getLinkManager().getLink(GregTech.class);
			for (Entry<Block, Map<ItemStack, Integer>> block : gt.getSiftingMap().entrySet()) {
				for (Entry<ItemStack, Integer> e : block.getValue().entrySet()) {
					SieveRegistry.register(block.getKey(), e.getKey().getItem(), e.getKey().getItemDamage(), e.getValue());
				}
			}
		}
	}

	@Override
	public void preLoadLink() {

	}

	public Block getDustBlock() {
		return ENBlocks.Dust;
	}

	public Item getSilkMesh() {
		return ENItems.Mesh;
	}
}
