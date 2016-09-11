package co.q64.exgregilo.api.links;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface ModLink {
	public String modName();
	public String modId();
}
