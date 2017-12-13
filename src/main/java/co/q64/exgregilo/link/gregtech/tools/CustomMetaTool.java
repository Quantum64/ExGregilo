package co.q64.exgregilo.link.gregtech.tools;

import gregtech.api.interfaces.IIconContainer;
import gregtech.common.tools.GT_Tool;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public abstract class CustomMetaTool extends GT_Tool {
	public CustomMetaTool() {
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			for (IIconContainer icon : getTextureClass().getEnumConstants()) {
				icon.getTextureFile();
			}
		}
	}

	public abstract Class<? extends IIconContainer> getTextureClass();
}
