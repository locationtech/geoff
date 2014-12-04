package org.locationtech.geoff.designer.webserver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Dispatcher;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jface.util.SafeRunnable;
import org.locationtech.geoff.ol.ResourcesUtil;

public class JettyLocalServerManager {
	public static final JettyLocalServerManager INSTANCE = new JettyLocalServerManager();

	private Server server;

	private JettyLocalServerManager() {
	}

	public void stopServer() {
		if (server != null) {
			SafeRunner.run(new SafeRunnable() {

				@Override
				public void run() throws Exception {
					server.stop();
				}
			});
		}
	}

	public void startServer(String baseResourcesPath, int port) {
		final File webRoot = new File(baseResourcesPath);
		Assert.isTrue(webRoot.isDirectory(),
				"Expected a directory to use as web root directory.");
		Assert.isTrue(webRoot.exists(),
				"Directory does not exist to use as web root folder.");

		server = new Server();
		SelectChannelConnector connector0 = new SelectChannelConnector();
		connector0.setPort(port);
		connector0.setMaxIdleTime(30000);
		connector0.setRequestHeaderSize(8192);
		server.setConnectors(new Connector[] { connector0 });
		// server.setHandler(new ResourceHandler() {
		// @Override
		// protected Resource getResource(HttpServletRequest request)
		// throws MalformedURLException {
		// request.setAttribute(Dispatcher.INCLUDE_REQUEST_URI, true);
		// request.setAttribute(Dispatcher.INCLUDE_SERVLET_PATH, webRoot
		// .toURI().toURL().toString());
		// request.setAttribute(Dispatcher.INCLUDE_PATH_INFO, request
		// .getPathInfo().substring(1));
		// return super.getResource(request);
		// }
		// });

		server.setHandler(new DefaultHandler() {
			@Override
			public void handle(String target, Request baseRequest,
					HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
				File file = new File(webRoot, target);
				String ext = file.getName();
				
				if (ext.endsWith(".png")) {
					response.setContentType("image/png");
				}
				
				byte[] contents = Files.readAllBytes(file.toPath());
				response.getWriter().write(new String(contents));
				response.getWriter().flush();
			}
		});

		SafeRunner.run(new SafeRunnable() {

			@Override
			public void run() throws Exception {
				server.start();
			}
		});
	}
}
