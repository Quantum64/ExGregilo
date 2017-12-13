package co.q64.exgregilo.link.minetweaker.machines;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.block.IBlock;
import minetweaker.api.item.IItemStack;
import minetweaker.api.item.WeightedItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.link.gregtech.GregTech;

@ZenClass("mods.exgregilo.AutoSieve")
@ModOnly("ExGregilo")
public class ZenAutoSieve {
	private static LinkManager linkManager;

	@ZenMethod
	public static void addProduct(IBlock input, WeightedItemStack output) {
		if (linkManager.isEnabled(GregTech.class)) {
			GregTech gt = linkManager.getLink(GregTech.class);
			MineTweakerAPI.apply(new AddSieveProductAction(gt, input, output));
		}
	}

	@ZenMethod
	public static void removeProduct(IBlock input, IItemStack output) {
		if (linkManager.isEnabled(GregTech.class)) {
			GregTech gt = linkManager.getLink(GregTech.class);
			MineTweakerAPI.apply(new RemoveSieveProductAction(gt, input, output));
		}
	}

	public static void setLinkManager(LinkManager linkManager) {
		ZenAutoSieve.linkManager = linkManager;
	}

	private static class AddSieveProductAction implements IUndoableAction {
		private GregTech gt;
		private IBlock input;
		private WeightedItemStack output;

		public AddSieveProductAction(GregTech gt, IBlock input, WeightedItemStack output) {
			this.gt = gt;
			this.input = input;
			this.output = output;
		}

		@Override
		public void apply() {
			gt.getSubMap(MineTweakerMC.getBlock(input)).put(MineTweakerMC.getItemStack(output.getStack()), (int) (1f / output.getChance()));
		}

		@Override
		public void undo() {
			gt.getSubMap(MineTweakerMC.getBlock(input)).remove(MineTweakerMC.getItemStack(output.getStack()));
		}

		@Override
		public boolean canUndo() {
			return true;
		}

		@Override
		public String describe() {
			return "Adding auto sieve product " + output.getStack().getName() + " with a chance of " + output.getPercent() + " from " + input.getDisplayName();
		}

		@Override
		public String describeUndo() {
			return "Removing auto sieve product " + output.getStack().getName() + " from " + input.getDisplayName();
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}
	}

	private static class RemoveSieveProductAction implements IUndoableAction {
		private GregTech gt;
		private IBlock input;
		private IItemStack output;

		public RemoveSieveProductAction(GregTech gt, IBlock input, IItemStack output) {
			this.gt = gt;
			this.input = input;
			this.output = output;
		}

		@Override
		public void apply() {
			gt.getSubMap(MineTweakerMC.getBlock(input)).remove(MineTweakerMC.getItemStack(output));
		}

		@Override
		public boolean canUndo() {
			return false;
		}

		@Override
		public String describe() {
			return "Removing " + output.getName() + " from " + input.getDisplayName();
		}

		@Override
		public String describeUndo() {
			return "This action cannot be undone!";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}

		@Override
		public void undo() {}
	}
}
