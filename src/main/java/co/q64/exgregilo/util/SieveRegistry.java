package co.q64.exgregilo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

@Singleton
public class SieveRegistry {
	private Map<Block, Map<ItemStack, Integer>> sifting = new HashMap<Block, Map<ItemStack, Integer>>();
	private Random random = new Random();

	public boolean siftable(Block block) {
		return sifting.containsKey(block);
	}

	public Map<ItemStack, Integer> getSubMap(Block block) {
		Map<ItemStack, Integer> map = sifting.get(block);
		if (map == null) {
			map = new HashMap<ItemStack, Integer>();
			sifting.put(block, map);
		}
		return map;
	}

	public List<ItemStack> getResult(Block block, int luck) {
		Map<ItemStack, Integer> map = sifting.get(block);
		if (map == null) {
			return Collections.emptyList();
		}
		List<ItemStack> result = new ArrayList<ItemStack>();
		for (Entry<ItemStack, Integer> e : map.entrySet()) {
			if (random.nextInt(e.getValue()) <= luck) {
				result.add(e.getKey());
			}
		}
		return result;
	}

	public List<ItemStack> getResult(Block block) {
		return getResult(block, 0);
	}

	public Map<Block, Map<ItemStack, Integer>> getSiftingMap() {
		return sifting;
	}
}
