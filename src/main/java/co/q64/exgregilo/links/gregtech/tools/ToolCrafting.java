package co.q64.exgregilo.links.gregtech.tools;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.interfaces.IOreRecipeRegistrator;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.api.ExGregiloAPI;
import co.q64.exgregilo.links.gregtech.GregTech;

public class ToolCrafting implements IOreRecipeRegistrator {

	@Override
	public void registerOre(OrePrefixes prefix, Materials material, String oreDictName, String modName, ItemStack stack) {
		if ((!material.contains(SubTag.WOOD)) && (!material.contains(SubTag.BOUNCY)) && (!material.contains(SubTag.NO_SMASHING))) {
			//formatter:off
		GT_ModHandler.addCraftingRecipe(ExGregiloAPI.getLinkManager().getLink(GregTech.class).getTools().getToolWithStats(MetaGeneratedTools.WIRE_MESH_ID, 1, material, material.mHandleMaterial, null), GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{
			"WWW", 
			"WGW", 
			"WWW", 
			Character.valueOf('W'), OrePrefixes.wireGt01.get(material), 
			Character.valueOf('G'), OrePrefixes.gearGtSmall.get(material.mHandleMaterial)});
		//formatter:on
		}
	}
}
