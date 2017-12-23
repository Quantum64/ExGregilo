package co.q64.exgregilo.link.gregtech.tile;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_GT_Recipe.X;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_Recipe.GT_Recipe_Map;
import gregtech.api.util.GT_Utility;
import co.q64.exgregilo.link.gregtech.crafting.MachineRecipeHelper;
import co.q64.exgregilo.link.gregtech.recipe.ElectricCrucibleRecipes;
import co.q64.exgregilo.link.gregtech.render.BlockTextures;

public class ElectricCrucible extends GT_MetaTileEntity_BasicMachine {
	private static final int IN_SLOTS = 1;
	private static final int OUT_SLOTS = 1;
	private ElectricCrucibleRecipes map;

	public ElectricCrucible(int aID, String aName, String aNameRegional, int aTier, MachineRecipeHelper helper, ElectricCrucibleRecipes map) {
		super(aID, aName, aNameRegional, aTier, 1, "Greg's crucible with pipe support", IN_SLOTS, OUT_SLOTS, "electriccrucible.png", "crucible",
//formatter:off
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK));

		helper.addMachineRecipe(this, aTier, new Object[] { 
				"PCP",
				"HMH",
				"WHW", 
				Character.valueOf('M'), X.HULL, 
				Character.valueOf('C'), X.CIRCUIT, 
				Character.valueOf('W'), X.WIRE, 
				Character.valueOf('H'), X.COIL_HEATING_DOUBLE,
				Character.valueOf('P'), X.PUMP });
		
//formatter:on
		this.map = map;

	}

	public ElectricCrucible(String aName, int aTier, String aDescription, ITexture[][][] aTextures, String aGUIName, String aNEIName, ElectricCrucibleRecipes map) {
		super(aName, aTier, 1, aDescription, aTextures, IN_SLOTS, OUT_SLOTS, aGUIName, aNEIName);
		this.map = map;
	}

	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new ElectricCrucible(mName, mTier, mDescription, mTextures, mGUIName, mNEIName, map);
	}

	@Override
	public GT_Recipe_Map getRecipeList() {
		return map;
	}

	@Override
	public int getCapacity() {
		return 16000;
	}

	@Override
	public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
		super.startSoundLoop(aIndex, aX, aY, aZ);
		if (aIndex == 1)
			GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(207), 10, 1.0F, aX, aY, aZ);
	}

	@Override
	public void startProcess() {
		sendLoopStart((byte) 1);
	}
}
