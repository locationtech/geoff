package org.locationtech.geoff.designer;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.ServerPort;
import org.eclipse.wst.server.core.ServerUtil;
import org.locationtech.geoff.Color;
import org.locationtech.geoff.GeoffPackage;
import org.locationtech.geoff.core.Geoff;
import org.locationtech.geoff.designer.handlers.AddResourceAsLayerHandler;
import org.locationtech.geoff.designer.internal.DesignerActivator;
import org.locationtech.geoff.layer.VectorLayer;
import org.locationtech.geoff.ol.ResourcesUtil;
import org.locationtech.geoff.style.Circle;
import org.locationtech.geoff.style.Fill;
import org.locationtech.geoff.style.Stroke;
import org.locationtech.geoff.style.Style;
import org.locationtech.geoff.style.StylePackage;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class DesignerUtil {
	public static final String ICONS_GEOFF_48_PNG = "icons/geoff-48.png";
	public static final String ICONS_GEOFF_16_PNG = "icons/geoff-16.png";
	public static final String ICONS_GEOFF_WIZBAN_PNG = "icons/geoff-wizban.png";

	public static String toWSTServerUrl(IResource resource) {
		String url = getServerUrl(resource);
		String indexFileName = "index-geomap.html";
		return url + indexFileName;
	}

	private static String getServerUrl(IResource resource) {
		IProject project = resource.getProject();
		IModule module = ServerUtil.getModule(project);
		IServer[] serversByModule = ServerUtil.getServersByModule(module, new NullProgressMonitor());
		IServer server = serversByModule[0];
		ServerPort[] serverPorts = server.getServerPorts(new NullProgressMonitor());
		ServerPort serverPort = serverPorts[0];
		String protocol = serverPort.getProtocol();
		String host = server.getHost();
		int port = serverPort.getPort();
		String contextName = module.getName();
		String url = String.format("%s://%s:%d/%s/", protocol, host, port, contextName);
		return url;
	}

	public static String toRelativeURL(IFile file) {
		IProject project = file.getProject();
		IResource root = getSourcePath(project);
		StringBuilder builder = new StringBuilder();

		for (IResource context = file.getParent(); context != null
				&& !root.equals(context); context = context.getParent()) {
			builder.insert(0, context.getName()).append("/");
		}

		builder.append(file.getName());
		return builder.toString();
	}

	private static IResource getSourcePath(IProject project) {
		// TODO hack: find better way to determine the source path of the WST
		// project
		IFile wstComponent = project.getFile(".settings/org.eclipse.wst.common.component");
		IResource root = project;

		if (wstComponent != null) {
			try {
				InputStream input = wstComponent.getContents();
				String contents = ResourcesUtil.readStream(input);
				Pattern pattern = Pattern.compile(".*source-path=\"(.*?)\".*", Pattern.DOTALL);
				Matcher matcher = pattern.matcher(contents);

				if (matcher.matches()) {
					String group = matcher.group(1);
					root = project.getFolder(group);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return root;
	}

	public static IFolder toSourceFolder(IProject project, String folderName) {
		IResource sourcePath = getSourcePath(project);

		if (!(sourcePath instanceof IFolder)) {
			return null;
		}

		IFolder folder = (IFolder) sourcePath;
		IFolder targetFolder = folder.getFolder(folderName);
		return targetFolder;
	}

	public static String toSourceFolder(IFolder folder) {
		IResource sourcePath = getSourcePath(folder.getProject());

		if (!(sourcePath instanceof IFolder)) {
			return null;
		}

		IPath projectRelativePath = sourcePath.getProjectRelativePath();

		if (projectRelativePath == null) {
			return null;
		}

		IPath projectRelativePathFolder = folder.getProjectRelativePath();
		return projectRelativePathFolder.toString().substring(projectRelativePath.toString().length() + 1);
	}

	public static ImageDescriptor getImageDescriptor(String imageFilePath) {
		Bundle bundle = FrameworkUtil.getBundle(DesignerUtil.class);
		String symbolicName = bundle.getSymbolicName();
		ImageDescriptor imageDescriptorFromPlugin = DesignerActivator.imageDescriptorFromPlugin(symbolicName,
				imageFilePath);
		return imageDescriptorFromPlugin;
	}

	public static ComposedAdapterFactory createComposedAdapterFactor() {
		return new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	}

	public static void populateDefaultStyles(VectorLayer layer) {
		{
			Style style = createPointStyle();
			layer.getStyles().put("Point", style);
		}
		{
			Style style = createPointStyle();
			layer.getStyles().put("MultiPoint", style);
		}

		{
			Style style = createPolygonStyle();
			layer.getStyles().put("Polygon", style);
		}
		{
			Style style = createPolygonStyle();
			layer.getStyles().put("MultiPolygon", style);
		}

		{
			Style style = createLineStringStyle();
			layer.getStyles().put("LineString", style);
		}
		{
			Style style = createLineStringStyle();
			layer.getStyles().put("MultiLineString", style);
		}
	}

	public static Style createLineStringStyle() {
		Stroke stroke = createStroke(1.0f, 0, 0, 255, 1.0f);
		Fill fill = createFill(0, 0, 255, 0.1f);
		Style style = Geoff.instance(StylePackage.Literals.STYLE);
		style.setFill(fill);
		style.setStroke(stroke);
		return style;
	}

	public static Style createPolygonStyle() {
		Stroke stroke = createStroke(1.0f, 0, 0, 255, 1.0f);
		Fill fill = createFill(0, 0, 255, 0.05f);
		Style style = Geoff.instance(StylePackage.Literals.STYLE);
		style.setFill(fill);
		style.setStroke(stroke);
		return style;
	}

	public static Style createPointStyle() {
		Fill fill = createFill(0, 0, 255, 0.5f);
		Stroke stroke = createStroke(5.0f, 0, 0, 255, 1.0f);
		Circle circle = Geoff.instance(StylePackage.Literals.CIRCLE);
		circle.setRadius(3);
		circle.setStroke(stroke);
		circle.setFill(fill);

		Style style = Geoff.instance(StylePackage.Literals.STYLE);
		style.setImage(circle);
		return style;
	}

	private static Fill createFill(int r, int g, int b, float alpha) {
		Fill fill = Geoff.instance(StylePackage.Literals.FILL);
		{
			Color color = Geoff.instance(GeoffPackage.Literals.COLOR);
			{
				color.setRed(r);
				color.setGreen(g);
				color.setBlue(b);
				color.setAlpha(alpha);
			}

			fill.setColor(color);
		}

		return fill;
	}

	private static Stroke createStroke(float width, int r, int g, int b, float alpha) {
		Stroke stroke = Geoff.instance(StylePackage.Literals.STROKE);
		{
			stroke.setWidth((double) width);

			Color color = Geoff.instance(GeoffPackage.Literals.COLOR);
			{
				color.setRed(r);
				color.setGreen(g);
				color.setBlue(b);
				color.setAlpha(alpha);
			}

			stroke.setColor(color);
		}

		return stroke;
	}
}
