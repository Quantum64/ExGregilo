package co.q64.exgregilo.link.gregtech.tools;

import gregtech.api.enums.Materials;
import gregtech.api.interfaces.IToolStats;
import gregtech.api.items.GT_MetaGenerated_Tool;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import co.q64.exgregilo.link.gregtech.crafting.OreDictAddons;

@Singleton
public class MetaGeneratedTools extends GT_MetaGenerated_Tool {
	public static final int WIRE_MESH_ID = 0;
	private @Inject WireMesh wireMesh;

	public MetaGeneratedTools() {
		super("exgregilo.metatool.01");
	}

	@Inject
	public void init() {
		addTool(WIRE_MESH_ID, "Wire Mesh", "Place in an Advanced Sieve", wireMesh, new Object[] { OreDictAddons.WIRE_MESH });
	}

	public void addCrafting() {
		wireMesh.addCrafting();
	}

	public WireMesh getWireMesh() {
		return wireMesh;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" /*Why it's like this, I have no idea*/})
	@Override
	public void addAdditionalToolTips(List list, ItemStack stack, EntityPlayer player) {
		long maxDamage = getToolMaxDamage(stack);
		Materials material = getPrimaryMaterial(stack);
		IToolStats stats = getToolStats(stack);
		int tOffset = getElectricStats(stack) != null ? 2 : 1;
		if (stats != null) {
			list.add(tOffset + 0, EnumChatFormatting.WHITE + "Durability: " + EnumChatFormatting.GREEN + (maxDamage - getToolDamage(stack)) + " / " + maxDamage + EnumChatFormatting.GRAY);
			list.add(tOffset + 1, EnumChatFormatting.WHITE + material.mDefaultLocalName + EnumChatFormatting.YELLOW + " lvl " + getHarvestLevel(stack, "") + EnumChatFormatting.GRAY);
			list.add(tOffset + 2, EnumChatFormatting.WHITE + "Clicks Required: " + EnumChatFormatting.LIGHT_PURPLE + ((int) Math.ceil(Math.max(1, stats.getSpeedMultiplier() * (WireMesh.MAX_SPEED - getPrimaryMaterial(stack).mToolSpeed)))) + EnumChatFormatting.GRAY);
		}
	}

	public ItemStack getMeshWithStats(int aToolID, int aAmount, Materials aPrimaryMaterial, Materials aSecondaryMaterial, long[] aElectricArray) {
		ItemStack rStack = new ItemStack(this, aAmount, aToolID);
		IToolStats tToolStats = getToolStats(rStack);
		if (tToolStats != null) {
			NBTTagCompound tMainNBT = new NBTTagCompound(), tToolNBT = new NBTTagCompound();
			if (aPrimaryMaterial != null) {
				tToolNBT.setString("PrimaryMaterial", aPrimaryMaterial.toString());
				tToolNBT.setLong("MaxDamage", (long) (100f * (aPrimaryMaterial.mDurability * tToolStats.getMaxDurabilityMultiplier())));
			}
			if (aSecondaryMaterial != null)
				tToolNBT.setString("SecondaryMaterial", aSecondaryMaterial.toString());

			if (aElectricArray != null) {
				tToolNBT.setBoolean("Electric", true);
				tToolNBT.setLong("MaxCharge", aElectricArray[0]);
				tToolNBT.setLong("Voltage", aElectricArray[1]);
				tToolNBT.setLong("Tier", aElectricArray[2]);
				tToolNBT.setLong("SpecialData", aElectricArray[3]);
			}

			tMainNBT.setTag("GT.ToolStats", tToolNBT);
			rStack.setTagCompound(tMainNBT);
		}
		isItemStackUsable(rStack);
		return rStack;
	}
}
