package co.q64.exgregilo.block;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import co.q64.exgregilo.api.binders.ModDataBinders.AdvancedSieveBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.AdvancedSieveMesh;
import co.q64.exgregilo.api.binders.ModDataBinders.DomainPath;
import co.q64.exgregilo.api.binders.ModDataBinders.ModId;
import co.q64.exgregilo.link.gregtech.tools.MetaGeneratedTools;
import co.q64.exgregilo.tile.AdvancedSieveTile;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Singleton
public class AdvancedSieve extends AbstractSieve {
	private @Inject @DomainPath String domainPath;
	private @Inject @AdvancedSieveMesh String mesh;
	private @Inject Provider<AdvancedSieveTile> tileProvider;

	private IIcon meshIcon;

	@Inject

	public AdvancedSieve(@ModId String modId, @AdvancedSieveBlockName String name) {
		super();
		setBlockName(modId + "." + name);
		GameRegistry.registerTileEntity(AdvancedSieveTile.class, modId + "." + name);
	}

	@Override
	public int getMeshId() {
		return MetaGeneratedTools.WIRE_MESH_ID;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return tileProvider.get();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
		meshIcon = register.registerIcon(domainPath + mesh);
	}

	@Override
	public IIcon getMeshTexture() {
		return meshIcon;
	}
}
