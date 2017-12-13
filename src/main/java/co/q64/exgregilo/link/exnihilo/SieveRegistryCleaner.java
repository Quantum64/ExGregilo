package co.q64.exgregilo.link.exnihilo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Logger;

import exnihilo.items.ores.ItemOre;
import exnihilo.registries.SieveRegistry;
import exnihilo.registries.helpers.SiftingResult;
import exnihilo.utils.ItemInfo;

@Singleton
public class SieveRegistryCleaner {
	private @Inject Logger logger;

	public void removeDefaultOres() {
		Map<ItemInfo, ArrayList<SiftingResult>> rewards = SieveRegistry.getSiftables();
		List<Pair<Item, Integer>> toRemove = new ArrayList<Pair<Item, Integer>>();
		int foundForRemove = 0;
		for (Entry<ItemInfo, ArrayList<SiftingResult>> e : rewards.entrySet()) {
			for (SiftingResult result : e.getValue()) {
				if (result.item instanceof ItemOre || result.item == Items.emerald || result.item == Items.diamond) {
					toRemove.add(new ImmutablePair<Item, Integer>(result.item, result.meta));
					foundForRemove++;
				}
			}
		}
		logger.info("Found " + foundForRemove + " ore results to remove from sieve!");
		/* This causes a CMOD because of an extremely stupid mistake in the Ex Nihilo sieve registry, I can't even find where to report the issue...
		for (Pair<Item, Integer> e : toRemove) {
			SieveRegistry.unregisterRewardFromAllBlocks(e.getKey(), e.getValue());
		}
		So we need to use this insane hack to get it to work as seen below
		*/
		List<Pair<Item, Integer>> possibleBlocks = new ArrayList<Pair<Item, Integer>>();
		for (ItemInfo in : rewards.keySet()) {
			possibleBlocks.add(new ImmutablePair<Item, Integer>(in.getItem(), in.getMeta()));
		}
		for (Pair<Item, Integer> block : possibleBlocks) {
			for (Pair<Item, Integer> item : toRemove) {
				SieveRegistry.unregisterReward(Block.getBlockFromItem(block.getLeft()), block.getRight(), item.getLeft(), item.getRight());
			}
		}
	}
}
