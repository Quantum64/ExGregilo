package co.q64.exgregilo.links.exastris;

import net.minecraft.item.ItemStack;
import ExAstris.ExAstrisBlock;
import co.q64.exgregilo.api.ExGregiloAPI;
import co.q64.exgregilo.api.links.LinkBase;
import co.q64.exgregilo.api.links.ModLink;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.links.gregtech.GregTech;

@ModLink(modId = ModData.EX_ASTRIS_ID, modName = "Ex Astris")
public class ExAstris implements LinkBase {

	@Override
	public void loadLink() {
	}

	@Override
	public void postLoadLink() {
		if (ExGregiloAPI.getLinkManager().isEnabled(GregTech.class)) {
			ExGregiloAPI.getLinkManager().getLink(GregTech.class).removeRecipe(new ItemStack(ExAstrisBlock.SieveAutomatic, 1));
		}
	}

	@Override
	public void afterPostLoadLink() {
	}
}
