package co.q64.exgregilo.util;

import exnihilo.registries.SieveRegistry;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GTSieveRegistration {
	public static void addGTOres() {
		ItemStack bauxite = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bauxite, 1L);
		SieveRegistry.register(Blocks.gravel, bauxite.getItem(), bauxite.getItemDamage(), 10);
	}
}
