package co.q64.exgregilo.link.gregtech.item;

import gregtech.api.enums.GT_Values;
import gregtech.api.interfaces.IItemContainer;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum ItemList implements IItemContainer {

	//formatter:off
	AUTO_SIEVE_LV, AUTO_SIEVE_MV, AUTO_SIEVE_HV, AUTO_SIEVE_EV, AUTO_SIEVE_IV, AUTO_SIEVE_LuV, AUTO_SIEVE_ZPM, AUTO_SIEVE_UV,
	GEM_EXTRACTOR_LV, GEM_EXTRACTOR_MV, GEM_EXTRACTOR_HV, GEM_EXTRACTOR_EV, GEM_EXTRACTOR_IV, GEM_EXTRACTOR_LuV, GEM_EXTRACTOR_ZPM, GEM_EXTRACTOR_UV,
	INDUSTRIAL_HAMMER_LV, INDUSTRIAL_HAMMER_MV, INDUSTRIAL_HAMMER_HV, INDUSTRIAL_HAMMER_EV, INDUSTRIAL_HAMMER_IV, INDUSTRIAL_HAMMER_LuV, INDUSTRIAL_HAMMER_ZPM, INDUSTRIAL_HAMMER_UV,
	ELECTRIC_CRUCIBLE_LV, ELECTRIC_CRUCIBLE_MV, ELECTRIC_CRUCIBLE_HV, ELECTRIC_CRUCIBLE_EV, ELECTRIC_CRUCIBLE_IV, ELECTRIC_CRUCIBLE_LuV, ELECTRIC_CRUCIBLE_ZPM, ELECTRIC_CRUCIBLE_UV
	//formatter:on
	;

	private ItemStack item;
	private boolean isSet = true;

	@Override
	public IItemContainer set(Item aItem) {
		isSet = false;
		if (aItem == null)
			return this;
		ItemStack aStack = new ItemStack(aItem, 1, 0);
		item = GT_Utility.copyAmount(1, aStack);
		return this;
	}

	@Override
	public IItemContainer set(ItemStack aStack) {
		isSet = false;
		item = GT_Utility.copyAmount(1, aStack);
		return this;
	}

	@Override
	public Item getItem() {
		if (isSet)
			throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(item))
			return null;
		return item.getItem();
	}

	@Override
	public Block getBlock() {
		if (isSet)
			throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
		return GT_Utility.getBlockFromItem(getItem());
	}

	@Override
	public final boolean hasBeenSet() {
		return !isSet;
	}

	@Override
	public boolean isStackEqual(Object aStack) {
		return isStackEqual(aStack, false, false);
	}

	@Override
	public boolean isStackEqual(Object aStack, boolean aWildcard, boolean aIgnoreNBT) {
		if (GT_Utility.isStackInvalid(aStack))
			return false;
		return GT_Utility.areUnificationsEqual((ItemStack) aStack, aWildcard ? getWildcard(1) : get(1), aIgnoreNBT);
	}

	@Override
	public ItemStack get(long aAmount, Object... aReplacements) {
		if (isSet)
			throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(item))
			return GT_Utility.copyAmount(aAmount, aReplacements);
		return GT_Utility.copyAmount(aAmount, GT_OreDictUnificator.get(item));
	}

	@Override
	public ItemStack getWildcard(long aAmount, Object... aReplacements) {
		if (isSet)
			throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(item))
			return GT_Utility.copyAmount(aAmount, aReplacements);
		return GT_Utility.copyAmountAndMetaData(aAmount, GT_Values.W, GT_OreDictUnificator.get(item));
	}

	@Override
	public ItemStack getUndamaged(long aAmount, Object... aReplacements) {
		if (isSet)
			throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(item))
			return GT_Utility.copyAmount(aAmount, aReplacements);
		return GT_Utility.copyAmountAndMetaData(aAmount, 0, GT_OreDictUnificator.get(item));
	}

	@Override
	public ItemStack getAlmostBroken(long aAmount, Object... aReplacements) {
		if (isSet)
			throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(item))
			return GT_Utility.copyAmount(aAmount, aReplacements);
		return GT_Utility.copyAmountAndMetaData(aAmount, item.getMaxDamage() - 1, GT_OreDictUnificator.get(item));
	}

	@Override
	public ItemStack getWithName(long aAmount, String aDisplayName, Object... aReplacements) {
		ItemStack rStack = get(1, aReplacements);
		if (GT_Utility.isStackInvalid(rStack))
			return null;
		rStack.setStackDisplayName(aDisplayName);
		return GT_Utility.copyAmount(aAmount, rStack);
	}

	@Override
	public ItemStack getWithCharge(long aAmount, int aEnergy, Object... aReplacements) {
		ItemStack rStack = get(1, aReplacements);
		if (GT_Utility.isStackInvalid(rStack))
			return null;
		GT_ModHandler.chargeElectricItem(rStack, aEnergy, Integer.MAX_VALUE, true, false);
		return GT_Utility.copyAmount(aAmount, rStack);
	}

	@Override
	public ItemStack getWithDamage(long aAmount, long aMetaValue, Object... aReplacements) {
		if (isSet)
			throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(item))
			return GT_Utility.copyAmount(aAmount, aReplacements);
		return GT_Utility.copyAmountAndMetaData(aAmount, aMetaValue, GT_OreDictUnificator.get(item));
	}

	@Override
	public IItemContainer registerOre(Object... aOreNames) {
		if (isSet)
			throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
		for (Object tOreName : aOreNames)
			GT_OreDictUnificator.registerOre(tOreName, get(1));
		return this;
	}

	@Override
	public IItemContainer registerWildcardAsOre(Object... aOreNames) {
		if (isSet)
			throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
		for (Object tOreName : aOreNames)
			GT_OreDictUnificator.registerOre(tOreName, getWildcard(1));
		return this;
	}

}
