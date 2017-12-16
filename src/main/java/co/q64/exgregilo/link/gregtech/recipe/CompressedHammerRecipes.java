package co.q64.exgregilo.link.gregtech.recipe;

import gregtech.api.enums.GT_Values;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Recipe.GT_Recipe_Map;

import java.util.HashSet;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.link.gregtech.gui.NEITextureHelper;

@Singleton
public class CompressedHammerRecipes extends GT_Recipe_Map {

	@Inject
	public CompressedHammerRecipes(NEITextureHelper nei) {
		super(new HashSet<GT_Recipe>(100), "gt.recipe.compressedhammer", "Compressed Hammer", null, nei.getNEITexture("tools/compressedhammer"), 1, 1, 1, 0, 1, GT_Values.E, 0, GT_Values.E, true, true);
	}

}
