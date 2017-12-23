package co.q64.exgregilo.loader.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import co.q64.exgregilo.api.binders.ModDataBinders.CompressedBlockName;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.loader.InitLoader;
import co.q64.exgregilo.block.AbstractCompressed;
import co.q64.exgregilo.block.CompressedDust;
import co.q64.exgregilo.block.CompressedGravel;
import co.q64.exgregilo.block.CompressedSand;
import co.q64.exgregilo.block.Dust;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.GregTech;
import cpw.mods.fml.common.registry.GameRegistry;

@Singleton
public class CompressedLoader implements InitLoader {
	private @Inject @CompressedBlockName String cname;
	private @Inject CompressedGravel cgravel;
	private @Inject CompressedSand csand;
	private @Inject CompressedDust cdust;
	private @Inject LinkManager linkManager;
	private @Inject Dust dust;

	@Override
	public void load() {
		boolean gt = linkManager.isEnabled(GregTech.class);
		List<Block> dusts = new ArrayList<Block>();
		if (linkManager.isEnabled(ExNihilo.class)) {
			dusts.add(linkManager.getLink(ExNihilo.class).getDustBlock());
		}
		dusts.add(dust);
		Map<AbstractCompressed, Block> blocks = new HashMap<AbstractCompressed, Block>();
		blocks.put(cgravel, Blocks.gravel);
		blocks.put(csand, Blocks.sand);
		for (Block dust : dusts) {
			blocks.put(cdust, dust);
		}
		for (Entry<AbstractCompressed, Block> entry : blocks.entrySet()) {

			for (int i = 0; i < 8; i++) {
				OreDictionary.registerOre(cname + entry.getKey().getTypeName() + (i + 1) + "x", new ItemStack(entry.getKey(), 1, i));
				if (i == 0) {
					GameRegistry.addShapedRecipe(new ItemStack(entry.getKey(), 1, 0), "BBB", "BBB", "BBB", Character.valueOf('B'), entry.getValue());
					GameRegistry.addShapelessRecipe(new ItemStack(entry.getValue(), 9), new ItemStack(entry.getKey(), 1, 0));
					if (gt) {
						linkManager.getLink(GregTech.class).addCompressorRecipe(new ItemStack(entry.getValue(), 9, 0), new ItemStack(entry.getKey(), 1, i), 100, 10);
						linkManager.getLink(GregTech.class).addCuttingRecipe(new ItemStack(entry.getKey(), 1, 0), new ItemStack(entry.getValue(), 9, 0), 100, 10);
					}
				} else if (i < 4 || !gt) {
					GameRegistry.addShapedRecipe(new ItemStack(entry.getKey(), 1, i), "BBB", "BBB", "BBB", Character.valueOf('B'), new ItemStack(entry.getKey(), 1, i - 1));
					GameRegistry.addShapelessRecipe(new ItemStack(entry.getKey(), 9, i - 1), new ItemStack(entry.getKey(), 1, i));
				}
				if (i > 0 && gt) {
					linkManager.getLink(GregTech.class).addCompressorRecipe(new ItemStack(entry.getKey(), 9, i - 1), new ItemStack(entry.getKey(), 1, i), 100, 10);
					linkManager.getLink(GregTech.class).addCuttingRecipe(new ItemStack(entry.getKey(), 1, 0), new ItemStack(entry.getKey(), 9, i - 1), 100, 10);
				}
			}
		}
	}
}
