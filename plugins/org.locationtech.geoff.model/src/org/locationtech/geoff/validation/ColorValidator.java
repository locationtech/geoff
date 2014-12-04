/**
 *
 * $Id$
 */
package org.locationtech.geoff.validation;

/**
 * A sample validator interface for {@link org.locationtech.geoff.Color}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ColorValidator {
	boolean validate();

	boolean validateRed(int value);

	boolean validateGreen(int value);

	boolean validateBlue(int value);

	boolean validateAlpha(float value);

	boolean validateAlpha(int value);
}