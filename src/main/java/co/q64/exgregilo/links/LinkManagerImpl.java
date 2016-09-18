package co.q64.exgregilo.links;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import co.q64.exgregilo.api.ExGregiloAPI;
import co.q64.exgregilo.api.links.LinkBase;
import co.q64.exgregilo.api.links.LinkManager;
import co.q64.exgregilo.api.links.MalformedLinkException;
import co.q64.exgregilo.api.links.ModLink;
import cpw.mods.fml.common.Loader;

public class LinkManagerImpl implements LinkManager {
	private Map<Class<? extends LinkBase>, ModLink> pendingLinks = new HashMap<Class<? extends LinkBase>, ModLink>();
	private List<LinkBase> enabledLinks = new ArrayList<LinkBase>();
	private boolean enabled;

	@Override
	public void registerLink(Class<? extends LinkBase> linkClass) {
		if (enabled) {
			throw new IllegalStateException("Links need to be registered in PreLoad");
		}
		ModLink link = getModLink(linkClass);
		if (link == null) {
			throw new MalformedLinkException("The main link class must contain the ModLink annotation");
		}
		pendingLinks.put(linkClass, link);
	}

	@Override
	public boolean isEnabled(Class<? extends LinkBase> linkClass) {
		for (LinkBase link : enabledLinks) {
			if (link.getClass().equals(linkClass)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public <T> T getLink(Class<T> linkClass) {
		for (LinkBase link : enabledLinks) {
			if (link.getClass().isAssignableFrom(linkClass)) {
				return linkClass.cast(link);
			}
		}
		return null;
	}

	public void enableLinks() {
		for (LinkBase link : enabledLinks) {
			link.postLoadLink();
		}
		for (LinkBase link : enabledLinks) {
			link.afterPostLoadLink();
		}
	}

	public void loadLinks() {
		if (enabled) {
			throw new IllegalStateException("Links have already been enabled!");
		}
		for (Entry<Class<? extends LinkBase>, ModLink> e : pendingLinks.entrySet()) {
			boolean loaded = Loader.isModLoaded(e.getValue().modId());
			ExGregiloAPI.getProxy().getLogger().info("ExGregilo " + (loaded ? "linked to " : "did not link to ") + e.getValue().modName());
			if (loaded) {
				try {
					enabledLinks.add(e.getKey().newInstance());
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
		}
		pendingLinks.clear();
		for (LinkBase link : enabledLinks) {
			link.loadLink(); // TODO Move if this doesn't work
		}
		enabled = true;
	}

	@Override
	public ModLink getModLink(Class<? extends LinkBase> linkClass) {
		return linkClass.getAnnotation(ModLink.class);
	}
}
