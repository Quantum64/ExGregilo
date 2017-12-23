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
import co.q64.exgregilo.link.gregtech.recipe.IndustrialForgeHammerRecipes;
import co.q64.exgregilo.link.gregtech.render.BlockTextures;

public class IndustrialForgeHammer extends GT_MetaTileEntity_BasicMachine {
	private static final int IN_SLOTS = 1;
	private static final int OUT_SLOTS = 1;
	private IndustrialForgeHammerRecipes map;

	public IndustrialForgeHammer(int aID, String aName, String aNameRegional, int aTier, MachineRecipeHelper helper, IndustrialForgeHammerRecipes map) {
		super(aID, aName, aNameRegional, aTier, 1, "Industrial forge hammer description", IN_SLOTS, OUT_SLOTS, "industrialhammer.png", "sieve",
//formatter:off
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.OVERLAY_INDUSTRIAL_FORGE_HAMMER_FRONT),
				new GT_RenderedTexture(BlockTextures.OVERLAY_INDUSTRIAL_FORGE_HAMMER_FRONT_ACTIVE),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK));

		helper.addMachineRecipe(this, aTier, new Object[] { 
				"WPW",
				"AHA",
				"WCW", 
				Character.valueOf('H'), X.HULL, 
				Character.valueOf('C'), X.CONVEYOR, 
				Character.valueOf('W'), X.WIRE, 
				Character.valueOf('A'), X.MOTOR,
				Character.valueOf('P'), X.PISTON });
		
//formatter:on
		this.map = map;
	}

	public IndustrialForgeHammer(String aName, int aTier, String aDescription, ITexture[][][] aTextures, String aGUIName, String aNEIName, IndustrialForgeHammerRecipes map) {
		super(aName, aTier, 1, aDescription, aTextures, IN_SLOTS, OUT_SLOTS, aGUIName, aNEIName);
		this.map = map;
	}

	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new IndustrialForgeHammer(mName, mTier, mDescription, mTextures, mGUIName, mNEIName, map);
	}

	/*
	@Override
	public int checkRecipe() {
		GT_Recipe_Map tMap = getRecipeList();
		if (tMap == null) {
			return DID_NOT_FIND_RECIPE;
		}
		GT_Recipe tRecipe = tMap.findRecipe(getBaseMetaTileEntity(), mLastRecipe, false, GT_Values.V[mTier], new FluidStack[] { getFillableStack() }, getSpecialSlot(), getAllInputs());
		if (tRecipe == null)
			return DID_NOT_FIND_RECIPE;
		if (tRecipe.mCanBeBuffered)
			mLastRecipe = tRecipe;
		if (!tRecipe.isRecipeInputEqual(true, new FluidStack[] { getFillableStack() }, getAllInputs())) {
			return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
		}

		boolean foundNull = false;
		for (ItemStack is : getAllOutputs()) {
			if (is == null) {
				foundNull = true;
			}
		}
		if (!foundNull) {
			return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
		}

		int outputIndex = 0;
		for (int i = 0; i < tRecipe.mOutputs.length; i++) {
			if (getBaseMetaTileEntity().getRandomNumber(tRecipe.getOutputChance(i) / 4) == 0) {
				if (outputIndex >= mOutputItems.length) {
					break;
				}
				mOutputItems[outputIndex] = tRecipe.getOutput(i);
				outputIndex++;
			}
		}
		mOutputFluid = tRecipe.getFluidOutput(0);
		//calculateOverclockedNess(tRecipe);
		mMaxProgresstime = 512 / (1 << (mTier - 1));
		mEUt = 4 * (1 << (mTier - 1)) * (1 << (mTier - 1));
		return FOUND_AND_SUCCESSFULLY_USED_RECIPE;
	}
	*/

	@Override
	public GT_Recipe_Map getRecipeList() {
		return map;
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
