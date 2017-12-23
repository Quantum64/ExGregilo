package co.q64.exgregilo.link.excompressum;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.blay09.mods.excompressum.ModBlocks;
import net.blay09.mods.excompressum.ModItems;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.config.ConfigManager;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.data.ModIds;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.util.CraftingKiller;

@Singleton
@ModLink(modId = ModIds.EX_COMPRESSUM_ID, modName = "ExCompressum")
public class ExCompressum extends LinkBase {
	private @Inject CraftingKiller killer;
	private @Inject ConfigManager configManager;

	@Override
	public void loadLink() {
		killer.remove(new ItemStack(ModBlocks.autoCompressedHammer, 1));
		killer.remove(new ItemStack(ModBlocks.autoHammer, 1));
		killer.remove(new ItemStack(ModBlocks.autoSieve, 1));
		killer.remove(new ItemStack(ModBlocks.autoHeavySieve, 1));

		if (configManager.getBoolean(ExNihilo.class, "removeCompressedHammer", true)) {
			killer.remove(new ItemStack(ModItems.compressedHammerDiamond));
			killer.remove(new ItemStack(ModItems.compressedHammerGold));
			killer.remove(new ItemStack(ModItems.compressedHammerIron));
			killer.remove(new ItemStack(ModItems.compressedHammerStone));
			killer.remove(new ItemStack(ModItems.compressedHammerWood));
		}

		if (configManager.getBoolean(ExNihilo.class, "removeHeavySieves", true)) {
			killer.remove(new ItemStack(ModBlocks.heavySieve, 1, 0));
			killer.remove(new ItemStack(ModBlocks.heavySieve, 1, 1));
			killer.remove(new ItemStack(ModBlocks.heavySieve, 1, 2));
			killer.remove(new ItemStack(ModBlocks.heavySieve, 1, 3));
			killer.remove(new ItemStack(ModBlocks.heavySieve, 1, 4));
			killer.remove(new ItemStack(ModBlocks.heavySieve, 1, 5));
		}

		if (configManager.getBoolean(ExNihilo.class, "removeCompressed", true)) {
			killer.remove(new ItemStack(ModBlocks.compressedBlock, 1));
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
