package co.q64.exgregilo.container.blocks;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import co.q64.exgregilo.api.binders.ModDataBinders.BasicSieveBlockName;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.block.BasicSieve;
import co.q64.exgregilo.block.BasicSieveItemBlock;
import co.q64.exgregilo.container.GregiloBlockContainer;
import co.q64.exgregilo.link.exnihilo.ExNihilo;
import co.q64.exgregilo.link.gregtech.GregTech;

@Singleton
public class BasicSieveContainer extends GregiloBlockContainer {
	private @Inject Provider<BasicSieve> provider;
	private @Inject @BasicSieveBlockName String name;

	@Override
	public Provider<? extends Block> getBlockProvider() {
		return provider;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlock() {
		return BasicSieveItemBlock.class;
	}

	@Override
	public List<Class<? extends LinkBase>> getRequired() {
		return require(GregTech.class).require(ExNihilo.class);
	}
}
