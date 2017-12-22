package co.q64.exgregilo.block;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.material.Material;
import co.q64.exgregilo.api.binders.ModDataBinders.CompressedDustType;

@Singleton
public class CompressedDust extends AbstractCompressed {

	@Inject
	public CompressedDust(@CompressedDustType String type) {
		super(Material.sand, type);
	}
}
