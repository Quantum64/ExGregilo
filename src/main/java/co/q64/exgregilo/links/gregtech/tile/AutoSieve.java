package co.q64.exgregilo.links.gregtech.tile;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import co.q64.exgregilo.links.gregtech.render.BlockTextures;

public class AutoSieve extends GT_MetaTileEntity_BasicMachine {
	public AutoSieve(int aID, String aName, String aNameRegional, int aTier) {
		super(aID, aName, aNameRegional, aTier, 1, "It's like sieving... but more auto", 1, 1, "Massfabricator.png", "sieve",
				//formatter:off
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK),
				aTier == 1 ? new GT_RenderedTexture(BlockTextures.OVERLAY_AUTO_SIEVE_BASIC_TOP) : new GT_RenderedTexture(BlockTextures.OVERLAY_AUTO_SIEVE_TOP),
				aTier == 1 ? new GT_RenderedTexture(BlockTextures.OVERLAY_AUTO_SIEVE_BASIC_TOP) : new GT_RenderedTexture(BlockTextures.OVERLAY_AUTO_SIEVE_TOP),
				new GT_RenderedTexture(BlockTextures.BLANK),
				new GT_RenderedTexture(BlockTextures.BLANK));
				//formatter:on
	}

	public AutoSieve(String aName, int aTier, String aDescription, ITexture[][][] aTextures, String aGUIName, String aNEIName) {
		super(aName, aTier, 1, aDescription, aTextures, 1, 1, aGUIName, aNEIName);
	}

	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new AutoSieve(mName, mTier, mDescription, mTextures, mGUIName, mNEIName);
	}

	@Override
	public int checkRecipe() {
		if (null != (mOutputItems[0] = GT_ModHandler.getSmeltingOutput(getInputAt(0), true, getOutputAt(0)))) {
			mEUt = 4 * (1 << (mTier - 1)) * (1 << (mTier - 1));
			mMaxProgresstime = 128 / (1 << (mTier - 1));
			return 2;
		}
		return 0;
	}

	@Override
	public boolean allowPutStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, byte aSide, ItemStack aStack) {
		return super.allowPutStack(aBaseMetaTileEntity, aIndex, aSide, aStack) && GT_ModHandler.getSmeltingOutput(GT_Utility.copyAmount(64, aStack), false, null) != null;
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
