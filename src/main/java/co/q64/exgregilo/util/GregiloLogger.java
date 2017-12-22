package co.q64.exgregilo.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.exgregilo.api.util.Logger;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Singleton
public class GregiloLogger implements Logger {
	public static final String PREFIX = "[ExGregilo] ";

	private @Inject FMLPreInitializationEvent event;

	public void info(String s) {
		event.getModLog().info(PREFIX + s);
	}

	@Override
	public void warn(String s) {
		event.getModLog().warn(PREFIX + s);
	}

	@Override
	public void error(String s) {
		event.getModLog().error(PREFIX + s);
	}
}
