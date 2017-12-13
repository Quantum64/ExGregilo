package co.q64.exgregilo.api.link;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface ModLink {
	public String modName();

	public String modId();
}
