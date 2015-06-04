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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.ServerPort;
import org.eclipse.wst.server.core.ServerUtil;
import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.ol.ResourcesUtil;

public class DesignerUtil {

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
}
