package org.locationtech.geoff.designer;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.Identifiable;
import org.locationtech.geoff.layer.Layer;

public interface IEditingService {
	void execute(Command command);

	Command createDeleteCommand(Identifiable id);

	Collection<? extends Identifiable> samplesOf(EClass eclass);

	Command createAddCommand(GeoMap geoMap, EReference ref, Identifiable id);

	<T extends Identifiable> T createInstance(EClass ec);

	void addLayer(Layer layer);
}
