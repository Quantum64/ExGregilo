package co.q64.exgregilo.container;

import javax.inject.Provider;

import net.minecraft.item.Item;

public abstract class GregiloItemContainer extends GregiloContainer {
	private Item item;
	private boolean registered;

	public abstract Provider<? extends Item> getItemProvider();

	public abstract String getName();

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = true;
	}
}
