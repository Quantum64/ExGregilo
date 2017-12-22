package co.q64.exgregilo.link.gregtech.render;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.IIconContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import co.q64.exgregilo.data.ModData;

public enum BlockTextures implements IIconContainer, Runnable {
	//formatter:off
	BLANK("blank"),
	OVERLAY_AUTO_SIEVE_TOP("overlay_auto_sieve_top"),
	OVERLAY_AUTO_SIEVE_FRONT("overlay_auto_sieve_front"),
	OVERLAY_AUTO_SIEVE_BASIC_TOP("overlay_auto_sieve_basic_top"),
	OVERLAY_GEM_EXTRACTOR_TOP("overlay_gem_extractor_top"),
	OVERLAY_GEM_EXTRACTOR_FRONT("overlay_gem_extractor_front"),
	OVERLAY_INDUSTRIAL_FORGE_HAMMER_FRONT("overlay_industrial_forge_hammer_front"),
	OVERLAY_INDUSTRIAL_FORGE_HAMMER_FRONT_ACTIVE("overlay_industrial_forge_hammer_front_active");
	//formatter:on
	;

	private String textureName;
	private IIcon icon;

	private BlockTextures(String textureName) {
		this.textureName = textureName;
		GregTech_API.sGTBlockIconload.add(this);
	}

	@Override
	public void run() {
		icon = GregTech_API.sBlockIcons.registerIcon(ModData.BLOCK_ICON_TEX_PATH + textureName);
	}

	@Override
	public IIcon getIcon() {
		return icon;
	}

	@Override
	public IIcon getOverlayIcon() {
		return null;
	}

	@Override
	public ResourceLocation getTextureFile() {
		return TextureMap.locationBlocksTexture;
	}
}
