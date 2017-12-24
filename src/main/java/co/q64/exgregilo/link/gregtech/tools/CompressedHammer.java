package co.q64.exgregilo.link.gregtech.tools;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.enums.SubTag;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Recipe.GT_Recipe_Map;
import gregtech.api.util.GT_Utility;
import gregtech.common.items.GT_MetaGenerated_Tool_01;
import gregtech.common.items.behaviors.Behaviour_Prospecting;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.world.BlockEvent;
import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.link.gregtech.recipe.CompressedHammerRecipes;
import co.q64.exgregilo.link.gregtech.recipe.SilkHammerRecipes;
import co.q64.exgregilo.link.gregtech.render.ItemTextures;
import co.q64.exgregilo.link.nei.NEI;

@Singleton
public class CompressedHammer extends CustomMetaTool {
	public static final int MAX_SPEED = 14;

	private @Inject LinkManager linkManager;
	private @Inject CompressedHammerRecipes chr;
	private @Inject SilkHammerRecipes shr;

	public void addCrafting() {
		boolean useNEI = linkManager.isEnabled(NEI.class);
		for (Materials material : Materials.values()) {
			if (material.contains(SubTag.METAL) && material.mDurability > 0) {
				ItemStack result = linkManager.getLink(GregTech.class).getTools().getToolWithStats(MetaGeneratedTools.COMPRESSED_HAMMER_ID, 1, material, material, null);
				//formatter:off
				boolean added = GT_ModHandler.addCraftingRecipe(result, GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{
				"HHH", 
				"HHH", 
				"HHH", 
				Character.valueOf('H'), GT_MetaGenerated_Tool.sInstances.get("gt.metatool.01").getToolWithStats(GT_MetaGenerated_Tool_01.HARDHAMMER, 1, material, material, null)});
				//formatter:on
				if (added && useNEI) {
					linkManager.getLink(NEI.class).addItemVariant(linkManager.getLink(GregTech.class).getTools(), result);
				}
			}
		}
	}

	@Override
	public Class<? extends IIconContainer> getTextureClass() {
		return ItemTextures.class;
	}

	@Override
	public IIconContainer getIcon(boolean isToolHead, ItemStack stack) {
		return ItemTextures.COMPRESSED_HAMMER;
	}

	public int getToolDamagePerBlockBreak() {
		return 50;
	}

	public int getToolDamagePerDropConversion() {
		return 200;
	}

	public int getToolDamagePerContainerCraft() {
		return 400;
	}

	public int getToolDamagePerEntityAttack() {
		return 200;
	}

	public int getBaseQuality() {
		return 0;
	}

	public float getBaseDamage() {
		return 3.0F;
	}

	public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity) {
		return aOriginalHurtResistance * 2;
	}

	public float getSpeedMultiplier() {
		return 0.75F;
	}

	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}

	public String getCraftingSound() {
		return (String) GregTech_API.sSoundList.get(Integer.valueOf(1));
	}

	public String getEntityHitSound() {
		return null;
	}

	public String getBreakingSound() {
		return (String) GregTech_API.sSoundList.get(Integer.valueOf(2));
	}

	public String getMiningSound() {
		return null;
	}

	public boolean canBlock() {
		return true;
	}

	public boolean isCrowbar() {
		return false;
	}

	public boolean isWeapon() {
		return true;
	}

	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		if (tTool != null) {
			if (tTool.equals("hammer") || (tTool.equals("pickaxe"))) {
				return true;
			}
		}
		return (aBlock.getMaterial() == Material.rock) || (aBlock == Blocks.gravel) || (aBlock == Blocks.sand) | (chr.findRecipe(null, true, 2147483647L, null, new ItemStack[] { new ItemStack(aBlock, 1, aMetaData) }) != null);
	}

	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		GT_Recipe_Map target = aSilkTouch ? shr : chr;
		int rConversions = 0;
		GT_Recipe tRecipe = target.findRecipe(null, true, 2147483647L, null, new ItemStack[] { new ItemStack(aBlock, 1, aMetaData) });
		if ((tRecipe == null) || (aBlock.hasTileEntity(aMetaData))) {
			for (ItemStack tDrop : aDrops) {
				tRecipe = target.findRecipe(null, true, 2147483647L, null, new ItemStack[] { GT_Utility.copyAmount(1L, new Object[] { tDrop }) });
				if (tRecipe != null) {
					ItemStack tHammeringOutput = tRecipe.getOutput(0);
					if (tHammeringOutput != null) {
						rConversions += tDrop.stackSize;
						tDrop.stackSize *= tHammeringOutput.stackSize;
						tHammeringOutput.stackSize = tDrop.stackSize;
						GT_Utility.setStack(tDrop, tHammeringOutput);
					}
				}
			}
		} else {
			aDrops.clear();
			aDrops.add(tRecipe.getOutput(0));
			rConversions++;
		}
		return rConversions;
	}

	public ItemStack getBrokenItem(ItemStack aStack) {
		return null;
	}

	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mRGBa : GT_MetaGenerated_Tool.getSecondaryMaterial(aStack).mRGBa;
	}

	public void onStatsAddedToTool(GT_MetaGenerated_Tool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behaviour_Prospecting(1, 1000));
	}

	public IChatComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity) {
		return new ChatComponentText(EnumChatFormatting.RED + aEntity.getCommandSenderName() + EnumChatFormatting.WHITE + " was squashed by " + EnumChatFormatting.GREEN + aPlayer.getCommandSenderName() + EnumChatFormatting.WHITE);
	}
}
