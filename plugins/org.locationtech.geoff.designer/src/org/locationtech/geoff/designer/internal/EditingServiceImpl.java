package org.locationtech.geoff.designer.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.Identifiable;
import org.locationtech.geoff.designer.IEditingService;
import org.locationtech.geoff.layer.Layer;

public class EditingServiceImpl implements IEditingService {
	@Inject
	private EditingDomain editingDomain;

	@Inject
	private GeoMap geoMap;

	@Override
	public Command createDeleteCommand(Identifiable id) {
		Command deleteCommand = DeleteCommand.create(editingDomain, id);
		return deleteCommand;
	}

	@Override
	public void execute(Command command) {
		editingDomain.getCommandStack().execute(command);
	}

	@Override
	public Collection<? extends Identifiable> samplesOf(EClass eclass) {
		EPackage root = eclass.getEPackage();
		EPackage current = root;

		while ((current = root.getESuperPackage()) != null) {
			root = current;
		}

		Collection<Identifiable> ret = new ArrayList<>();
		Collection<EClass> eclasses = new ArrayList<>();
		collectSubtypes(eclasses, eclass, root);

		for (EClass ec : eclasses) {
			if (!GeoffPackage.Literals.IDENTIFIABLE.isSuperTypeOf(ec)) {
				continue;
			}

			Identifiable eo = (Identifiable) EcoreUtil.create(ec);
			ret.add(eo);
		}

		return ret;
	}

	private void collectSubtypes(Collection<EClass> eclasses, EClass eclass,
			EPackage root) {
		for (EClassifier eClassifier : root.getEClassifiers()) {
			if (!(eClassifier instanceof EClass)) {
				continue;
			}

			EClass subType = (EClass) eClassifier;

			if (subType.isAbstract()) {
				continue;
			}

			if (eclass.isSuperTypeOf(subType)) {
				eclasses.add(subType);
			}
		}

		for (EPackage subPackage : root.getESubpackages()) {
			collectSubtypes(eclasses, eclass, subPackage);
		}
	}

	@Override
	public Command createAddCommand(GeoMap geoMap, EReference ref,
			Identifiable id) {
		Command command = AddCommand.create(editingDomain, geoMap, ref, id);
		return command;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Identifiable createInstance(EClass ec) {
		if (ec == null) {
			throw new IllegalArgumentException(
					"The provided type must not be null.");
		}

		if (!GeoffPackage.Literals.IDENTIFIABLE.isSuperTypeOf(ec)) {
			throw new IllegalArgumentException(
					"Only creation of instances of Identifiable are supported.");
		}

		if (ec.isAbstract()) {
			throw new IllegalArgumentException(
					"Abstract classes cannot be instantiated.");
		}

		Identifiable ret = (Identifiable) EcoreUtil.create(ec);
		String id = UUID.randomUUID().toString().toUpperCase();
		ret.setId(id);
		return ret;
	}

	@Override
	public void addLayer(Layer layer) {
		Command addLayerCommand = createAddCommand(geoMap,
				GeoffPackage.Literals.GEO_MAP__LAYERS, layer);
		execute(addLayerCommand);
	}
}