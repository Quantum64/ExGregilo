package co.q64.exgregilo.types;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.block.AdvancedSieve;
import co.q64.exgregilo.block.AdvancedSieveItemBlock;
import co.q64.exgregilo.block.GemSand;
import co.q64.exgregilo.function.BlockProvider;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.GregTech;

@SuppressWarnings("unchecked")
public enum GregiloBlocks {
	ADVANCED_SIEVE(AdvancedSieve.BLOCK_NAME, new BlockProvider() {
		public Block newBlock() {
			return new AdvancedSieve();
		}
	}, AdvancedSieveItemBlock.class, Arrays.asList(ExNihilo.class, GregTech.class)), GEM_SAND(GemSand.BLOCK_NAME, new BlockProvider() {

		@Override
		public Block newBlock() {
			return new GemSand();
		}
	}, Arrays.asList(ExNihilo.class, GregTech.class));

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

	public void setRealBlock(Block block) {
		this.realBlock = block;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
}
