package org.locationtech.geoff.ui;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.locationtech.geoff.GeoMap;

public class ProvidersFactory {
	public static ITreeContentProvider createLayerContentProvider(AdapterFactory af) {
		return new AdapterFactoryContentProvider(af) {

			@Override
			public Object[] getElements(Object inputElement) {
				super.getElements(inputElement);
				
				if (inputElement instanceof GeoMap) {
					return ((GeoMap) inputElement).getLayers().toArray();
				}

				return null;
			}
			
			@Override
			public boolean hasChildren(Object object) {
				super.hasChildren(object);
				return false;
			}
		};
	}

	public static ILabelProvider createLayerLabelProvider(AdapterFactory af) {
		return new AdapterFactoryLabelProvider(af);
	}
}
