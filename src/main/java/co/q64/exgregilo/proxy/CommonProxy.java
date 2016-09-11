package co.q64.exgregilo.proxy;

import org.apache.logging.log4j.Logger;

public abstract class CommonProxy {
	private Logger logger;

	public void setLogger(Logger logger) {
		if (getLogger() != null) {
			getLogger().error("You cannot set the logger again!");
		}
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}
}
