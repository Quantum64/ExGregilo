package co.q64.exgregilo.links.exnihilo;

import co.q64.exgregilo.api.ExGregiloAPI;
import co.q64.exgregilo.api.links.LinkBase;
import co.q64.exgregilo.api.links.ModLink;
import co.q64.exgregilo.data.ModData;

@ModLink(modName = "Ex Nihilo", modId = ModData.EX_NIHILO_ID)
public class ExNihilo implements LinkBase {

	@Override
	public void loadLink() {
		if (ExGregiloAPI.getConfigManager().getBoolean(getClass(), "removeDefaultSiftOres", true)) {
			SieveRegistryCleaner.removeDefaultOres();
		}
	}

	@Override
	public void enableLink() {
		
	}
}
