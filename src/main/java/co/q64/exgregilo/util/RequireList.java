package co.q64.exgregilo.util;

import java.util.ArrayList;

public class RequireList<T> extends ArrayList<T> {
	private static final long serialVersionUID = 1L;

	public RequireList<T> require(T t) {
		add(t);
		return this;
	}
}
