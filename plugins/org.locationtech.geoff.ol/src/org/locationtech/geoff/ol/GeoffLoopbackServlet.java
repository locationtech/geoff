/*******************************************************************************
 * Copyright (c) 2014 Erdal Karaca.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 ******************************************************************************/
package org.locationtech.geoff.ol;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.locationtech.geoff.GeoMap;
import org.locationtech.geoff.core.Geoff;

public class GeoffLoopbackServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String token = req.getParameter("token");

		GeoMap map = null;

		if (token == null || "".equals(token.trim()) || "debug".equals(token)) {
			map = GeoMapProvidersRegistry.INSTANCE.providers.values()
					.iterator().next().getMap();
		} else {
			IGeoMapProvider provider = GeoMapProvidersRegistry.INSTANCE
					.getProvider(token);
			map = provider.getMap();
		}

		String xml = Geoff.wrap(map).toXML();
		System.out.println(xml);
		resp.setContentType("text/xml");
		resp.setContentLength(xml.length());
		resp.getWriter().write(xml);
		resp.getWriter().flush();
	}
}
