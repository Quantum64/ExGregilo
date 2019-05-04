package co.q64.exgregilo.block;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import co.q64.exgregilo.api.binders.ModDataBinders.BasicSieveBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.BasicSieveMesh;
import co.q64.exgregilo.api.binders.ModDataBinders.DomainPath;
import co.q64.exgregilo.api.binders.ModDataBinders.ModId;
import co.q64.exgregilo.link.gregtech.tools.MetaGeneratedTools;
import co.q64.exgregilo.tile.BasicSieveTile;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Singleton
public class BasicSieve extends AbstractSieve {
	private @Inject @DomainPath String domainPath;
	private @Inject @BasicSieveMesh String mesh;
	private @Inject Provider<BasicSieveTile> tileProvider;

	private IIcon meshIcon;

	@Inject
	public BasicSieve(@ModId String modId, @BasicSieveBlockName String name) {
		super();
		setBlockName(modId + "." + name);
		GameRegistry.registerTileEntity(BasicSieveTile.class, modId + "." + name);
	}

	@Override
	public int getMeshId() {
		return MetaGeneratedTools.BASIC_MESH_ID;
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
