package co.q64.exgregilo.data;

import co.q64.exgregilo.binders.ConstantPool;

public interface ModData {
	public static final String MODID = "exgregilo";
	public static final String VERSION = ConstantPool.VERSION;

	// Has to be here because GT requires enum use
	public static final String ITEM_ICON_TEX_PATH = ModData.MODID  + ":icons/";
	public static final String BLOCK_ICON_TEX_PATH = ModData.MODID  + ":icons/";
}
