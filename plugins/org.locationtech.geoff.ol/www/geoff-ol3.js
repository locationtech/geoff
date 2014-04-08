/*******************************************************************************
 * Copyright (c) 2014 Erdal Karaca.
 * 
 * All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Erdal Karaca - initial API and implementation
 ******************************************************************************/

/***/
(function(target, $) {
	var rules = {};
	rules["geoff:GeoMap"] = function(domNode, env) {
		var layers = domNode.getElementsByTagName("layer");
		var view = domNode.getElementsByTagName("view")[0];
		var rendererHint = domNode.getAttribute("rendererHint");

		if (rendererHint != null) {
			rendererHint = rendererHint.toLowerCase();
		}

		return new ol.Map({
			renderers : ol.RendererHints.createFromQueryData(),
			layers : geoff.convertCollection(layers, env),
			view : geoff.convertObject(view, env)
		});
	};

	rules["geoff:View2D"] = function(domNode, env) {
		var center = domNode.getElementsByTagName("center")[0];
		var zoom = domNode.getAttribute("zoom");

		return new ol.View2D({
			center : geoff.convertObject(center, env),
			zoom : geoff.convertInt(zoom, env)
		});
	};

	rules["geoff.layer:Tile"] = function(domNode, env) {
		var source = domNode.getElementsByTagName("source")[0];

		return new ol.layer.Tile({
			source : geoff.convertObject(source, env)
		});
	};

	rules["geoff.source:OSM"] = function(domNode, env) {
		return new ol.source.OSM();
	};

	rules["geoff.source:MapQuestOpenAerial"] = function(domNode, env) {
		return new ol.source.MapQuestOpenAerial();
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
		var x = domNode.getAttribute("x") != null ? geoff.convertFloat(domNode
				.getAttribute("x"), env) : 0;
		var y = domNode.getAttribute("y") != null ? geoff.convertFloat(domNode
				.getAttribute("y"), env) : 0;
		var z = domNode.getAttribute("z") != null ? geoff.convertFloat(domNode
				.getAttribute("z"), env) : 0;
		return [ x, y, z ];
	};

	rules["geoff:Transformation"] = function(domNode, env) {
		var coords = rules["geoff:XYZLocation"](domNode, env);
		var sourceProj = domNode.getAttribute("sourceProjection");
		var targetProj = domNode.getAttribute("targetProjection");

		return ol.proj.transform(coords, sourceProj, targetProj);
	};

	target.geoff = {
		loadFromServer : function(/* String */token) {
			var url = "/geoff-maps?t=" + new Date().getMilliseconds()
					+ "&token=" + token;
			$.ajax({
				type : "GET",
				url : url,
				dataType : "xml",
				success : function(xml) {
					geoff.loadMaps(xml);
				}
			});
		},

		loadMaps : function(root) {
			var maps = root.getElementsByTagName("geoff:GeoMap");
			var env = {};

			if (maps.length == 1) {
				var domMap = maps[0];
				geoff.loadMap(domMap, "map", env);
			} else {
				for ( var i = 0; i < maps.length; i++) {
					var domMap = maps[i];
					var divId = "map" + i;
					var $div = $("<div id='" + divId + "' class='map'></div>");
					$div.appendTo($(domMap));
					geoff.loadMap(domMap, divId, env);
				}
			}
		},

		loadMap : function(domNode, divId, env) {
			// TODO set NS properly
			domNode.setAttribute("xsi:type", "geoff:GeoMap");
			var map = geoff.convertObject(domNode, env);
			map.setTarget(divId);
			map.updateSize();
		},

		convertCollection : function(domCollection, env) {
			var jArray = new Array();

			for ( var i = 0; i < domCollection.length; i++) {
				var jObject = geoff.convertObject(domCollection[i], env);
				jArray.push(jObject);
			}

			return jArray;
		},

		convertObject : function(domNode, env) {
			if (domNode == null) {
				return null;
			}

			var type = domNode.getAttribute("xsi:type");
			var rule = rules[type];

			if (rule == null) {
				console.log("undefined rule: " + type);
				return null;
			}

			return rule(domNode, env);
		},

		convertInt : function(value, env) {
			return parseInt(value);
		},

		convertFloat : function(value, env) {
			return parseFloat(value);
		}
	};

	target.geoff.standalone = false;

	$(document).ready(function() {
		if (window.geoff.standalone) {
			geoff.loadMaps(document);
		}
	});
}(window, jQuery));
