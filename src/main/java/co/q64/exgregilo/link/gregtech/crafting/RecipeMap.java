package co.q64.exgregilo.link.gregtech.crafting;

import gregtech.api.enums.GT_Values;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Recipe.GT_Recipe_Map;

import java.util.HashSet;

import co.q64.exgregilo.data.ModData;

public class RecipeMap {
	public static final GT_Recipe_Map AUTO_SIEVE_RECIPES = new GT_Recipe_Map(new HashSet<GT_Recipe>(100), "gt.recipe.autosieve", "Auto Sieve", null, ModData.ITEM_ICON_TEX_PATH + "Autosieve", 1, 6, 1, 0, 1, GT_Values.E, 0, GT_Values.E, true, true);
}
