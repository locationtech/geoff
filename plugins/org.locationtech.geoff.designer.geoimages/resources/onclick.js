/*******************************************************************************
 * Copyright (c) 2016 Erdal Karaca and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Erdal Karaca - initial API and implementation
 *******************************************************************************/
function geoff_designer_geoimages_onFeatureClick(feature) {
	var uri = feature.get("src");

	if (!uri) {
		return;
	}
	
	var imageDialog = $('#geoff_designer_geoimages_onFeatureClick_dialog');

	if (!imageDialog[0]) {
		imageDialog = $("<div id='geoff_designer_geoimages_onFeatureClick_dialog' title='An Image!' style='display: none;'></div>");
		var img = $("<img id='geoff_designer_geoimages_onFeatureClick_image' width='100%' height='100%' src=''></img>");
		imageDialog.append(img);
		$(document.body).append(imageDialog);
	}

	var imageTag = $('#geoff_designer_geoimages_onFeatureClick_image');

	// Split the URI so we can get the file name
	uriParts = uri.split("/");

	// Set the image src
	imageTag.attr('src', uri);

	// When the image has loaded, display the dialog
	imageTag.load(function() {
		imageDialog.dialog({
			modal : true,
			resizable : false,
			draggable : false,
			width : '70%',
			title : uriParts[uriParts.length - 1]
		});
	});
}

