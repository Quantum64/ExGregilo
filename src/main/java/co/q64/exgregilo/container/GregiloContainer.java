package co.q64.exgregilo.container;

import java.util.Collections;
import java.util.List;

import co.q64.exgregilo.api.link.LinkBase;
import co.q64.exgregilo.util.RequireList;

public abstract class GregiloContainer {
	public abstract List<Class<? extends LinkBase>> getRequired();

	public RequireList<Class<? extends LinkBase>> require(Class<? extends LinkBase> clazz) {
		RequireList<Class<? extends LinkBase>> list = new RequireList<Class<? extends LinkBase>>();
		list.add(clazz);
		return list;
	}

	public List<Class<? extends LinkBase>> requireNone() {
		return Collections.emptyList();
	}
}
