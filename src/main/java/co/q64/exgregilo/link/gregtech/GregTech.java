package co.q64.exgregilo.link.gregtech;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.apache.commons.lang3.text.WordUtils;

import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.crafting.OreDictAddons;
import co.q64.exgregilo.link.gregtech.crafting.RecipeMap;
import co.q64.exgregilo.link.gregtech.item.ItemList;
import co.q64.exgregilo.link.gregtech.tile.AutoSieve;
import co.q64.exgregilo.link.gregtech.tools.MetaGeneratedTools;
import co.q64.exgregilo.types.GregiloBlocks;
import cpw.mods.fml.common.registry.GameRegistry;

@Singleton
@ModLink(modName = "GregTech", modId = ModData.GREGTECH_ID)
public class GregTech implements LinkBase {
	private @Inject LinkManager linkManager;
	private @Inject MetaGeneratedTools tools;

	private Map<Block, Map<ItemStack, Integer>> sifting = new HashMap<Block, Map<ItemStack, Integer>>();

	@Override
	public void loadLink() {
		tools.addCrafting();

		if (linkManager.isEnabled(ExNihilo.class)) {
			GT_OreDictUnificator.registerOre(OreDictAddons.SILK_MESH, new ItemStack(linkManager.getLink(ExNihilo.class).getSilkMesh(), 1));
		} else {
			GT_OreDictUnificator.registerOre(OreDictAddons.SILK_MESH, new ItemStack(Items.string, 1));
		}

		ItemList.AUTO_SIEVE_LV.set(new AutoSieve(11300, "basicmachine.autosieve.tier.01", "Auto Sieve", 1).getStackForm(1L));
		ItemList.AUTO_SIEVE_MV.set(new AutoSieve(11301, "basicmachine.autosieve.tier.02", "Advanced Auto Sieve", 2).getStackForm(1L));
		ItemList.AUTO_SIEVE_HV.set(new AutoSieve(11302, "basicmachine.autosieve.tier.03", "Advanced Auto Sieve II", 3).getStackForm(1L));
		ItemList.AUTO_SIEVE_EV.set(new AutoSieve(11303, "basicmachine.autosieve.tier.04", "Advanced Auto Sieve III", 4).getStackForm(1L));
		ItemList.AUTO_SIEVE_IV.set(new AutoSieve(11304, "basicmachine.autosieve.tier.05", "Advanced Auto Sieve IV", 5).getStackForm(1L));
		ItemList.AUTO_SIEVE_LuV.set(new AutoSieve(11305, "basicmachine.autosieve.tier.06", "Advanced Auto Sieve V", 6).getStackForm(1L));
		ItemList.AUTO_SIEVE_ZPM.set(new AutoSieve(11306, "basicmachine.autosieve.tier.07", "Advanced Auto Sieve VI", 7).getStackForm(1L));
		ItemList.AUTO_SIEVE_UV.set(new AutoSieve(11307, "basicmachine.autosieve.tier.08", "Advanced Auto Sieve VII", 8).getStackForm(1L));
	}

