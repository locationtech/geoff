package org.locationtech.geoff.designer.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.emf.common.command.AbstractCommand;
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
import org.locationtech.geoff.ol.ResourcesUtil;

@SuppressWarnings("restriction")
public class EditingServiceImpl implements IEditingService {
	@Inject
	private Logger logger;

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

	private void collectSubtypes(Collection<EClass> eclasses, EClass eclass, EPackage root) {
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
	public Command createAddCommand(GeoMap geoMap, EReference ref, Identifiable id) {
		AddCommand command = (AddCommand) AddCommand.create(editingDomain, geoMap, ref, id);
		return command;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Identifiable createInstance(EClass ec) {
		if (ec == null) {
			throw new IllegalArgumentException("The provided type must not be null.");
		}

		if (!GeoffPackage.Literals.IDENTIFIABLE.isSuperTypeOf(ec)) {
			throw new IllegalArgumentException("Only creation of instances of Identifiable are supported.");
		}

		if (ec.isAbstract()) {
			throw new IllegalArgumentException("Abstract classes cannot be instantiated.");
		}

		Identifiable ret = (Identifiable) EcoreUtil.create(ec);
		// String id = UUID.randomUUID().toString().toUpperCase();
		// ret.setId(id);
		return ret;
	}

	@Override
	public void addLayer(Layer layer) {
		Command addLayerCommand = createAddCommand(geoMap, GeoffPackage.Literals.GEO_MAP__LAYERS, layer);
		execute(addLayerCommand);
	}

	@Override
	public Command createAddResourceCommand(final IFolder targetFolder, final String targetResourceName,
			final URL srcUrl, final boolean overwrite) {
		return new AbstractCommand("Add resource", "Adding resource " + targetResourceName) {

			@Override
			protected boolean prepare() {
				return true;
			}

			@Override
			public void redo() {
				execute();
			}

			@Override
			public void execute() {
				try {
					IFile file = targetFolder.getFile(targetResourceName);

					if (file.exists() && !overwrite) {
						return;
					}

					InputStream source = srcUrl.openStream();

					if (file.exists()) {
						file.setContents(source, true, true, new NullProgressMonitor());
					} else {
						file.create(source, true, new NullProgressMonitor());
					}

					targetFolder.refreshLocal(IFolder.DEPTH_INFINITE, new NullProgressMonitor());
				} catch (IOException e) {
					logger.error(e);
				} catch (CoreException e) {
					logger.error(e);
				}
			}

			@Override
			public void undo() {
				try {
					IFile file = targetFolder.getFile(targetResourceName);
					file.delete(true, new NullProgressMonitor());
					targetFolder.refreshLocal(IFolder.DEPTH_INFINITE, new NullProgressMonitor());
				} catch (CoreException e) {
					logger.error(e);
				}
			}
		};
	}
}