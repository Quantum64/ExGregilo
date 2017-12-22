package co.q64.exgregilo.link.gregtech;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;

import java.util.ArrayList;
import java.util.Arrays;
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
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.lang3.text.WordUtils;

import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.block.AdvancedSieve;
import co.q64.exgregilo.block.GemSand;
import co.q64.exgregilo.data.ModIds;
import co.q64.exgregilo.item.GemShards;
import co.q64.exgregilo.link.excompressum.ExCompressum;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.crafting.MachineRecipeHelper;
import co.q64.exgregilo.link.gregtech.crafting.OreDictAddons;
import co.q64.exgregilo.link.gregtech.item.ItemList;
import co.q64.exgregilo.link.gregtech.recipe.AutoSieveRecipes;
import co.q64.exgregilo.link.gregtech.recipe.CompressedHammerRecipes;
import co.q64.exgregilo.link.gregtech.recipe.GemExtractorRecipes;
import co.q64.exgregilo.link.gregtech.recipe.IndustrialForgeHammerRecipes;
import co.q64.exgregilo.link.gregtech.tile.AutoSieve;
import co.q64.exgregilo.link.gregtech.tile.GemExtractor;
import co.q64.exgregilo.link.gregtech.tile.IndustrialForgeHammer;
import co.q64.exgregilo.link.gregtech.tools.MetaGeneratedTools;
import cpw.mods.fml.common.registry.GameRegistry;

@Singleton
@ModLink(modName = "GregTech", modId = ModIds.GREGTECH_ID)
public class GregTech extends LinkBase {
	private static final int CHANCE_CONSTANT = 4;
	private static final int DEFAULT_ID_START = 11264;
	private static final int DEFAULT_ID_OFFSET = 36;

	private @Inject LinkManager linkManager;
	private @Inject MetaGeneratedTools tools;
	private @Inject MachineRecipeHelper helper;

	private @Inject AutoSieveRecipes asr;
	private @Inject GemExtractorRecipes ger;
	private @Inject CompressedHammerRecipes chr;
	private @Inject IndustrialForgeHammerRecipes ihr;

	private @Inject GemShards gemShards;
	private @Inject GemSand gemSand;
	private @Inject AdvancedSieve advancedSieve;

	private Map<Block, Map<ItemStack, Integer>> sifting = new HashMap<Block, Map<ItemStack, Integer>>();
	private Map<ItemStack, Integer> gems = new HashMap<ItemStack, Integer>();
	private int idStart = DEFAULT_ID_START + DEFAULT_ID_OFFSET;

