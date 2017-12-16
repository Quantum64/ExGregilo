package co.q64.exgregilo.link.excompressum;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.blay09.mods.excompressum.ModBlocks;
import net.blay09.mods.excompressum.ModItems;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.config.ConfigManager;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.data.ModIds;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.GregTech;

@Singleton
@ModLink(modId = ModIds.EX_COMPRESSUM_ID, modName = "ExCompressum")
public class ExCompressum extends LinkBase {
	private @Inject LinkManager linkManager;
	private @Inject ConfigManager configManager;

	@Override
	public void loadLink() {
		if (linkManager.isEnabled(GregTech.class)) {
			GregTech gt = linkManager.getLink(GregTech.class);
			gt.removeRecipe(new ItemStack(ModBlocks.autoCompressedHammer, 1));
			gt.removeRecipe(new ItemStack(ModBlocks.autoHammer, 1));
			gt.removeRecipe(new ItemStack(ModBlocks.autoSieve, 1));
			gt.removeRecipe(new ItemStack(ModBlocks.autoHeavySieve, 1));

			if (configManager.getBoolean(ExNihilo.class, "removeCompressedHammer", true)) {
				gt.removeRecipe(new ItemStack(ModItems.compressedHammerDiamond));
				gt.removeRecipe(new ItemStack(ModItems.compressedHammerGold));
				gt.removeRecipe(new ItemStack(ModItems.compressedHammerIron));
				gt.removeRecipe(new ItemStack(ModItems.compressedHammerStone));
				gt.removeRecipe(new ItemStack(ModItems.compressedHammerWood));
			}
		}
	}

	public void fixThisModsDumbRegistrationTime() {
		loadLink();
	}

	public ItemStack getCompressedGravel() {
		return new ItemStack(ModBlocks.compressedBlock, 1, 2);
	}

	public ItemStack getCompressedSand() {
		return new ItemStack(ModBlocks.compressedBlock, 1, 3);
	}

	public ItemStack getCompressedDust() {
		return new ItemStack(ModBlocks.compressedBlock, 1, 0);
	}
}
