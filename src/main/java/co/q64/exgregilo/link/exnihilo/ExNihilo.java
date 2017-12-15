package co.q64.exgregilo.link.exnihilo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.config.ConfigManager;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.link.gregtech.GregTech;
import exnihilo.ENBlocks;
import exnihilo.ENItems;
import exnihilo.registries.SieveRegistry;
import exnihilo.registries.helpers.SiftingResult;

@Singleton
@ModLink(modName = "Ex Nihilo", modId = ModData.EX_NIHILO_ID)
public class ExNihilo extends LinkBase {
	private @Inject LinkManager linkManager;
	private @Inject ConfigManager configManager;
	private @Inject SieveRegistryCleaner cleaner;

	@Override
	public void preloadLink() {
		if (configManager.getBoolean(ExNihilo.class, "removeDefaultSiftOres", true)) {
			cleaner.removeDefaultOres();
		}
	}

	@Override
	public void postloadLink() {
		if (linkManager.isEnabled(GregTech.class)) {
			GregTech gt = linkManager.getLink(GregTech.class);
			for (Entry<Block, Map<ItemStack, Integer>> block : gt.getSiftingMap().entrySet()) {
				for (Entry<ItemStack, Integer> e : block.getValue().entrySet()) {
					SieveRegistry.register(block.getKey(), e.getKey().getItem(), e.getKey().getItemDamage(), e.getValue());
				}
			}
		}
	}

	public Block getDustBlock() {
		return ENBlocks.Dust;
	}

	public Item getSilkMesh() {
		return ENItems.Mesh;
	}

	public Map<ItemStack, Integer> getAdditionalResults(Block b) {
		Map<ItemStack, Integer> result = new HashMap<ItemStack, Integer>();
		for (SiftingResult s : SieveRegistry.getSiftingOutput(b, 0)) {
			result.put(new ItemStack(s.item, 1, s.meta), s.rarity);
		}
		return result;
	}
}
