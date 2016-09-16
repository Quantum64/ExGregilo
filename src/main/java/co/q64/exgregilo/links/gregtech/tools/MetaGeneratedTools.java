package co.q64.exgregilo.links.gregtech.tools;

import gregtech.api.items.GT_MetaGenerated_Tool;
import co.q64.exgregilo.links.gregtech.crafting.OreDictAddons;

public class MetaGeneratedTools extends GT_MetaGenerated_Tool {
	public static final int WIRE_MESH_ID = 0;

	public MetaGeneratedTools() {
		super("exgregilo.metatool.01");
		addTool(WIRE_MESH_ID, "Wire Mesh", "Place in an Advanced Sieve", new WireMesh(), new Object[] { OreDictAddons.WIRE_MESH });
	}

}
