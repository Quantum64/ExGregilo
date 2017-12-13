package co.q64.exgregilo.types;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.Item;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.function.ItemProvider;
import co.q64.exgregilo.item.GemShards;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.GregTech;

public enum GregiloItems {
	@SuppressWarnings("unchecked")
	GEM_SHARDS("gem_shards", new ItemProvider() {

		@Override
		public Item newItem() {
			return new GemShards();
		}
	}, Arrays.asList(GregTech.class, ExNihilo.class));

	private String name;
	private ItemProvider item;
	private List<Class<? extends LinkBase>> required;
	private boolean registered;
	private Item realItem;

	private GregiloItems(String name, ItemProvider item, List<Class<? extends LinkBase>> required) {
		this.name = name;
		this.item = item;
		this.required = required;
	}

	public ItemProvider getItem() {
		return item;
	}

	public String getName() {
		return name;
	}

	public List<Class<? extends LinkBase>> getRequired() {
		return required;
	}

	public Item getRealItem() {
		return realItem;
	}

	public void setRealItem(Item block) {
		this.realItem = block;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
}
