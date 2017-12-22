package co.q64.exgregilo.block;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.material.Material;
import co.q64.exgregilo.api.binders.ModDataBinders.CompressedGravelType;

@Singleton
public class CompressedGravel extends AbstractCompressed {

	@Inject
	public CompressedGravel(@CompressedGravelType String type) {
		super(Material.sand, type);
	}
}
