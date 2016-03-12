package org.locationtech.geoff.designer.geoimages.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.locationtech.geoff.Feature;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.Script;
import org.locationtech.geoff.ScriptContext;
import org.locationtech.geoff.XYZLocation;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.core.IGeoMapService;
import org.locationtech.geoff.core.logging.LogUtil;
import org.locationtech.geoff.interaction.EventCondition;
import org.locationtech.geoff.interaction.InteractionPackage;
import org.locationtech.geoff.interaction.Select;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.source.VectorSource;
import org.osgi.framework.FrameworkUtil;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

public class AddGeotaggedImagesLayer {

	private static final String ONCLICK_FUNCTION = "geoff_designer_geoimages_onFeatureClick";

	@Execute
	public void execute(IGeoMapService geoMapService, ISelection selection)
			throws CoreException, ImageProcessingException, IOException {
		if (selection.isEmpty()) {
			return;
		}

		Object first = ((IStructuredSelection) selection).getFirstElement();

		if (!(first instanceof IFolder)) {
			return;
		}

		IFolder folder = (IFolder) first;

		Map<String, Entry<Double, Double>> geoTaggedImages = collectGeoTaggedImages(folder);

		if (geoTaggedImages.isEmpty()) {
			return;
		}

		GeoMap geoMap = geoMapService.getGeoMap();
		List<Runnable> commands = new ArrayList<>();

		// the marker.png icon is used to represent an image on the map
		String markerFolderName = "resources";
		String markerTargetFolderName = "data";
		String markerResourceName = "marker-blue.png";
		String markerTarget = markerTargetFolderName + "/" + markerResourceName;
		{
			String markerSrc = markerFolderName + "/" + markerResourceName;
			URL url = FrameworkUtil.getBundle(getClass()).getEntry(markerSrc);
			IFolder targetFolder = folder.getProject().getFolder(markerTargetFolderName);
			commands.add(() -> {
				LogUtil.logErrorOnException(() -> {
					createAddResourceCommand(targetFolder, markerResourceName, url, true);
				});
			});
		}

		String jsTargetFolderName = "js";
		String onclickResourceName = "onclick.js";
		String onclickResourceNameTarget = jsTargetFolderName + "/" + onclickResourceName;
		String onclickResourceNameLocal = markerFolderName + "/" + onclickResourceName;

		// install onclick.js file
		{
			URL url = FrameworkUtil.getBundle(getClass()).getEntry(onclickResourceNameLocal);
			IFolder targetFolder = folder.getProject().getFolder(jsTargetFolderName);
			commands.add(() -> {
				LogUtil.logErrorOnException(() -> {
					createAddResourceCommand(targetFolder, onclickResourceName, url, true);
				});
			});
		}

		if (!containsScriptSrc(geoMap, onclickResourceNameTarget)) {
			Script script = Geoff.instance(GeoffPackage.Literals.SCRIPT);
			script.setSrc(onclickResourceNameTarget);
			script.setContext(ScriptContext.GLOBAL);
			commands.add(() -> {
				geoMap.getScripts().add(script);
			});

			Select interaction = Geoff.instance(InteractionPackage.Literals.SELECT);
			interaction.setCondition(EventCondition.SINGLE_CLICK);
			commands.add(() -> {
				geoMap.getInteractions().add(interaction);
			});
		}

		VectorSource vectorSource = Geoff.vectorSource();
		VectorLayer layer = Geoff.vectorLayer(vectorSource);
		String targetFolderName = folder.getName();
		layer.setShortDescription("Geotagged Images (Source: " + targetFolderName + ")");

		for (Entry<String, Entry<Double, Double>> e : geoTaggedImages.entrySet()) {
			String src = targetFolderName + "/" + e.getKey();
			Entry<Double, Double> v = e.getValue();
			double x = v.getKey();
			double y = v.getValue();

			// assume WGS/84 (GPS) coordinate system used
			XYZLocation xyLocation = Geoff.xyLocation(x, y, Geoff.EPSG4326_WGS84);

			Feature feature = Geoff.feature(Geoff.pointGeom(xyLocation), Geoff.style(Geoff.icon(markerTarget)));
			feature.getProperties().put("src", src);
			feature.setOnclick(ONCLICK_FUNCTION);
			vectorSource.getFeatures().add(feature);
		}

		commands.add(() -> {
			geoMapService.addLayer(layer);
		});

		geoMapService.batchChanges(commands.toArray(new Runnable[0]));
	}

	private boolean containsScriptSrc(GeoMap geoMap, String src) {
		for (Script script : geoMap.getScripts()) {
			if (script.getSrc().equals(src)) {
				return true;
			}
		}

		return false;
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

	private void createAddResourceCommand(final IFolder targetFolder, final String targetResourceName, final URL srcUrl,
			final boolean overwrite) throws IOException, CoreException {
		if (!targetFolder.exists()) {
			targetFolder.create(true, false, new NullProgressMonitor());
		}

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

		// @OVERRIDE
		// PUBLIC VOID UNDO() {
		// TRY {
		// IFILE FILE = TARGETFOLDER.GETFILE(TARGETRESOURCENAME);
		// FILE.DELETE(TRUE, NEW NULLPROGRESSMONITOR());
		// TARGETFOLDER.REFRESHLOCAL(IFOLDER.DEPTH_INFINITE, NEW
		// NULLPROGRESSMONITOR());
		// } CATCH (COREEXCEPTION E) {
		// LOGGER.ERROR(E);
		// }
		// }
	}
}