	@Override
	public void preloadLink() {
		tools.addCrafting();

		if (linkManager.isEnabled(ExNihilo.class)) {
			GT_OreDictUnificator.registerOre(OreDictAddons.SILK_MESH, new ItemStack(linkManager.getLink(ExNihilo.class).getSilkMesh(), 1));
		} else {
			GT_OreDictUnificator.registerOre(OreDictAddons.SILK_MESH, new ItemStack(Items.string, 1));
		}

		ItemList.AUTO_SIEVE_LV.set(new AutoSieve(id(0), "basicmachine.autosieve.tier.01", "Auto Sieve", 1, helper, asr).getStackForm(1L));
		ItemList.AUTO_SIEVE_MV.set(new AutoSieve(id(1), "basicmachine.autosieve.tier.02", "Advanced Auto Sieve", 2, helper, asr).getStackForm(1L));
		ItemList.AUTO_SIEVE_HV.set(new AutoSieve(id(2), "basicmachine.autosieve.tier.03", "Advanced Auto Sieve II", 3, helper, asr).getStackForm(1L));
		ItemList.AUTO_SIEVE_EV.set(new AutoSieve(id(3), "basicmachine.autosieve.tier.04", "Advanced Auto Sieve III", 4, helper, asr).getStackForm(1L));
		ItemList.AUTO_SIEVE_IV.set(new AutoSieve(id(4), "basicmachine.autosieve.tier.05", "Advanced Auto Sieve IV", 5, helper, asr).getStackForm(1L));
		ItemList.AUTO_SIEVE_LuV.set(new AutoSieve(id(5), "basicmachine.autosieve.tier.06", "Advanced Auto Sieve V", 6, helper, asr).getStackForm(1L));
		ItemList.AUTO_SIEVE_ZPM.set(new AutoSieve(id(6), "basicmachine.autosieve.tier.07", "Advanced Auto Sieve VI", 7, helper, asr).getStackForm(1L));
		ItemList.AUTO_SIEVE_UV.set(new AutoSieve(id(7), "basicmachine.autosieve.tier.08", "Advanced Auto Sieve VII", 8, helper, asr).getStackForm(1L));

		ItemList.GEM_EXTRACTOR_LV.set(new GemExtractor(id(10), "basicmachine.gemextractor.tier.01", "Gem Extractor", 1, helper, ger).getStackForm(1L));
		ItemList.GEM_EXTRACTOR_MV.set(new GemExtractor(id(11), "basicmachine.gemextractor.tier.02", "Advanced Gem Extractor", 2, helper, ger).getStackForm(1L));
		ItemList.GEM_EXTRACTOR_HV.set(new GemExtractor(id(12), "basicmachine.gemextractor.tier.03", "Advanced Gem Extractor II", 3, helper, ger).getStackForm(1L));
		ItemList.GEM_EXTRACTOR_EV.set(new GemExtractor(id(13), "basicmachine.gemextractor.tier.04", "Advanced Gem Extractor III", 4, helper, ger).getStackForm(1L));
		ItemList.GEM_EXTRACTOR_IV.set(new GemExtractor(id(14), "basicmachine.gemextractor.tier.05", "Advanced Gem Extractor IV", 5, helper, ger).getStackForm(1L));
		ItemList.GEM_EXTRACTOR_LuV.set(new GemExtractor(id(15), "basicmachine.gemextractor.tier.06", "Advanced Gem Extractor V", 6, helper, ger).getStackForm(1L));
		ItemList.GEM_EXTRACTOR_ZPM.set(new GemExtractor(id(16), "basicmachine.gemextractor.tier.07", "Advanced Gem Extractor VI", 7, helper, ger).getStackForm(1L));
		ItemList.GEM_EXTRACTOR_UV.set(new GemExtractor(id(17), "basicmachine.gemextractor.tier.08", "Advanced Gem Extractor VII", 8, helper, ger).getStackForm(1L));

		ItemList.INDUSTRIAL_HAMMER_LV.set(new IndustrialForgeHammer(id(20), "basicmachine.industrialhammer.tier.01", "Industrial Forge Hammer", 1, helper, ihr).getStackForm(1L));
		ItemList.INDUSTRIAL_HAMMER_MV.set(new IndustrialForgeHammer(id(21), "basicmachine.industrialhammer.tier.02", "Advanced Industrial Forge Hammer", 2, helper, ihr).getStackForm(1L));
		ItemList.INDUSTRIAL_HAMMER_HV.set(new IndustrialForgeHammer(id(22), "basicmachine.industrialhammer.tier.03", "Advanced Industrial Forge Hammer II", 3, helper, ihr).getStackForm(1L));
		ItemList.INDUSTRIAL_HAMMER_EV.set(new IndustrialForgeHammer(id(23), "basicmachine.industrialhammer.tier.04", "Advanced Industrial Forge Hammer III", 4, helper, ihr).getStackForm(1L));
		ItemList.INDUSTRIAL_HAMMER_IV.set(new IndustrialForgeHammer(id(24), "basicmachine.industrialhammer.tier.05", "Advanced Industrial Forge Hammer IV", 5, helper, ihr).getStackForm(1L));
		ItemList.INDUSTRIAL_HAMMER_LuV.set(new IndustrialForgeHammer(id(25), "basicmachine.industrialhammer.tier.06", "Advanced Industrial Forge Hammer V", 6, helper, ihr).getStackForm(1L));
		ItemList.INDUSTRIAL_HAMMER_ZPM.set(new IndustrialForgeHammer(id(26), "basicmachine.industrialhammer.tier.07", "Advanced Industrial Forge Hammer VI", 7, helper, ihr).getStackForm(1L));
		ItemList.INDUSTRIAL_HAMMER_UV.set(new IndustrialForgeHammer(id(27), "basicmachine.industrialhammer.tier.08", "Advanced Industrial Forge Hammer VII", 8, helper, ihr).getStackForm(1L));
	}

