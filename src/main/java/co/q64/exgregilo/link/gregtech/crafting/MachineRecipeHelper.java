package co.q64.exgregilo.link.gregtech.crafting;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.Tier;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_GT_Recipe.X;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_ModHandler.RecipeBits;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.Logger;

import co.q64.exgregilo.api.link.LinkManager;
import co.q64.exgregilo.link.gregtech.GregTech;
import co.q64.exgregilo.link.gregtech.tools.MetaGeneratedTools;

@Singleton
public class MachineRecipeHelper {
	private @Inject Logger logger;
	private @Inject LinkManager linkManager;

	public void addMachineRecipe(MetaTileEntity tile, int mTier, Object[] aRecipe) {
		for (int i = 3; i < aRecipe.length; i++) {
			if (aRecipe[i] == X.CIRCUIT) {
				aRecipe[i] = Tier.ELECTRIC[mTier].mManagingObject;
				continue;
			}
			if (aRecipe[i] == X.BETTER_CIRCUIT) {
				aRecipe[i] = Tier.ELECTRIC[mTier].mBetterManagingObject;
				continue;
			}
			if (aRecipe[i] == X.HULL) {
				aRecipe[i] = Tier.ELECTRIC[mTier].mHullObject;
				continue;
			}
			if (aRecipe[i] == X.WIRE) {
				aRecipe[i] = Tier.ELECTRIC[mTier].mConductingObject;
				continue;
			}
			if (aRecipe[i] == X.WIRE4) {
				aRecipe[i] = Tier.ELECTRIC[mTier].mLargerConductingObject;
				continue;
			}

			if (aRecipe[i] == X.GLASS) {
				switch (mTier) {
				case 6:
				case 7:
				case 8:
					//aRecipe[i] = Ic2Items.reinforcedGlass; // Don't have this api installed
					break;
				default:
					aRecipe[i] = new ItemStack(Blocks.glass, 1, GT_Values.W);
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.PLATE) {
				switch (mTier) {
				case 1:
					aRecipe[i] = OrePrefixes.plate.get(Materials.Steel);
					break;
				case 2:
					aRecipe[i] = OrePrefixes.plate.get(Materials.Aluminium);
					break;
				case 3:
					aRecipe[i] = OrePrefixes.plate.get(Materials.StainlessSteel);
					break;
				case 4:
					aRecipe[i] = OrePrefixes.plate.get(Materials.Titanium);
					break;
				case 5:
					aRecipe[i] = OrePrefixes.plate.get(Materials.TungstenSteel);
					break;
				case 6:
					//aRecipe[i] = OrePrefixes.plate.get(Materials.HSSG);
					aRecipe[i] = OrePrefixes.plate.get(Materials.TungstenSteel);
					break;
				case 7:
					//aRecipe[i] = OrePrefixes.plate.get(Materials.HSSE);
					aRecipe[i] = OrePrefixes.plate.get(Materials.TungstenSteel);
					break;
				case 8:
					aRecipe[i] = OrePrefixes.plate.get(Materials.Neutronium);
					break;
				default:
					aRecipe[i] = OrePrefixes.plate.get(Materials.TungstenSteel);
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.PIPE) {
				switch (mTier) {
				case 1:
					aRecipe[i] = OrePrefixes.pipeMedium.get(Materials.Bronze);
					break;
				case 2:
					aRecipe[i] = OrePrefixes.pipeMedium.get(Materials.Steel);
					break;
				case 3:
					aRecipe[i] = OrePrefixes.pipeMedium.get(Materials.StainlessSteel);
					break;
				case 4:
					aRecipe[i] = OrePrefixes.pipeMedium.get(Materials.Titanium);
					break;
				case 5:
					aRecipe[i] = OrePrefixes.pipeMedium.get(Materials.TungstenSteel);
					break;
				case 6:
					aRecipe[i] = OrePrefixes.pipeSmall.get(Materials.Ultimate);
					break;
				case 7:
					aRecipe[i] = OrePrefixes.pipeMedium.get(Materials.Ultimate);
					break;
				case 8:
					aRecipe[i] = OrePrefixes.pipeLarge.get(Materials.Ultimate);
					break;
				default:
					aRecipe[i] = OrePrefixes.pipeMedium.get(Materials.TungstenSteel);
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.COIL_HEATING) {
				switch (mTier) {
				case 1:
					aRecipe[i] = OrePrefixes.wireGt02.get(Materials.AnyCopper);
					break;
				case 2:
					aRecipe[i] = OrePrefixes.wireGt02.get(Materials.Cupronickel);
					break;
				case 3:
					aRecipe[i] = OrePrefixes.wireGt02.get(Materials.Kanthal);
					break;
				case 4:
					aRecipe[i] = OrePrefixes.wireGt02.get(Materials.Nichrome);
					break;
				case 5:
					aRecipe[i] = OrePrefixes.wireGt02.get(Materials.TungstenSteel);
					break;
				case 6:
					//aRecipe[i] = OrePrefixes.wireGt02.get(Materials.HSSG);
					aRecipe[i] = OrePrefixes.plate.get(Materials.TungstenSteel);
					break;
				case 7:
					aRecipe[i] = OrePrefixes.wireGt02.get(Materials.Naquadah);
					break;
				case 8:
					aRecipe[i] = OrePrefixes.wireGt02.get(Materials.NaquadahAlloy);
					break;
				default:
					aRecipe[i] = OrePrefixes.wireGt08.get(Materials.Nichrome);
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.COIL_HEATING_DOUBLE) {
				switch (mTier) {
				case 1:
					aRecipe[i] = OrePrefixes.wireGt04.get(Materials.AnyCopper);
					break;
				case 2:
					aRecipe[i] = OrePrefixes.wireGt04.get(Materials.Cupronickel);
					break;
				case 3:
					aRecipe[i] = OrePrefixes.wireGt04.get(Materials.Kanthal);
					break;
				case 4:
					aRecipe[i] = OrePrefixes.wireGt04.get(Materials.Nichrome);
					break;
				case 5:
					aRecipe[i] = OrePrefixes.wireGt04.get(Materials.TungstenSteel);
					break;
				case 6:
					//aRecipe[i] = OrePrefixes.wireGt04.get(Materials.HSSG);
					aRecipe[i] = OrePrefixes.plate.get(Materials.TungstenSteel);
					break;
				case 7:
					aRecipe[i] = OrePrefixes.wireGt04.get(Materials.Naquadah);
					break;
				case 8:
					aRecipe[i] = OrePrefixes.wireGt04.get(Materials.NaquadahAlloy);
					break;
				default:
					aRecipe[i] = OrePrefixes.wireGt16.get(Materials.Nichrome);
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.STICK_DISTILLATION) {
				switch (mTier) {
				default:
					aRecipe[i] = OrePrefixes.stick.get(Materials.Blaze);
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.STICK_MAGNETIC) {
				switch (mTier) {
				case 1:
					aRecipe[i] = OrePrefixes.stick.get(Materials.IronMagnetic);
					break;
				case 2:
				case 3:
					aRecipe[i] = OrePrefixes.stick.get(Materials.SteelMagnetic);
					break;
				case 4:
				case 5:
					aRecipe[i] = OrePrefixes.stick.get(Materials.NeodymiumMagnetic);
					break;
				case 6:
				case 7:
					aRecipe[i] = OrePrefixes.stickLong.get(Materials.NeodymiumMagnetic);
					break;
				default:
					aRecipe[i] = OrePrefixes.block.get(Materials.NeodymiumMagnetic);
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.STICK_ELECTROMAGNETIC) {
				switch (mTier) {
				case 1:
					aRecipe[i] = OrePrefixes.stick.get(Materials.AnyIron);
					break;
				case 2:
				case 3:
					aRecipe[i] = OrePrefixes.stick.get(Materials.Steel);
					break;
				case 4:
					aRecipe[i] = OrePrefixes.stick.get(Materials.Neodymium);
					break;
				default:
					aRecipe[i] = OrePrefixes.stick.get(Materials.VanadiumGallium);
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.COIL_ELECTRIC) {
				switch (mTier) {
				case 1:
					aRecipe[i] = OrePrefixes.wireGt02.get(Materials.Tin);
					break;
				case 2:
					aRecipe[i] = OrePrefixes.wireGt02.get(Materials.AnyCopper);
					break;
				case 3:
					aRecipe[i] = OrePrefixes.wireGt04.get(Materials.AnyCopper);
					break;
				case 4:
					aRecipe[i] = OrePrefixes.wireGt08.get(Materials.AnnealedCopper);
					break;
				case 5:
					aRecipe[i] = OrePrefixes.wireGt08.get(Materials.AnnealedCopper);
					break;
				case 6:
					aRecipe[i] = OrePrefixes.wireGt04.get(Materials.YttriumBariumCuprate);
					break;
				case 7:
					aRecipe[i] = OrePrefixes.wireGt08.get(Materials.Superconductor);
					break;
				default:
					aRecipe[i] = OrePrefixes.wireGt16.get(Materials.Superconductor);
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.ROBOT_ARM) {
				switch (mTier) {
				case 1:
					aRecipe[i] = ItemList.Robot_Arm_LV;
					break;
				case 2:
					aRecipe[i] = ItemList.Robot_Arm_MV;
					break;
				case 3:
					aRecipe[i] = ItemList.Robot_Arm_HV;
					break;
				case 4:
					aRecipe[i] = ItemList.Robot_Arm_EV;
					break;
				case 5:
					aRecipe[i] = ItemList.Robot_Arm_IV;
					break;
				case 6:
					aRecipe[i] = ItemList.Robot_Arm_LuV;
					break;
				case 7:
					aRecipe[i] = ItemList.Robot_Arm_ZPM;
					break;
				default:
					aRecipe[i] = ItemList.Robot_Arm_UV;
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.PUMP) {
				switch (mTier) {
				case 1:
					aRecipe[i] = ItemList.Electric_Pump_LV;
					break;
				case 2:
					aRecipe[i] = ItemList.Electric_Pump_MV;
					break;
				case 3:
					aRecipe[i] = ItemList.Electric_Pump_HV;
					break;
				case 4:
					aRecipe[i] = ItemList.Electric_Pump_EV;
					break;
				case 5:
					aRecipe[i] = ItemList.Electric_Pump_IV;
					break;
				case 6:
					aRecipe[i] = ItemList.Electric_Pump_LuV;
					break;
				case 7:
					aRecipe[i] = ItemList.Electric_Pump_ZPM;
					break;
				default:
					aRecipe[i] = ItemList.Electric_Pump_UV;
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.ROTOR) {
				switch (mTier) {
				case 1:
					aRecipe[i] = OrePrefixes.rotor.get(Materials.Tin);
					break;
				case 2:
					aRecipe[i] = OrePrefixes.rotor.get(Materials.Bronze);
					break;
				case 3:
					aRecipe[i] = OrePrefixes.rotor.get(Materials.Steel);
					break;
				case 4:
					aRecipe[i] = OrePrefixes.rotor.get(Materials.StainlessSteel);
					break;
				case 5:
					aRecipe[i] = OrePrefixes.rotor.get(Materials.TungstenSteel);
					break;
				case 6:
					aRecipe[i] = OrePrefixes.rotor.get(Materials.Chrome);
					break;
				case 7:
					aRecipe[i] = OrePrefixes.rotor.get(Materials.Iridium);
					break;
				default:
					aRecipe[i] = OrePrefixes.rotor.get(Materials.Osmium);
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.MOTOR) {
				switch (mTier) {
				case 1:
					aRecipe[i] = ItemList.Electric_Motor_LV;
					break;
				case 2:
					aRecipe[i] = ItemList.Electric_Motor_MV;
					break;
				case 3:
					aRecipe[i] = ItemList.Electric_Motor_HV;
					break;
				case 4:
					aRecipe[i] = ItemList.Electric_Motor_EV;
					break;
				case 5:
					aRecipe[i] = ItemList.Electric_Motor_IV;
					break;
				case 6:
					aRecipe[i] = ItemList.Electric_Motor_LuV;
					break;
				case 7:
					aRecipe[i] = ItemList.Electric_Motor_ZPM;
					break;
				default:
					aRecipe[i] = ItemList.Electric_Motor_UV;
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.PISTON) {
				switch (mTier) {
				case 1:
					aRecipe[i] = ItemList.Electric_Piston_LV;
					break;
				case 2:
					aRecipe[i] = ItemList.Electric_Piston_MV;
					break;
				case 3:
					aRecipe[i] = ItemList.Electric_Piston_HV;
					break;
				case 4:
					aRecipe[i] = ItemList.Electric_Piston_EV;
					break;
				case 5:
					aRecipe[i] = ItemList.Electric_Piston_IV;
					break;
				case 6:
					aRecipe[i] = ItemList.Electric_Piston_LuV;
					break;
				case 7:
					aRecipe[i] = ItemList.Electric_Piston_ZPM;
					break;
				default:
					aRecipe[i] = ItemList.Electric_Piston_UV;
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.CONVEYOR) {
				switch (mTier) {
				case 1:
					aRecipe[i] = ItemList.Conveyor_Module_LV;
					break;
				case 2:
					aRecipe[i] = ItemList.Conveyor_Module_MV;
					break;
				case 3:
					aRecipe[i] = ItemList.Conveyor_Module_HV;
					break;
				case 4:
					aRecipe[i] = ItemList.Conveyor_Module_EV;
					break;
				case 5:
					aRecipe[i] = ItemList.Conveyor_Module_IV;
					break;
				case 6:
					aRecipe[i] = ItemList.Conveyor_Module_LuV;
					break;
				case 7:
					aRecipe[i] = ItemList.Conveyor_Module_ZPM;
					break;
				default:
					aRecipe[i] = ItemList.Conveyor_Module_UV;
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.EMITTER) {
				switch (mTier) {
				case 1:
					aRecipe[i] = ItemList.Emitter_LV;
					break;
				case 2:
					aRecipe[i] = ItemList.Emitter_MV;
					break;
				case 3:
					aRecipe[i] = ItemList.Emitter_HV;
					break;
				case 4:
					aRecipe[i] = ItemList.Emitter_EV;
					break;
				case 5:
					aRecipe[i] = ItemList.Emitter_IV;
					break;
				case 6:
					aRecipe[i] = ItemList.Emitter_LuV;
					break;
				case 7:
					aRecipe[i] = ItemList.Emitter_ZPM;
					break;
				default:
					aRecipe[i] = ItemList.Emitter_UV;
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.SENSOR) {
				switch (mTier) {
				case 1:
					aRecipe[i] = ItemList.Sensor_LV;
					break;
				case 2:
					aRecipe[i] = ItemList.Sensor_MV;
					break;
				case 3:
					aRecipe[i] = ItemList.Sensor_HV;
					break;
				case 4:
					aRecipe[i] = ItemList.Sensor_EV;
					break;
				case 5:
					aRecipe[i] = ItemList.Sensor_IV;
					break;
				case 6:
					aRecipe[i] = ItemList.Sensor_LuV;
					break;
				case 7:
					aRecipe[i] = ItemList.Sensor_ZPM;
					break;
				default:
					aRecipe[i] = ItemList.Sensor_UV;
					break;
				}
				continue;
			}

			if (aRecipe[i] == X.FIELD_GENERATOR) {
				switch (mTier) {
				case 1:
					aRecipe[i] = ItemList.Field_Generator_LV;
					break;
				case 2:
					aRecipe[i] = ItemList.Field_Generator_MV;
					break;
				case 3:
					aRecipe[i] = ItemList.Field_Generator_HV;
					break;
				case 4:
					aRecipe[i] = ItemList.Field_Generator_EV;
					break;
				case 5:
					aRecipe[i] = ItemList.Field_Generator_IV;
					break;
				case 6:
					aRecipe[i] = ItemList.Field_Generator_LuV;
					break;
				case 7:
					aRecipe[i] = ItemList.Field_Generator_ZPM;
					break;
				default:
					aRecipe[i] = ItemList.Field_Generator_UV;
					break;
				}
				continue;
			}

			if (aRecipe[i].equals(OreDictAddons.WIRE_MESH)) {
				aRecipe[i] = linkManager.getLink(GregTech.class).getTools().getMeshWithStats(MetaGeneratedTools.WIRE_MESH_ID, 1, Materials.Tin, Materials.Tin, null);
			}

			if (aRecipe[i] instanceof X)
				throw new IllegalArgumentException("MISSING TIER MAPPING FOR: " + aRecipe[i] + " AT TIER " + mTier);
		}

		if (!GT_ModHandler.addCraftingRecipe(tile.getStackForm(1), RecipeBits.DISMANTLEABLE | RecipeBits.BUFFERED | RecipeBits.NOT_REMOVABLE | RecipeBits.REVERSIBLE | RecipeBits.KEEPNBT, aRecipe)) {
			throw new IllegalArgumentException("INVALID CRAFTING RECIPE FOR: " + tile.getStackForm(1).getDisplayName());
		}
		logger.info("Added recipe: " + Arrays.asList(aRecipe));
	}
}
