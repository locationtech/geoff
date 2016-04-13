/*******************************************************************************
 * Copyright (c) 2016 Erdal Karaca.
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
	var DEFAULT_MAP_DIV_ID = "map";
	// the various Geoff XML namespaces
	var GEOFF_NS = {
		"geoff" : "http://www.locationtech.org/geoff-v1",
		"geoff.geom" : "http://www.locationtech.org/geoff-geom-v1",
		"geoff.layer" : "http://www.locationtech.org/geoff-layer-v1",
		"geoff.source" : "http://www.locationtech.org/geoff-source-v1",
		"geoff.style" : "http://www.locationtech.org/geoff-style-v1",
		"geoff.interaction" : "http://www.locationtech.org/geoff-interaction-v1"
	};

	var eventHandlers = {};

	function handleEvent(eventName, params, sendEvent) {
		if (sendEvent == null || sendEvent) {
			// notify server that event triggered
			geoff.eventTriggered(eventName, params);
		} else {
			// return current value of event
			return params;
		}
	}

	eventHandlers["viewCenter"] = function(e, sendEvent) {
		var view = ol3Map().getView();
		var center = view.getCenter();
		var code = view.getProjection().getCode();
		var params = [ center, code ];
		return handleEvent("viewCenter", params, sendEvent);
	};

	eventHandlers["viewZoom"] = function(e, sendEvent) {
		var view = ol3Map().getView();
		var params = [ view.getZoom() ];
		return handleEvent("viewZoom", params, sendEvent);
	};

	eventHandlers["editingMode"] = function(e, sendEvent) {
		var map = ol3Map();
		var params = [ map.currentMode ];
		return handleEvent("editingMode", params, sendEvent);
	};

	var wktFormat = new ol.format.WKT();
	eventHandlers["geometryAdded"] = function(e, sendEvent) {
		// TODO if circle, then transform to polygon
		var geomWKT = wktFormat.writeGeometry(e.element.getGeometry());
		var params = [ geomWKT ];
		return handleEvent("geometryAdded", params, sendEvent);
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
		var domView = elements(domNode, "view")[0];
		var rendererHint = attrValue(domNode, "rendererHint");

		if (rendererHint != null) {
			rendererHint = rendererHint.toLowerCase();
		}

		var olView = execRule("geoff", "View", domView, env);
		var map = new ol.Map({
			view : olView
		});
		env.map = map;

		var layers = convertCollection(elements(domNode, "layer"), env);
		for (var i = 0; i < layers.length; i++) {
			map.addLayer(layers[i]);
		}

		var interactions = convertCollection(elements(domNode, "interactions"),
				env);
		for (var i = 0; i < interactions.length; i++) {
			map.addInteraction(interactions[i]);
		}

		zoomslider = new ol.control.ZoomSlider();
		map.addControl(zoomslider);

		var domScripts = elements(domNode, "scripts");
		convertCollection(domScripts, env, "geoff:Script");

		// setup drawing support
		map.currentFeatures = new ol.Collection();
		map.currentFeatures.on("add",
				target.geoff.eventHandlers["geometryAdded"]);

		var featureOverlay = new ol.layer.Vector({
			source : new ol.source.Vector({
				features : map.currentFeatures
			}),
			style : new ol.style.Style({
				fill : new ol.style.Fill({
					color : 'rgba(255, 255, 255, 0.2)'
				}),
				stroke : new ol.style.Stroke({
					color : '#ffcc33',
					width : 2
				}),
				image : new ol.style.Circle({
					radius : 7,
					fill : new ol.style.Fill({
						color : '#ffcc33'
					})
				})
			})
		});

		map.switchEditingMode = function(mode) {
			map.currentMode = mode;
			// clear the collection as any new added feature will be delivered
			// to the server to process it in any way
			map.currentFeatures.clear();

			if (mode == "NONE" || mode == null) {
				if (map.currentModify != null) {
					map.removeInteraction(map.currentModify);
				}

				if (map.currentDraw != null) {
					map.removeInteraction(map.currentDraw);
				}

				featureOverlay.setMap(null);
				return;
			}

			featureOverlay.setMap(map);

			map.currentModify = new ol.interaction.Modify({
				features : map.currentFeatures,
				// the SHIFT key must be pressed to delete vertices, so
				// that new vertices can be drawn at the same position
				// of existing vertices
				deleteCondition : function(event) {
					return ol.events.condition.shiftKeyOnly(event)
							&& ol.events.condition.singleClick(event);
				}
			});
			map.addInteraction(map.currentModify);

			map.currentDraw = new ol.interaction.Draw({
				features : map.currentFeatures,
				type : mode
			});
			map.currentDraw.on("drawstart", function(e) {
				map.currentFeatures.clear();
			});
			// map.currentDraw.on("drawend", function(e) {
			// var geomWKT = wktFormat.writeGeometry(e.feature.getGeometry());
			// // geometry must be handled by server
			// geoff.eventTriggered("geometryAdded", geomWKT);
			// });
			map.addInteraction(map.currentDraw);
		}

		return map;
	};

	rules["geoff.interaction:Select"] = function(domNode, env) {
		var conditionStr = attrValue(domNode, "condition");
		var opts = {};

		if (conditionStr == "CLICK") {
			opts.condition = ol.events.condition.click;
		} else if (conditionStr == "HOVER") {
			opts.condition = ol.events.condition.pointerMove;
		}

		var select = new ol.interaction.Select(opts);
		select.on('select', function(e) {
			var features = e.target.getFeatures();

			for (var i = 0; i < features.getLength(); i++) {
				var feature = features.item(i);

				if (feature.geoff_interaction_select_on_click) {
					feature.geoff_interaction_select_on_click(feature);
				}
			}
		});

		return select;
	};

	rules["geoff:Script"] = function(domNode, env) {
		var src = attrValue(domNode, "src");
		loadScript(src, function() {
		});
	};

	rules["geoff:View"] = function(domNode, env) {
		var center = elements(domNode, "center")[0];
		var zoom = attrValue(domNode, "zoom");

		var view = new ol.View({
			zoom : convertInt(zoom, env)
		});
		// make the view available to the environment, for example,
		// to transform positions/locations to view projection
		env.view = view;

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

		view.on("change:resolution", target.geoff.eventHandlers["viewZoom"]);
		view.on("change:center", target.geoff.eventHandlers["viewCenter"]);

		return view;
	};

	rules["geoff.layer:TileLayer"] = function(domNode, env) {
		var source = elements(domNode, "source")[0];

		return new ol.layer.Tile({
			source : convertObject(source, env)
		});
	};

	rules["geoff.layer:VectorLayer"] = function(domNode, env) {
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

	rules["geoff.source:VectorSource"] = function(domNode, env) {
		var features = elements(domNode, "feature");
		var opts = {};

		if (features.length > 0) {
			opts.features = convertCollection(features, env, "geoff:Feature");
		} else {
			var formatStr = attrValue(domNode, "format");
			var url = attrValue(domNode, "url");

			if (url != null) {
				opts.url = url;
			}

			if (formatStr === "GeoJSON") {
				opts.format = new ol.format.GeoJSON();
			} else if (formatStr === "KML") {
				opts.format = new ol.format.KML();
			} else if (formatStr === "GML") {
				opts.format = new ol.format.GML();
			} else if (formatStr === "GPX") {
				opts.format = new ol.format.GPX();
			}
		}

		return new ol.source.Vector(opts);
	};

	rules["geoff:Feature"] = function(domNode, env) {
		var geometry = elements(domNode, "geometry")[0];
		var styles = elements(domNode, "style")[0];
		var propsElems = elements(domNode, "properties");

		var props = convertKeyValueProperties(propsElems, env);
		props.geometry = convertObject(geometry, env);
		var olFeature = new ol.Feature(props);

		var olStyle = convertObject(styles, env, "geoff.style:Style");
		olFeature.setStyle(olStyle);

		var onclickStr = attrValue(domNode, "onclick");

		if (onclickStr) {
			olFeature.geoff_interaction_select_on_click = function(feature) {
				var onclickFunc = window[onclickStr];

				if (onclickFunc) {
					onclickFunc(olFeature);
				}
			}
		}

		return olFeature;
	};

	rules["geoff.geom:Point"] = function(domNode, env) {
		var coords = elements(domNode, "coordinates")[0];
		var location = convertObject(coords, env);
		return new ol.geom.Point(location);
	};

	rules["geoff.geom:LineString"] = function(domNode, env) {
		var coords = coordsArray(domNode, env);
		return new ol.geom.LineString(coords);
	};

	rules["geoff.geom:Polygon"] = function(domNode, env) {
		var coords = coordsArray(domNode, env);
		return new ol.geom.Polygon(coords);
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

		var sourceProj = attrValue(domNode, "projectionCode");

		if (sourceProj != null) {
			var viewProjection = env.view.getProjection();
			var viewProjectionCode = viewProjection.getCode();
			coords = ol.proj.transform(coords, sourceProj, viewProjectionCode);
		}

		return coords;
	};

	function coordsArray(domNode, env) {
		var coords = elements(domNode, "coordinates");
		var ret = new Array();

		for (var i = 0; i < coords.length; i++) {
			var coord = coords[i];
			var location = convertObject(coord, env);
			ret[i] = location;
		}

		return ret;
	}

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

	function idOf(domNode) {
		var id = domNode.getAttribute("id");

		if (id == null) {
			var ctx = domNode;

			id = "";
			var doc = domNode.ownerDocument;

			while (ctx != null && ctx != doc) {
				var parent = ctx.parentNode;
				var index = parent == null ? 0 : Array.prototype.indexOf.call(
						parent.childNodes, ctx);
				id = ctx.nodeName + "[" + index + "]/" + id;
				ctx = parent;
			}

			id = "xpath:/" + id;
		}

		return id;
	}

	function attrValue(domNode, attrName) {
		return domNode.getAttribute(attrName);
	}

	function execRule(nsPrefix, tagName, contextNode, env) {
		var ruleName = nsPrefix + ":" + tagName;
		return rules[ruleName](contextNode, env);
	}

	function convertKeyValueProperties(domCollection, env) {
		var props = {};

		for (var i = 0; i < domCollection.length; i++) {
			var prop = domCollection[i];
			var key = attrValue(prop, "key");
			var value = attrValue(prop, "value");
			props[key] = value;
		}

		return props;
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
				var $div = $("<div id='" + divId + "' class='"
						+ DEFAULT_MAP_DIV_ID + "'></div>");
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

	function eventTriggered(evtName, evtParams) {
		// not implemented in standalone mode
		// this is supposed to be overridden in the SWT environment for
		// communication purposes

		// may also be used to enable client/server communication
	}

	function ol3Map() {
		return $('#' + DEFAULT_MAP_DIV_ID).data('ol3Map');
	}

	function printMap(token) {
		var map = ol3Map();
		map.once('postcompose', function(event) {
			var canvas = event.context.canvas;
			var data = canvas.toDataURL('image/png');
			geoff.eventTriggered("mapPrinted", [ token, data ]);
		});
		map.renderSync();
	}

	function editingMode(editingMode) {
		var map = geoff.ol3Map();
		map.switchEditingMode(editingMode);
	}

	// this is the API object which can be accessed via the global
	// 'window.geoff' variable
	target.geoff = {
		loadFromUrl : loadFromUrl,
		loadFromXMLString : loadFromXMLString,
		eventTriggered : eventTriggered,
		ol3Map : ol3Map,
		eventHandlers : eventHandlers,
		printMap : printMap,
		editingMode : editingMode
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