	@Override
	public void loadLink() {
		
		//Gravel Drops
    
		// Gem Shards
		addGravel(new ItemStack(gemShards), 50);
		//Naquadah
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Naquadah, 1), 100);
		// Coal
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Lignite, 1), 10);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Coal, 1), 15);
		// Iron - Changed to Iron from Magnetite
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Iron, 1), 6);
		// Tin
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Cassiterite, 1), 33);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Tin, 1), 9);
		// Copper
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Chalcopyrite, 1), 10);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Tetrahedrite, 1), 17);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Copper, 1), 6);
		// Bauxite
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Bauxite, 1), 33);
		// Gold
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Gold, 1), 25);
		// Lead
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Lead, 1), 20);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Wulfenite, 1), 50);
		// Silver
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Galena, 1), 23);
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Silver, 1), 20);
		// Lapis
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Lapis, 1), 15);
		//Sodalite
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Sodalite, 1), 22);
		//Redstone
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Redstone, 1), 12);
		//Tungsten
		addGravel(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Tungsten, 1), 50);

		// Gems

		addGravel(new ItemStack(GregiloItems.GEM_SHARDS.getRealItem()), 50);
		
		//Sand Drops
    
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
		//Gypsum
		addSand(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Gypsum, 1), 17);
		//Calcite
		addSand(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Calcite, 1), 17);

		//Dust Drops
		
		// Iridium/Osmium
		addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iridium, 1), 300);
		//This is an alloy
		//addDust(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Osmiridium, 1), 400);
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

		//Various gems (someone should probably modify these chances)
		//Changed the chances a little
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Diamond, 1), 50);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Emerald, 1), 40);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Ruby, 1), 21);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Sapphire, 1), 13);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.GreenSapphire, 1), 13);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Olivine, 1), 19);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Topaz, 1), 25);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Tanzanite, 1), 25);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Amethyst, 1), 25);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Opal, 1), 25);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.BlueTopaz, 1), 13);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.GarnetRed, 1), 13);
		addGem(GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.GarnetYellow, 1), 13);

		// Autosieve recipies
		List<Block> blocks = new ArrayList<Block>();
		blocks.add(Blocks.gravel);
		blocks.add(Blocks.sand);
		blocks.add(getDustBlock());
		for (Block b : blocks) {
			Map<ItemStack, Integer> additional = new HashMap<ItemStack, Integer>();
			//if (linkManager.isEnabled(ExNihilo.class)) {
			//	additional.putAll(linkManager.getLink(ExNihilo.class).getAdditionalResults(b));
			//}
			ItemStack[] outputs = new ItemStack[getSubMap(b).size() + additional.size()];
			int[] chances = new int[getSubMap(b).size() + additional.size()];
			int i = 0;
			for (Entry<ItemStack, Integer> e : getSubMap(b).entrySet()) {
				outputs[i] = e.getKey();
				chances[i] = e.getValue() * CHANCE_CONSTANT;
				i++;
			}
			//for (Entry<ItemStack, Integer> e : additional.entrySet()) {
			//	outputs[i] = e.getKey();
			//	chances[i] = e.getValue() * CHANCE_CONSTANT;
			//	i++;
			//}
			asr.addRecipe(false, new ItemStack[] { (new ItemStack(b, 1)) }, outputs, null, chances, new FluidStack[0], new FluidStack[0], 512, 4, 0);
		}

		// Gem extractor recipe
		ItemStack[] outputs = new ItemStack[gems.size()];
		int[] chances = new int[gems.size()];
		int index = 0;
		for (Entry<ItemStack, Integer> e : gems.entrySet()) {
			outputs[index] = e.getKey();
			chances[index] = e.getValue() * CHANCE_CONSTANT;
			index++;
		}
		ger.addRecipe(false, new ItemStack[] { (new ItemStack(gemSand, 1)) }, outputs, null, chances, new FluidStack[0], new FluidStack[0], 512, 4, 0);

		//ItemStack shard = new ItemStack(GregiloItems.GEM_SHARDS.getRealItem());
		//GT_Values.RA.addMixerRecipe(new ItemStack(Blocks.sand), shard, shard, shard, null, null, new ItemStack(this), 400, 16);

		// Compressed hammer and industrial hammer
		{
			List<String> hammerTypes = Arrays.asList("Dust", "Dust", "Sand", "Gravel", "Cobblestone");
			for (int s = 1; s < hammerTypes.size(); s++) {
				for (int i = 1; i <= 8; i++) {
					List<ItemStack> current = new ArrayList<ItemStack>(OreDictionary.getOres("compressed" + hammerTypes.get(s) + i + "x"));
					List<ItemStack> previous = new ArrayList<ItemStack>(OreDictionary.getOres("compressed" + hammerTypes.get(s - 1) + (i - 1) + "x"));
					if (i == 1) {
						if (linkManager.isEnabled(ExCompressum.class)) {
							ExCompressum ec = linkManager.getLink(ExCompressum.class);
							if (hammerTypes.get(s).equals("Gravel")) {
								current.add(ec.getCompressedGravel());
							} else if (hammerTypes.get(s).equals("Sand")) {
								current.add(ec.getCompressedSand());
							} else if (hammerTypes.get(s).equals("Dust")) {
								current.add(ec.getCompressedDust());
							}
						}
						if (hammerTypes.get(s - 1).equals("Gravel")) {
							previous.add(new ItemStack(Blocks.gravel));
						} else if (hammerTypes.get(s - 1).equals("Sand")) {
							previous.add(new ItemStack(Blocks.sand));
						} else if (hammerTypes.get(s - 1).equals("Dust")) {
							previous.add(new ItemStack(getDustBlock()));
						}
					}
					if (current.size() == 0 || previous.size() == 0) {
						continue;
					}
					for (ItemStack in : current) {
						for (ItemStack out : previous) {
							out = new ItemStack(out.getItem(), 9, out.getItemDamage());
							chr.addRecipe(false, new ItemStack[] { in }, new ItemStack[] { out }, null, new int[] { 10000 }, new FluidStack[0], new FluidStack[0], 0, 0, 0);
							ihr.addRecipe(false, new ItemStack[] { in }, new ItemStack[] { out }, null, new int[] { 10000 }, new FluidStack[0], new FluidStack[0], 128, 4, 0);
						}
					}
				}
			}
		}

		//formatter:off
		GameRegistry.addRecipe(new ItemStack(advancedSieve, 1), new Object[]{
		"   ", 
		"RPR", 
		"R R", 
		Character.valueOf('P'), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1), 
		Character.valueOf('R'), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(gemSand), 
		new ItemStack(Blocks.sand), new ItemStack(Blocks.sand),
		new ItemStack(gemShards),
		new ItemStack(gemShards));
		//formatter:on

		if (linkManager.isEnabled(ExNihilo.class)) {
			ItemStack dust = new ItemStack(linkManager.getLink(ExNihilo.class).getDustBlock());
			ItemStack sand = new ItemStack(Blocks.sand);
			GT_Values.RA.addForgeHammerRecipe(sand, dust, 16, 10);
		}
	}

	@Override
	public void postloadLink() {

	}

	public Map<ItemStack, Integer> getSubMap(Block block) {
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

	private void addGem(ItemStack is, int chance) {
		gems.put(is, chance);
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

	public void setIdStart(int i) {
		this.idStart = i;
	}

	public int getIdStart() {
		return idStart;
	}

	private int id(int i) {
		return i + idStart;
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
