package org.locationtech.geoff.e4.utils.fragments.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.MApplicationElement;
import org.eclipse.e4.ui.model.fragment.MStringModelFragment;
import org.eclipse.e4.ui.model.internal.ModelUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.locationtech.geoff.e4.utils.fragments.FragmentBuilder;
import org.locationtech.geoff.e4.utils.fragments.ModelFragmentsProvider;

@SuppressWarnings("restriction")
public class FragmentHelper {
	private static Logger LOG = Logger.getLogger(FragmentHelper.class.getName());

	public static void process(Object targetInstance, IEclipseContext ctx, MApplication app) {
		Method[] methods = targetInstance.getClass().getMethods();
		List<MApplicationElement> merged = new ArrayList<MApplicationElement>();

		for (Method method : methods) {
			Class<?>[] parameterTypes = method.getParameterTypes();

			if (parameterTypes.length == 0) {
				continue;
			}

			boolean matched = false;

			for (Class<?> class1 : parameterTypes) {
				if (class1 == FragmentBuilder.class) {
					matched = true;
					break;
				}
			}

			if (!matched) {
				continue;
			}

			processMethod(targetInstance, method, app, merged);
		}
		processCrossRefs(app, merged);
	}

	private static void processCrossRefs(MApplication app, List<MApplicationElement> merged) {
		Map<EObject, Collection<Setting>> find = EcoreUtil.CrossReferencer.find(merged);

		if (find.isEmpty()) {
			return;
		}

		for (Entry<EObject, Collection<Setting>> e : find.entrySet()) {
			EObject crossRef = e.getKey();

			if (!(crossRef instanceof MApplicationElement)) {
				continue;
			}

			MApplicationElement appElement = (MApplicationElement) crossRef;
			String elementId = appElement.getElementId();
			MApplicationElement referencedObject = ModelUtils.findElementById(app, elementId);

			if (referencedObject == null) {
				LOG.log(Level.WARNING, "Application element not found: " + elementId);
			}

			for (Setting setting : e.getValue()) {
				EObject referencingObject = setting.getEObject();
				EStructuralFeature feature = setting.getEStructuralFeature();
				referencingObject.eSet(feature, referencedObject);
			}
		}
	}

	private static void processMethod(Object targetInstance, Method method, MApplication app,
			List<MApplicationElement> merged) {
		FragmentBuilder builder = FragmentBuilder.create();

		try {
			method.invoke(targetInstance, builder);
		} catch (Exception e) {
			e.printStackTrace();
		}

		MStringModelFragment fragment = builder.get();

		if (ModelFragmentsProvider.ANY_APPLICATION.equals(fragment.getParentElementId())) {
			fragment.setParentElementId(app.getElementId());
		}

		List<MApplicationElement> merge = fragment.merge(app);
		merged.addAll(merge);
	}
}
