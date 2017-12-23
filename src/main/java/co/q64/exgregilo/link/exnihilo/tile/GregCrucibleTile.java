package co.q64.exgregilo.link.exnihilo.tile;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import exnihilo.blocks.tileentities.TileEntityCrucible;

public class GregCrucibleTile extends TileEntityCrucible {

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}
}
