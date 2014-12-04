package org.locationtech.geoff.ui;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RenderSettingsBuilder {
	private Map<String, Object> settings = new HashMap<String, Object>();
	private IRenderSettings settingsInterface = (IRenderSettings) Proxy
			.newProxyInstance(getClass().getClassLoader(),
					new Class<?>[] { IRenderSettings.class },
					new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method,
								Object[] args) throws Throwable {
							if (method.getDeclaringClass() == IRenderSettings.class
									&& args == null) {
								return settings.get(method.getName());
							}

							return method.invoke(proxy, args);
						}
					});

	public static RenderSettingsBuilder create() {
		return new RenderSettingsBuilder();
	}

	public RenderSettingsBuilder baseURL(URL baseURL) {
		settings.put("baseURL", baseURL);
		return this;
	}

	public IRenderSettings get() {
		return settingsInterface;
	}
}