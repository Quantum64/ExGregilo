package co.q64.exgregilo.link.gregtech.tools;

import gregtech.api.enums.Materials;
import gregtech.api.interfaces.IToolStats;
import gregtech.api.items.GT_MetaGenerated_Tool;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import co.q64.exgregilo.link.gregtech.crafting.OreDictAddons;

@Singleton
public class MetaGeneratedTools extends GT_MetaGenerated_Tool {
	public static final int WIRE_MESH_ID = 0;
	public static final int COMPRESSED_HAMMER_ID = 2;

	private @Inject WireMesh wireMesh;
	private @Inject CompressedHammer compressedHammer;

	public MetaGeneratedTools() {
		super("exgregilo.metatool.01");
	}

	@Inject
	public void init() {
		addTool(WIRE_MESH_ID, "Wire Mesh", "Place in an Advanced Sieve", wireMesh, new Object[] { OreDictAddons.WIRE_MESH });
		addTool(COMPRESSED_HAMMER_ID, "Compressed Hammer", "Hammer compressed blocks", compressedHammer, new Object[] {});
	}

	public void addCrafting() {
		wireMesh.addCrafting();
		compressedHammer.addCrafting();
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
			String name = stack.getUnlocalizedName();
			if (name.equals(getUnlocalizedName() + "." + WIRE_MESH_ID)) {
				list.add(tOffset + 0, EnumChatFormatting.WHITE + "Durability: " + EnumChatFormatting.GREEN + (maxDamage - getToolDamage(stack)) + " / " + maxDamage + EnumChatFormatting.GRAY);
				list.add(tOffset + 1, EnumChatFormatting.WHITE + material.mDefaultLocalName + EnumChatFormatting.YELLOW + " lvl " + getHarvestLevel(stack, "") + EnumChatFormatting.GRAY);
				list.add(tOffset + 2, EnumChatFormatting.WHITE + "Clicks Required: " + EnumChatFormatting.LIGHT_PURPLE + ((int) Math.ceil(Math.max(1, stats.getSpeedMultiplier() * (WireMesh.MAX_SPEED - getPrimaryMaterial(stack).mToolSpeed)))) + EnumChatFormatting.GRAY);
				return;
			}
		}
		super.addAdditionalToolTips(list, stack, player);
	}
}
