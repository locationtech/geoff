/**
 *
 * $Id$
 */
package org.locationtech.geoff.validation;

import org.eclipse.emf.common.util.EList;

import org.locationtech.geoff.geom.Geometry;

import org.locationtech.geoff.style.Style;

/**
 * A sample validator interface for {@link org.locationtech.geoff.Feature}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface FeatureValidator {
	boolean validate();

	boolean validateGeometry(Geometry value);

	boolean validateStyle(Style value);

	boolean validateStyles(EList<Style> value);
}
