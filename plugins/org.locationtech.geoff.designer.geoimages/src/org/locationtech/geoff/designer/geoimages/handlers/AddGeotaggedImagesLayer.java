package org.locationtech.geoff.designer.geoimages.handlers;

import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.locationtech.geoff.Feature;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.XYZLocation;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.designer.DesignerUtil;
import org.locationtech.geoff.designer.IEditingService;
import org.locationtech.geoff.impl.StringToStringMapEntryImpl;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.VectorSource;
import org.osgi.framework.FrameworkUtil;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

public class AddGeotaggedImagesLayer {

	@Execute
	public void execute(IEditingService editingService, GeoMap geoMap, ISelection selection)
			throws CoreException, ImageProcessingException, IOException {
		if (selection.isEmpty()) {
			return;
		}

		Object first = ((IStructuredSelection) selection).getFirstElement();

		if (!(first instanceof IFolder)) {
			return;
		}

		CompoundCommand cc = new CompoundCommand();
		IFolder folder = (IFolder) first;

		Map<String, Entry<Double, Double>> geoTaggedImages = collectGeoTaggedImages(folder);

		if (geoTaggedImages.isEmpty()) {
			return;
		}

		// the marker.png icon is used to represent an image on the map
		String folderName = "resources";
		String targetResourceName = "marker-blue.png";
		String markerSrc = folderName + "/" + targetResourceName;
		{
			URL url = FrameworkUtil.getBundle(getClass()).getEntry(markerSrc);
			IFolder targetFolder = DesignerUtil.toSourceFolder(folder.getProject(), folderName);
			Command addResource = editingService.createAddResourceCommand(targetFolder, targetResourceName, url, true);
			cc.append(addResource);
		}

		VectorSource vectorSource = Geoff.vectorSource();
		VectorLayer layer = Geoff.vectorLayer(vectorSource);
		layer.setShortDescription("Geotagged Images");

		for (Entry<String, Entry<Double, Double>> e : geoTaggedImages.entrySet()) {
			String url = e.getKey();
			Entry<Double, Double> v = e.getValue();
			double x = v.getKey();
			double y = v.getValue();
			// assume WGS/84 (GPS) coordinate system used
			XYZLocation xyLocation = Geoff.xyLocation(x, y, Geoff.EPSG4326_WGS84);
			Feature feature = Geoff.feature(Geoff.pointGeom(xyLocation), Geoff.style(Geoff.icon(markerSrc)));
			BasicEMap.Entry<String, String> urlProp = Geoff.featureProperty("url", url);
			feature.getProperties().add(urlProp);
			vectorSource.getFeatures().add(feature);
		}

		Command command = editingService.createAddCommand(geoMap, GeoffPackage.Literals.GEO_MAP__LAYERS, layer);
		cc.append(command);
		editingService.execute(cc);
	}

	/**
	 * Collects all geo tagged images in the provided folder. The returned map
	 * contains entries of file names to geo coordinates (longitude/latitude).
	 */
	private Map<String, Map.Entry<Double, Double>> collectGeoTaggedImages(IFolder folder)
			throws CoreException, ImageProcessingException, IOException {
		Map<String, Map.Entry<Double, Double>> geoTaggedImages = new HashMap<String, Map.Entry<Double, Double>>();

		for (IResource resource : folder.members()) {
			if (!(resource instanceof IFile)) {
				continue;
			}

			IFile file = (IFile) resource;

			Metadata md;

			try {
				md = ImageMetadataReader.readMetadata(file.getContents());
			} catch (ImageProcessingException e) {
				// unsupported file
				continue;
			}

			GpsDirectory gpsDir = md.getFirstDirectoryOfType(GpsDirectory.class);
			GeoLocation geoLocation = gpsDir.getGeoLocation();
			geoTaggedImages.put(file.getName(),
					new AbstractMap.SimpleEntry<Double, Double>(geoLocation.getLongitude(), geoLocation.getLatitude()));
		}

		return geoTaggedImages;
	}
}
