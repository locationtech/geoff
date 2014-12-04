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
		var layers = elements(domNode, "layer");
		var domView = elements(domNode, "view")[0];
		var rendererHint = attrValue(domNode, "rendererHint");

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

		zoomslider = new ol.control.ZoomSlider();
		map.addControl(zoomslider);

		return map;
	};

	rules["geoff:View"] = function(domNode, env) {
		var center = elements(domNode, "center")[0];
		var zoom = attrValue(domNode, "zoom");

		var view = new ol.View({
			zoom : convertInt(zoom, env)
		});

		var centerCoords = convertObject(center, env);
		var sourceProj = attrValue(center, "projectionCode");

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
		var source = elements(domNode, "source")[0];

		return new ol.layer.Tile({
			source : convertObject(source, env)
		});
	};

	rules["geoff.layer:Vector"] = function(domNode, env) {
		var source = elements(domNode, "source")[0];
		var domStyles = elements(domNode, "style");
		var stylesMap = {};

		for (var i = 0; i < domStyles.length; i++) {
			var style = domStyles[i];
			var styleKey = attrValue(style, "key");
			var styleValue = elements(style, "value")[0];
			var olStyle = convertObject(styleValue, env, "geoff.style:Style");
			stylesMap[styleKey] = [ olStyle ];
		}

		return new ol.layer.Vector({
			source : convertObject(source, env),
			style : function(feature, resolution) {
				var key = feature.getGeometry().getType();
				var st = stylesMap[key];
				return st;
			}
		});
	};

	rules["geoff.source:Vector"] = function(domNode, env) {
		var features = elements(domNode, "feature");
		return new ol.source.Vector({
			features : convertCollection(features, env, "geoff:Feature")
		});
	};

	function staticVectorSourceOptions(domNode, env) {
		var url = attrValue(domNode, "url");
		var projection = attrValue(domNode, "projection");

		return {
			url : url,
			projection : projection
		};
	}

	rules["geoff.source:GPX"] = function(domNode, env) {
		var options = staticVectorSourceOptions(domNode, env);
		return new ol.source.GPX(options);
	};

	rules["geoff.source:KML"] = function(domNode, env) {
		var options = staticVectorSourceOptions(domNode, env);
		return new ol.source.KML(options);
	};

	rules["geoff.source:GeoJSON"] = function(domNode, env) {
		var options = staticVectorSourceOptions(domNode, env);
		return new ol.source.GeoJSON(options);
	};

	rules["geoff:Feature"] = function(domNode, env) {
		var geometry = elements(domNode, "geometry")[0];
		var styles = elements(domNode, "style")[0];

		var olFeature = new ol.Feature({
			geometry : convertObject(geometry, env)
		});

		var olStyle = convertObject(styles, env, "geoff.style:Style");
		olFeature.setStyle(olStyle);

		return olFeature;
	};

	rules["geoff.geom:Point"] = function(domNode, env) {
		var coords = elements(domNode, "coordinates")[0];
		var location = convertObject(coords, env);
		var sourceProj = attrValue(coords, "projectionCode");

		if (sourceProj != null) {
			var viewProjection = env.view.getProjection();
			var viewProjectionCode = viewProjection.getCode();
			location = ol.proj.transform(location, sourceProj,
					viewProjectionCode);
		}

		return new ol.geom.Point(location);
	};

	rules["geoff.style:Style"] = function(domNode, env) {
		var image = elements(domNode, "image");
		var fill = elements(domNode, "fill");
		var stroke = elements(domNode, "stroke");

		return new ol.style.Style({
			image : image.length > 0 ? convertObject(image[0], env) : null,
			fill : fill.length > 0 ? convertObject(fill[0], env,
					"geoff.style:Fill") : null,
			stroke : stroke.length > 0 ? convertObject(stroke[0], env,
					"geoff.style:Stroke") : null
		});
	};

	rules["geoff.style:Icon"] = function(domNode, env) {
		var src = attrValue(domNode, "src");
		return new ol.style.Icon({
			src : src
		});
	};

	rules["geoff.style:Color"] = function(domNode, env) {
		var r = attrValue(domNode, "red");
		var g = attrValue(domNode, "green");
		var b = attrValue(domNode, "blue");
		var a = attrValue(domNode, "alpha");

		return 'rgba(' + (r == null ? 0 : r) + ',' + (g == null ? 0 : g) + ','
				+ (b == null ? 0 : b) + ',' + (a == null ? 0 : a) + ')';
	};

	rules["geoff.style:Fill"] = function(domNode, env) {
		var color = elements(domNode, "color");

		return new ol.style.Fill({
			color : color.length > 0 ? convertObject(color[0], env,
					"geoff.style:Color") : null,
		});
	};

	rules["geoff.style:Circle"] = function(domNode, env) {
		var fill = elements(domNode, "fill");
		var stroke = elements(domNode, "stroke");
		var radius = convertFloat(attrValue(domNode, "radius"));

		return new ol.style.Circle({
			radius : radius,
			fill : fill.length > 0 ? convertObject(fill[0], env,
					"geoff.style:Fill") : null,
			stroke : stroke.length > 0 ? convertObject(stroke[0], env,
					"geoff.style:Stroke") : null
		});
	};

	rules["geoff.style:Stroke"] = function(domNode, env) {
		var color = elements(domNode, "color");
		var lineDash = elements(domNode, "lineDash");
		var options = {};

		if (color.length > 0) {
			options.color = convertObject(color[0], env, "geoff.style:Color");
		}

		if (lineDash.length > 0) {
			options.lineDash = convertCollectionFloat(lineDash, env);
		}

		var widthStr = attrValue(domNode, "width");

		if (widthStr != null) {
			options.width = convertFloat(widthStr);
		}

		var lineCap = attrValue(domNode, "lineCap");

		if (lineCap != null) {
			options.lineCap = lineCap;
		}

		var lineJoin = attrValue(domNode, "lineJoin");

		if (lineJoin != null) {
			options.lineJoin = lineJoin;
		}

		var meterLimitStr = attrValue(domNode, "miterLimit");

		if (meterLimitStr != null) {
			options.miterLimit = convertFloat(meterLimitStr);
		}

		return new ol.style.Stroke(options)
	};

	rules["geoff.source:OSM"] = function(domNode, env) {
		return new ol.source.OSM();
	};

	rules["geoff.source:MapQuest"] = function(domNode, env) {
		var layer = attrValue(domNode, "layer");

		if (layer == null) {
			layer = "osm";
		}

		return new ol.source.MapQuest({
			layer : layer
		});
	};

	rules["geoff.source:BingMaps"] = function(domNode, env) {
		var key = attrValue(domNode, "key");
		var imagerySet = attrValue(domNode, "imagerySet");

		return new ol.source.BingMaps({
			key : key,
			imagerySet : imagerySet
		});
	};

	rules["geoff:XYZLocation"] = function(domNode, env) {
		var coords = new Array();
		var x = attrValue(domNode, "x") != null ? convertFloat(attrValue(
				domNode, "x"), env) : 0;
		var y = attrValue(domNode, "y") != null ? convertFloat(attrValue(
				domNode, "y"), env) : 0;

		coords.push(x);
		coords.push(y);

		if (attrValue(domNode, "z") != null) {
			var z = convertFloat(attrValue(domNode, "z"), env);
			coords.push(z);
		}

		return coords;
	};

	function elements(domNode, elemName) {
		var ret = new Array();
		var children = domNode.childNodes;

		for (var i = 0; i < children.length; i++) {
			var child = children.item(i);

			if (child.nodeName === elemName) {
				ret.push(child);
			}
		}

		return ret;
	}

	function attrValue(domNode, attrName) {
		return domNode.getAttribute(attrName);
	}

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

	function convertCollectionFloat(domCollection, env, type) {
		var jArray = new Array();

		for (var i = 0; i < domCollection.length; i++) {
			var strValue = domCollection[i];

			if (strValue == null) {
				continue;
			}

			strValue = strValue.textContent;
			var jObject = convertFloat(strValue, env);
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
