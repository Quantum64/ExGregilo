package co.q64.exgregilo.link.exastris;

import javax.inject.Inject;

import net.minecraft.item.ItemStack;
import ExAstris.ExAstrisBlock;
import co.q64.com.google.inject.Singleton;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.data.ModData;
import co.q64.exgregilo.link.gregtech.GregTech;

@Singleton
@ModLink(modId = ModData.EX_ASTRIS_ID, modName = "Ex Astris")
public class ExAstris extends LinkBase {
	private @Inject LinkManager linkManager;

	@Override
	public void loadLink() {
		if (linkManager.isEnabled(GregTech.class)) {
			linkManager.getLink(GregTech.class).removeRecipe(new ItemStack(ExAstrisBlock.SieveAutomatic, 1));
		}
	}

	@Override
	public void postloadLink() {}
}
