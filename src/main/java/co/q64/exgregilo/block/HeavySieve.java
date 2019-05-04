package co.q64.exgregilo.block;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import co.q64.exgregilo.api.binders.ModDataBinders.DomainPath;
import co.q64.exgregilo.api.binders.ModDataBinders.HeavySieveBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.HeavySieveMesh;
import co.q64.exgregilo.api.binders.ModDataBinders.ModId;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.link.gregtech.tools.MetaGeneratedTools;
import co.q64.exgregilo.tile.AbstractSieveTile;
import co.q64.exgregilo.tile.AbstractSieveTile.SieveMode;
import co.q64.exgregilo.tile.HeavySieveTile;
import co.q64.exgregilo.util.SieveRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Singleton
public class HeavySieve extends AbstractSieve {
	private @Inject @DomainPath String domainPath;
	private @Inject @HeavySieveMesh String mesh;
	private @Inject SieveRegistry registry;
	private @Inject GregTech gt;
	private @Inject Provider<HeavySieveTile> tileProvider;
	private @Inject Dust dust;

	private IIcon meshIcon;

	@Inject
	public HeavySieve(@ModId String modId, @HeavySieveBlockName String name) {
		super();
		setBlockName(modId + "." + name);
		GameRegistry.registerTileEntity(HeavySieveTile.class, modId + "." + name);
	}

	@Override
	public int getMeshId() {
		return MetaGeneratedTools.HEAVY_MESH_ID;
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

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player == null) {
			return false;
		}
		AbstractSieveTile sieve = (AbstractSieveTile) world.getTileEntity(x, y, z);
		if (sieve.mode == SieveMode.EMPTY && player.getCurrentEquippedItem() != null) {
			ItemStack held = player.getCurrentEquippedItem();
			if (held.getItem() instanceof MetaGeneratedTools) {
				if (!held.getUnlocalizedName().equals(gt.getTools().getUnlocalizedName() + "." + getMeshId())) {
					return true;
				}
				if (sieve.getMesh() != null) {
					sieve.dropMesh();
				}
				sieve.setMesh(held.copy());
				removeCurrentItem(player);
				return true;
			}
			if (sieve.getMesh() != null) {
				for (ItemStack is : OreDictionary.getOres("compressedGravel1x")) {
					if (is.getItem() == held.getItem() && is.getItemDamage() == held.getItemDamage()) {
						sieve.addSievable(Blocks.gravel, 0);
						removeCurrentItem(player);
						break;
					}
				}
				for (ItemStack is : OreDictionary.getOres("compressedSand1x")) {
					if (is.getItem() == held.getItem() && is.getItemDamage() == held.getItemDamage()) {
						sieve.addSievable(Blocks.sand, 0);
						removeCurrentItem(player);
						break;
					}
				}
				for (ItemStack is : OreDictionary.getOres("compressedDust1x")) {
					if (is.getItem() == held.getItem() && is.getItemDamage() == held.getItemDamage()) {
						sieve.addSievable(dust, 0);
						removeCurrentItem(player);
						break;
					}
				}
			}
		} else {
			if (sieve.mode == SieveMode.EMPTY && player.getCurrentEquippedItem() == null && sieve.getMesh() != null) {
				sieve.dropMesh();
				return true;
			}
			if (world.isRemote) {
				sieve.processContents(registry, false);
			} else {
				if (sieve.mode != SieveMode.EMPTY) {
					if (isHuman(player)) {
						sieve.processContents(registry, false);
					}
				}
			}
		}
		return true;
	}
}
