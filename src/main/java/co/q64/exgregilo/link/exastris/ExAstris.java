package co.q64.exgregilo.link.exastris;

import javax.inject.Inject;

import net.minecraft.item.ItemStack;
import ExAstris.ExAstrisBlock;
import co.q64.com.google.inject.Singleton;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.api.link.ModLink;
import co.q64.exgregilo.data.ModIds;
import co.q64.exgregilo.util.CraftingKiller;

@Singleton
@ModLink(modId = ModIds.EX_ASTRIS_ID, modName = "Ex Astris")
public class ExAstris extends LinkBase {
	private @Inject CraftingKiller killer;

	@Override
	public void loadLink() {
		killer.remove(new ItemStack(ExAstrisBlock.SieveAutomatic, 1));
	}

	@Override
	public void postloadLink() {}
}
