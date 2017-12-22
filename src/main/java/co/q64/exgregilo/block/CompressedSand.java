package co.q64.exgregilo.block;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.material.Material;
import co.q64.exgregilo.api.binders.ModDataBinders.CompressedSandType;

@Singleton
public class CompressedSand extends AbstractCompressed {

	@Inject
	public CompressedSand(@CompressedSandType String type) {
		super(Material.sand, type);
	}
}
