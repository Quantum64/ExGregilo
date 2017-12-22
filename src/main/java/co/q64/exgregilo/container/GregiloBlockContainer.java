package co.q64.exgregilo.container;

import javax.inject.Provider;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public abstract class GregiloBlockContainer extends GregiloContainer {
	private Block block;
	private boolean registered;

	public abstract Provider<? extends Block> getBlockProvider();

	public abstract String getName();

	public Class<? extends ItemBlock> getItemBlock() {
		return null;
	}

	public void afterRegister() {}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = true;
	}
}
