package co.q64.exgregilo.container.blocks;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import co.q64.exgregilo.api.binders.ModDataBinders.CompressedBlockName;
import co.q64.exgregilo.api.binders.ModDataBinders.CompressedSandType;
import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.block.CompressedItemBlock;
import co.q64.exgregilo.block.CompressedSand;
import co.q64.exgregilo.container.GregiloBlockContainer;

@Singleton
public class CompressedSandContainer extends GregiloBlockContainer {
	private @Inject Provider<CompressedSand> provider;
	private @Inject @CompressedBlockName String name;
	private @Inject @CompressedSandType String sand;

	@Override
	public Provider<? extends Block> getBlockProvider() {
		return provider;
	}

	@Override
	public String getName() {
		return name + sand;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlock() {
		return CompressedItemBlock.class;
	}

	@Override
	public List<Class<? extends LinkBase>> getRequired() {
		return requireNone();
	}
}
