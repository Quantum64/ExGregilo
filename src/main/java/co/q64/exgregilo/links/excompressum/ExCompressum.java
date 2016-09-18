package co.q64.exgregilo.links.excompressum;

import net.blay09.mods.excompressum.ModBlocks;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.ExGregiloAPI;
import co.q64.exgregilo.api.links.LinkBase;
import co.q64.exgregilo.api.links.ModLink;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.links.gregtech.GregTech;

@ModLink(modId = ModData.EX_COMPRESSUM_ID, modName = "ExCompressum")
public class ExCompressum implements LinkBase {

	@Override
	public void loadLink() {
	}

	@Override
	public void postLoadLink() {
		if (ExGregiloAPI.getLinkManager().isEnabled(GregTech.class)) {
			ExGregiloAPI.getLinkManager().getLink(GregTech.class).removeRecipe(new ItemStack(ModBlocks.autoCompressedHammer, 1));
			ExGregiloAPI.getLinkManager().getLink(GregTech.class).removeRecipe(new ItemStack(ModBlocks.autoHammer, 1));
			ExGregiloAPI.getLinkManager().getLink(GregTech.class).removeRecipe(new ItemStack(ModBlocks.autoSieve, 1));
			ExGregiloAPI.getLinkManager().getLink(GregTech.class).removeRecipe(new ItemStack(ModBlocks.autoHeavySieve, 1));
		}
	}

	@Override
	public void afterPostLoadLink() {
	}

	public void fixThisModsDumbRegistrationTime() {
		postLoadLink();
	}
}
