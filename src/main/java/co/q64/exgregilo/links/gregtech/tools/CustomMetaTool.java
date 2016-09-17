package co.q64.exgregilo.links.gregtech.tools;

import gregtech.api.interfaces.IIconContainer;
import gregtech.common.tools.GT_Tool;

public abstract class CustomMetaTool extends GT_Tool {
	public CustomMetaTool() {
		for (IIconContainer icon : getTextureClass().getEnumConstants()) {
			icon.getTextureFile();
		}
	}

	public abstract Class<? extends IIconContainer> getTextureClass();
}
