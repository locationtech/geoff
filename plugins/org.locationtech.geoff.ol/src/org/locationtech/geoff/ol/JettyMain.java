package org.locationtech.geoff.ol;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

public class JettyMain {
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		SelectChannelConnector connector0 = new SelectChannelConnector();
		connector0.setPort(3333);
		connector0.setMaxIdleTime(30000);
		connector0.setRequestHeaderSize(8192);
		server.setConnectors(new Connector[] { connector0 });

		server.setHandler(new DefaultHandler() {
			@Override
			public void handle(String target, Request baseRequest,
					HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
				String readResource = ScriptUtil.readResource(target
						.substring(1));
				response.getWriter().write(readResource);
				response.getWriter().flush();
			}
		});

		server.start();
		server.join();
	}
}
