package co.q64.exgregilo.link.gregtech.render;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.IIconContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import co.q64.exgregilo.data.ModData;

public enum ItemTextures implements IIconContainer, Runnable {
	BLANK("BLANK"), WIRE_MESH("WIRE_MESH");

	private static final String BLANK_OVERLAY = "BLANK";
	private String textureName;
	private String overlayName;
	private IIcon mIcon;
	private IIcon mOverlay;

	private ItemTextures(String textureName) {
		this(textureName, BLANK_OVERLAY);
	}

	private ItemTextures(String textureName, String overlayName) {
		this.textureName = textureName;
		this.overlayName = overlayName;
		GregTech_API.sGTItemIconload.add(this);
	}

	@Override
	public IIcon getIcon() {
		return mIcon;
	}

	@Override
	public IIcon getOverlayIcon() {
		return mOverlay;
	}

	@Override
	public ResourceLocation getTextureFile() {
		return TextureMap.locationItemsTexture;
	}

	@Override
	public void run() {
		mIcon = GregTech_API.sItemIcons.registerIcon(ModData.ITEM_ICON_TEX_PATH + textureName);
		mOverlay = GregTech_API.sItemIcons.registerIcon(ModData.ITEM_ICON_TEX_PATH + overlayName);
		//ExGregiloAPI.getProxy().getLogger().info("Register item!");
	}
}