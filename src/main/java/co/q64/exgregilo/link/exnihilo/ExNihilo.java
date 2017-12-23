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
import co.q64.exgregilo.api.util.Logger;
import co.q64.exgregilo.data.ModIds;
import co.q64.exgregilo.link.exnihilo.block.GregCrucible;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.util.CraftingKiller;
import cpw.mods.fml.common.registry.GameRegistry;
import exnihilo.ENBlocks;
import exnihilo.ENItems;
import exnihilo.registries.SieveRegistry;
import exnihilo.registries.helpers.SiftingResult;

@Singleton
@ModLink(modName = "Ex Nihilo", modId = ModIds.EX_NIHILO_ID)
public class ExNihilo extends LinkBase {
	private @Inject CraftingKiller killer;
	private @Inject LinkManager linkManager;
	private @Inject ConfigManager configManager;
	private @Inject SieveRegistryCleaner cleaner;
	private @Inject Logger logger;
	private @Inject GregCrucible gregCrucible;

	@Override
	public void loadLink() {
		killer.remove(new ItemStack(ENBlocks.Crucible));

		if (configManager.getBoolean(ExNihilo.class, "removeDefaultSiftOres", true)) {
			cleaner.removeDefaultOres();
		}
		if (configManager.getBoolean(ExNihilo.class, "removeExNihiloHammers", true)) {
			killer.remove(new ItemStack(ENItems.HammerWood));
			killer.remove(new ItemStack(ENItems.HammerStone));
			killer.remove(new ItemStack(ENItems.HammerIron));
			killer.remove(new ItemStack(ENItems.HammerGold));
			killer.remove(new ItemStack(ENItems.HammerDiamond));
		}

		if (configManager.getBoolean(ExNihilo.class, "removeExNihiloSieves", true)) {
			killer.remove(new ItemStack(ENBlocks.Sieve, 1, 0));
			killer.remove(new ItemStack(ENBlocks.Sieve, 1, 1));
			killer.remove(new ItemStack(ENBlocks.Sieve, 1, 2));
			killer.remove(new ItemStack(ENBlocks.Sieve, 1, 3));
			killer.remove(new ItemStack(ENBlocks.Sieve, 1, 4));
			killer.remove(new ItemStack(ENBlocks.Sieve, 1, 5));
		}

		GameRegistry.addSmelting(ENBlocks.CrucibleUnfired, new ItemStack(gregCrucible), 0f);
	}

	@Override
	public void postloadLink() {
		if (linkManager.isEnabled(GregTech.class)) {
			GregTech gt = linkManager.getLink(GregTech.class);
			for (Entry<Block, Map<ItemStack, Integer>> block : gt.getSiftingMap().entrySet()) {
				for (Entry<ItemStack, Integer> e : block.getValue().entrySet()) {
					if (e.getKey() == null || e.getKey().getItem() == null) {
						logger.error("NULL sieve drop! " + e.getValue());
						continue;
					}
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
