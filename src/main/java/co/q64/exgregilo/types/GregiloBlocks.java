package co.q64.exgregilo.types;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import co.q64.exgregilo.api.ExGregiloAPI;
import co.q64.exgregilo.api.links.LinkBase;
import co.q64.exgregilo.block.AdvancedSieve;
import co.q64.exgregilo.block.AdvancedSieveItemBlock;
import co.q64.exgregilo.links.exnihilo.ExNihilo;
import co.q64.exgregilo.links.gregtech.GregTech;
import cpw.mods.fml.common.registry.GameRegistry;

public enum GregiloBlocks {
	@SuppressWarnings("unchecked")
	ADVANCED_SIEVE(AdvancedSieve.BLOCK_NAME, new BlockProvider() {
		public Block newBlock() {
			return new AdvancedSieve();
		}
	}, AdvancedSieveItemBlock.class, Arrays.asList(ExNihilo.class, GregTech.class));

	private String name;
	private BlockProvider block;
	private Class<? extends ItemBlock> itemBlock;
	private List<Class<? extends LinkBase>> required;
	private boolean registered;
	private Block realBlock;

	private GregiloBlocks(String name, BlockProvider block, Class<? extends ItemBlock> itemBlock, List<Class<? extends LinkBase>> required) {
		this.name = name;
		this.block = block;
		this.itemBlock = itemBlock;
		this.required = required;
	}

	private GregiloBlocks(String name, BlockProvider block, List<Class<? extends LinkBase>> required) {
		this.name = name;
		this.block = block;
		this.required = required;
	}

	public BlockProvider getBlock() {
		return block;
	}

	public String getName() {
		return name;
	}

	public Class<? extends ItemBlock> getItemBlock() {
		return itemBlock;
	}

	public List<Class<? extends LinkBase>> getRequired() {
		return required;
	}

	public Block getRealBlock() {
		return realBlock;
	}

	public boolean isRegistered() {
		return registered;
	}

	public static void registerBlocks() {
		for (GregiloBlocks b : GregiloBlocks.class.getEnumConstants()) {
			for (Class<? extends LinkBase> link : b.getRequired()) {
				if (!ExGregiloAPI.getLinkManager().isEnabled(link)) {
					continue;
				}
			}
			b.realBlock = b.getBlock().newBlock();
			b.registered = true;
			if (b.getItemBlock() == null) {
				GameRegistry.registerBlock(b.getRealBlock(), b.getName());
			} else {
				GameRegistry.registerBlock(b.getRealBlock(), b.getItemBlock(), b.getName());
			}
		}
	}

	private static interface BlockProvider {
		public Block newBlock();
	}
}