	@Override
	public void postLoadLink() {
		//addCrushed(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Naquadah, 1), 1);
		// Coal
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Lignite, 1), 10);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Coal, 1), 10);
		// Iron
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Magnetite, 1), 6);
		// Tin
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Cassiterite, 1), 33);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Tin, 1), 33);
		// Copper
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Chalcopyrite, 1), 10);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Tetrahedrite, 1), 20);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Copper, 1), 20);
		// Bauxite
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Bauxite, 1), 33);
		// Gold
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Gold, 1), 50);
		// Lead
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Lead, 1), 50);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Wulfenite, 1), 12);
		// Silver
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Galena, 1), 15);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Silver, 1), 50);

		// Salt
		addSand(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.RockSalt, 1), 12);
		addSand(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Salt, 1), 12);
		addSand(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Lepidolite, 1), 25);

		// Soapstone?
		addSand(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Soapstone, 1), 50);
		addSand(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Talc, 1), 50);
		addSand(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Glauconite, 1), 50);

		// Nickel
		addSand(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Garnierite, 1), 12);
		addSand(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Nickel, 1), 50);

		// Iridium/Osmium
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iridium, 1), 300);
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Osmiridium, 1), 400);
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Platinum, 1), 300);
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Chrome, 1), 300);
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Osmium, 1), 500);

		// Vanadium Magnetite
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.VanadiumMagnetite, 1), 100);

		// Stibnite
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Stibnite, 1), 100);

		// Ilmenite
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Ilmenite, 1), 100);

		// Spodumene
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Spodumene, 1), 100);

		// Cinnabar
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Cinnabar, 1), 100);

		// Lithium
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Lithium, 1), 100);

		List<Block> blocks = new ArrayList<Block>();
		blocks.add(Blocks.gravel);
		blocks.add(Blocks.sand);
		blocks.add(getDustBlock());
		for (Block b : blocks) {
			Map<ItemStack, Integer> additional = new HashMap<ItemStack, Integer>();
			if (linkManager.isEnabled(ExNihilo.class)) {
				additional.putAll(linkManager.getLink(ExNihilo.class).getAdditionalResults(b));
			}
			ItemStack[] outputs = new ItemStack[getSubMap(b).size() + additional.size()];
			int[] chances = new int[getSubMap(b).size() + additional.size()];
			int i = 0;
			for (Entry<ItemStack, Integer> e : getSubMap(b).entrySet()) {
				outputs[i] = e.getKey();
				//chances[i] = (int) Math.round(Math.ceil((1f / e.getValue()) * 100));
				chances[i] = e.getValue() * 4;
				i++;
			}
			for (Entry<ItemStack, Integer> e : additional.entrySet()) {
				outputs[i] = e.getKey();
				chances[i] = e.getValue() * 4;
				i++;
			}
			RecipeMap.AUTO_SIEVE_RECIPES.addRecipe(false, new ItemStack[] { (new ItemStack(b, 1)) }, outputs, null, chances, new FluidStack[0], new FluidStack[0], -1, -1, 0);
		}

		//formatter:off
		GameRegistry.addRecipe(new ItemStack(GregiloBlocks.ADVANCED_SIEVE.getRealBlock(), 1), new Object[]{
		"   ", 
		"RPR", 
		"R R", 
		Character.valueOf('P'), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1), 
		Character.valueOf('R'), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 1)});
		//formatter:on
	}

	@Override
	public void afterPostLoadLink() {

	}

	private Map<ItemStack, Integer> getSubMap(Block block) {
		Map<ItemStack, Integer> map = sifting.get(block);
		if (map == null) {
			map = new HashMap<ItemStack, Integer>();
			sifting.put(block, map);
		}
		return map;
	}

	private void addGravel(ItemStack is, int chance) {
		getSubMap(Blocks.gravel).put(is, chance);
	}

	private void addSand(ItemStack is, int chance) {
		getSubMap(Blocks.sand).put(is, chance);
	}

	private void addDust(ItemStack is, int chance) {
		if (linkManager.isEnabled(ExNihilo.class)) {
			getSubMap(linkManager.getLink(ExNihilo.class).getDustBlock()).put(is, chance);
		}
	}

	private Block getDustBlock() {
		if (linkManager.isEnabled(ExNihilo.class)) {
			return linkManager.getLink(ExNihilo.class).getDustBlock();
		}
		return Blocks.stonebrick;
	}

	public Map<Block, Map<ItemStack, Integer>> getSiftingMap() {
		return sifting;
	}

	public MetaGeneratedTools getTools() {
		return tools;
	}

	public void populateSplashList(List<String> list) {
		for (Entry<String, Materials> m : Materials.getMaterialsMap().entrySet()) {
			if (m.getValue().contains(SubTag.METAL)) {
				list.add("Tiny Piles of " + WordUtils.capitalizeFully(m.getKey()) + " Dust!");
			}
		}
	}

	public void removeRecipe(ItemStack output) {
		GT_ModHandler.removeRecipeByOutput(output);
	}
}
