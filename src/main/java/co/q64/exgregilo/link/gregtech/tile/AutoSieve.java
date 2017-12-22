package co.q64.exgregilo.link.gregtech.tile;

import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_GT_Recipe.X;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Recipe.GT_Recipe_Map;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import co.q64.exgregilo.link.gregtech.crafting.MachineRecipeHelper;
import co.q64.exgregilo.link.gregtech.recipe.AutoSieveRecipes;
import co.q64.exgregilo.link.gregtech.render.BlockTextures;

public class AutoSieve extends GT_MetaTileEntity_BasicMachine {
	private static final int IN_SLOTS = 1;
	private static final int OUT_SLOTS = 6;
	private AutoSieveRecipes map;

	public AutoSieve(int aID, String aName, String aNameRegional, int aTier, MachineRecipeHelper helper, AutoSieveRecipes map) {
		super(aID, aName, aNameRegional, aTier, 1, "It's like sieving... but more auto", IN_SLOTS, OUT_SLOTS, "autosieve.png", "sieve",
//formatter:off
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.OVERLAY_AUTO_SIEVE_FRONT),
				new GT_RenderedTexture(BlockTextures.OVERLAY_AUTO_SIEVE_FRONT),
				aTier == 1 ? new GT_RenderedTexture(BlockTextures.OVERLAY_AUTO_SIEVE_BASIC_TOP) : new GT_RenderedTexture(BlockTextures.OVERLAY_AUTO_SIEVE_TOP),
				aTier == 1 ? new GT_RenderedTexture(BlockTextures.OVERLAY_AUTO_SIEVE_BASIC_TOP) : new GT_RenderedTexture(BlockTextures.OVERLAY_AUTO_SIEVE_TOP),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK));

		helper.addMachineRecipe(this, aTier, new Object[] { 
				"PEP",
				"AMC",
				"PHP", 
				Character.valueOf('H'), X.HULL, 
				Character.valueOf('M'), X.MOTOR, 
				Character.valueOf('C'), X.CONVEYOR, 
				Character.valueOf('W'), X.WIRE, 
				Character.valueOf('A'), X.ROBOT_ARM,
				Character.valueOf('P'), X.PLATE,
				Character.valueOf('E'), X.PISTON });
		
//formatter:on
		this.map = map;
	}

	public AutoSieve(String aName, int aTier, String aDescription, ITexture[][][] aTextures, String aGUIName, String aNEIName, AutoSieveRecipes map) {
		super(aName, aTier, 1, aDescription, aTextures, IN_SLOTS, OUT_SLOTS, aGUIName, aNEIName);
		this.map = map;
	}

	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new AutoSieve(mName, mTier, mDescription, mTextures, mGUIName, mNEIName, map);
	}

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
		/*
		if (!canOutput(tRecipe)) {
			mOutputBlocked++;
			return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
		}
		*/
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
		mEUt = mTier * 4 * (1 << (mTier - 1)) * (1 << (mTier - 1));
		return FOUND_AND_SUCCESSFULLY_USED_RECIPE;
	}

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
