/*******************************************************************************
 * Copyright (c) 2014 Erdal Karaca.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Erdal Karaca - initial API and implementation
 ******************************************************************************/

// /***/
(function(target, $) {
	// the various Geoff XML namespaces
	var GEOFF_NS = {
		"geoff" : "http://www.locationtech.org/geoff-v1",
		"geoff.geom" : "http://www.locationtech.org/geoff-geom-v1",
		"geoff.layer" : "http://www.locationtech.org/geoff-layer-v1",
		"geoff.source" : "http://www.locationtech.org/geoff-source-v1",
		"geoff.style" : "http://www.locationtech.org/geoff-style-v1"
	};
	
	// this is the array of rules that are supported to transform DOM nodes to
	// OL3 instructions, this is like a grammar of a language with dynamic rules
	// ---
	// each rule is a key that denotes the DOM node to transform and a
	// transformation function that interprets the DOM node and executes to
	// valid OL3 instructions
	// ---
	// when rules are executed, they will be provided a reference to the DOM
	// node that has to be interpreted and a reference to a hash map that can be
	// used to interchange data to/from the rules
	// ---
	// Example:
	// rules['nsPrefix:NodeName'] = function(domNode, env)
	// {alert(domNode.nodeName);}

	var rules = {};

	// this is the entry rule that is responsible for parsing the root DOM node
	// geoff:GeoMap
	rules["geoff:GeoMap"] = function(domNode, env) {
		var layers = domNode.getElementsByTagName("layer");
		var domView = domNode.getElementsByTagName("view")[0];
		var rendererHint = domNode.getAttribute("rendererHint");

		if (rendererHint != null) {
			rendererHint = rendererHint.toLowerCase();
		}

		var olView = execRule("geoff", "View", domView, env);
		// make the view available to the environment, for example,
		// to transform positions/locations to view projection
		env.view = olView;
		var map = new ol.Map({
			layers : convertCollection(layers, env),
			view : olView
		});

		return map;
	};

	rules["geoff:View"] = function(domNode, env) {
		var center = domNode.getElementsByTagName("center")[0];
		var zoom = domNode.getAttribute("zoom");

		var view = new ol.View({
			zoom : convertInt(zoom, env)
		});

		var centerCoords = convertObject(center, env);
		var sourceProj = center.getAttribute("projectionCode");

		// if the center node has a projection code set,
		// use it to transform the coordinates to the view's projection
		if (sourceProj != null) {
			var viewProjection = view.getProjection();
			var viewProjectionCode = viewProjection.getCode();
			centerCoords = ol.proj.transform(centerCoords, sourceProj,
					viewProjectionCode);
		}

		view.setCenter(centerCoords);
		return view;
	};

	rules["geoff.layer:Tile"] = function(domNode, env) {
		var source = domNode.getElementsByTagName("source")[0];

		return new ol.layer.Tile({
			source : convertObject(source, env)
		});
	};

	rules["geoff.layer:Vector"] = function(domNode, env) {
		var source = domNode.getElementsByTagName("source")[0];

		return new ol.layer.Vector({
			source : convertObject(source, env)
		});
	};

	rules["geoff.source:Vector"] = function(domNode, env) {
		var features = domNode.getElementsByTagName("feature");
		return new ol.source.Vector({
			features : convertCollection(features, env, "geoff:Feature")
		});
	};

	rules["geoff:Feature"] = function(domNode, env) {
		var geometry = domNode.getElementsByTagName("geometry")[0];
		var styles = domNode.getElementsByTagName("style")[0];

		var olFeature = new ol.Feature({
			geometry : convertObject(geometry, env)
		});

		var olStyle = convertObject(styles, env, "geoff.style:Style");
		olFeature.setStyle(olStyle);

		return olFeature;
	};

	rules["geoff.geom:Point"] = function(domNode, env) {
		var coords = domNode.getElementsByTagName("coordinates")[0];
		var location = convertObject(coords, env);
		var sourceProj = coords.getAttribute("projectionCode");

		if (sourceProj != null) {
			var viewProjection = env.view.getProjection();
			var viewProjectionCode = viewProjection.getCode();
			location = ol.proj.transform(location, sourceProj,
					viewProjectionCode);
		}

		return new ol.geom.Point(location);
	};

	rules["geoff.style:Style"] = function(domNode, env) {
		var image = domNode.getElementsByTagName("image")[0];

		return new ol.style.Style({
			image : convertObject(image, env)
		});
	};

	rules["geoff.style:Icon"] = function(domNode, env) {
		var src = domNode.getAttribute("src");
		return new ol.style.Icon({
			anchor : [ 0.5, 46 ],
			anchorXUnits : 'fraction',
			anchorYUnits : 'pixels',
			opacity : 0.75,
			src : src
		});
	};

	rules["geoff.source:OSM"] = function(domNode, env) {
		return new ol.source.OSM();
	};

	rules["geoff.source:MapQuest"] = function(domNode, env) {
		var layer = domNode.getAttribute("zoom");

		if (layer == null) {
			layer = "osm";
		}

		return new ol.source.MapQuest({
			layer : layer
		});
	};

	rules["geoff.source:BingMaps"] = function(domNode, env) {
		var key = domNode.getAttribute("key");
		var imagerySet = domNode.getAttribute("imagerySet");

		return new ol.source.BingMaps({
			key : key,
			imagerySet : imagerySet
		});
	};

	rules["geoff:XYZLocation"] = function(domNode, env) {
		var x = domNode.getAttribute("x") != null ? convertFloat(domNode
				.getAttribute("x"), env) : 0;
		var y = domNode.getAttribute("y") != null ? convertFloat(domNode
				.getAttribute("y"), env) : 0;
		var z = domNode.getAttribute("z") != null ? convertFloat(domNode
				.getAttribute("z"), env) : 0;
		var coords = [ x, y, z ];
		return coords;
	};

	function execRule(nsPrefix, tagName, contextNode, env) {
		var ruleName = nsPrefix + ":" + tagName;
		return rules[ruleName](contextNode, env);
	}

	function convertCollection(domCollection, env, type) {
		var jArray = new Array();

		for (var i = 0; i < domCollection.length; i++) {
			var jObject = convertObject(domCollection[i], env, type);
			jArray.push(jObject);
		}

		return jArray;
	}

	function convertObject(domNode, env, defaultType) {
		if (domNode == null) {
			return null;
		}

		var type = domNode.getAttribute("xsi:type");

		if (type == null) {
			type = defaultType;
		}

		// TODO translate xsi:type to internal NS prefix mapping
		var rule = rules[type];

		if (rule == null) {
			console.error("undefined rule: " + type);
			return null;
		}

		return rule(domNode, env);
	}

	function convertInt(value, env) {
		return parseInt(value);
	}

	function convertFloat(value, env) {
		return parseFloat(value);
	}

	function getElements(domNode, nsPrefix, tagName) {
		var resolvedNamespace = GEOFF_NS[nsPrefix];
		return domNode.getElementsByTagNameNS(resolvedNamespace, tagName);
	}

	function loadMaps(root, divId) {
		var maps = getElements(root, "geoff", "GeoMap");
		var env = {};

		if (maps.length == 1) {
			var domMap = maps[0];
			loadMap(domMap, divId, env);
		} else {
			for (var i = 0; i < maps.length; i++) {
				var domMap = maps[i];
				var divId = "geoffMap" + i;
				var $div = $("<div id='" + divId + "' class='map'></div>");
				$div.appendTo($(domMap));
				loadMap(domMap, divId, env);
			}
		}
	}

	function loadMap(domNode, divId, env) {
		var $div = $("#" + divId);
		var ol3Map = $div.data("ol3Map");

		if (ol3Map != null) {
			ol3Map.setTarget();
			$div.data("ol3Map", null);
		}

		var map = execRule("geoff", "GeoMap", domNode, env);
		map.setTarget(divId);
		map.updateSize();
		$div.data("ol3Map", map);
	}

	function loadFromUrl(url, divId) {
		$.ajax({
			type : "GET",
			url : url,
			dataType : "xml",
			success : function(domRoot) {
				loadMaps(domRoot, divId);
			}
		});
	}

	function loadFromXMLString( /* String */xmlString, /* String */divId) {
		var domRoot = $.parseXML(xmlString);
		$(domRoot).find("*").each(function() {
			if (this.nodeName == "geoff:GeoMap") {
				var env = {};
				loadMap(this, divId, env);
			}
		});
	}

	// this is the API object which can be accessed via the global
	// 'window.geoff' variable
	target.geoff = {
		loadFromUrl : loadFromUrl,
		loadFromXMLString : loadFromXMLString
	};

	// the standalone mode is used to indicate that the document has embedded
	// maps which are to be loaded automatically
	target.geoff.standalone = false;

	$(document).ready(function() {
		if (window.geoff.standalone) {
			loadMaps(document);
		}
	});
}(window, jQuery));
