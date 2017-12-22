package co.q64.exgregilo.api;

import co.q64.exgregilo.ExGregilo;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;

/**
 * If you want to interact with my mod you need to do it here.
 * 
 * There are only two ways to interact with my mod, but they are both very powerful:
 *  - You can either install your own module, which can swap out any of the dependencies
 *    or constants in my mod. You need to do this before the injector is created during
 *    preinit, so make sure this mod loads after yours and you do the installation before
 *    my mod does preinit.
 *    
 *  - You can request that your object is injected. My injector will try to fill any
 *    fields annotated @Inject with my objects. If you want to register your own ExGregilo
 *    link, then you need the LinkManager. You can get it using this interaction method
 *    by creating a class with a field such as "private @Inject LinkManager linkManager;"
 *    then creating an instance of that class and passing it to ExGregiloAPI.inject(yourClass);
 *    After this method executes on your class, your linkManager field will be populated
 *    (as well as all other fields with a valid injection target and annotation), and be
 *    ready to use for registering your own link or accessing other links. This method only
 *    works after preinit. Make sure you are using javax.inject.Inject for your annotation import.
 *    
 *  DO NOT TRY TO CREATE INSTANCES OF MY CLASSES. IT WILL NOT WORK. INTERACT WITH
 *  EXGREGILO USING ONLY THESE TWO METHODS. Thanks. - Q64
 *
 */
public abstract class ExGregiloAPI {
	public static void install(ExGregiloModule module) {
		ExGregilo mod = getMod();
		if (mod == null) {
			return;
		}
		mod.install(module);
	}

	public static void inject(Object object) {
		ExGregilo mod = getMod();
		if (mod == null) {
			return;
		}
		mod.inject(object);
	}

	private static ExGregilo getMod() {
		for (ModContainer mc : Loader.instance().getModList()) {
			if (ExGregilo.class.equals(mc.getMod().getClass())) {
				return ExGregilo.class.cast(mc.getMod());
			}
		}
		return null;
	}
}
