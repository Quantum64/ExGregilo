package co.q64.exgregilo.link.excompressum;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.blay09.mods.excompressum.ModBlocks;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.link.gregtech.GregTech;

@Singleton
@ModLink(modId = ModData.EX_COMPRESSUM_ID, modName = "ExCompressum")
public class ExCompressum implements LinkBase {
	private @Inject LinkManager linkManager;

	@Override
	public void loadLink() {}

	@Override
	public void postLoadLink() {
		if (linkManager.isEnabled(GregTech.class)) {
			GregTech gt = linkManager.getLink(GregTech.class);
			gt.removeRecipe(new ItemStack(ModBlocks.autoCompressedHammer, 1));
			gt.removeRecipe(new ItemStack(ModBlocks.autoHammer, 1));
			gt.removeRecipe(new ItemStack(ModBlocks.autoSieve, 1));
			gt.removeRecipe(new ItemStack(ModBlocks.autoHeavySieve, 1));
		}
	}

	@Override
	public void afterPostLoadLink() {}

	public void fixThisModsDumbRegistrationTime() {
		postLoadLink();
	}
}
