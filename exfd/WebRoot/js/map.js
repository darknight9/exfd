window.EFINDER = window.EFINDER || {};

(function($, EFINDER, undefined) {
	"use strict";

	EFINDER.Map = function(div) {
		this.map = null;
		this.marker = null;
		this.infoWindow = null;

		if (!!div) {
			this.map = new BMap.Map(div);
			// Scale controle.
			this.map.addControl(new BMap.NavigationControl());
			// Proportion control.
			this.map.addControl(new BMap.ScaleControl());
			// Overview control.
			this.map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_TOP_RIGHT}));
		}
	};

	EFINDER.Map.prototype.init = function(longitude, latitude, zoom) {
		var point;

		if (!this.map) {
			return;
		}

		// Default center point.
		longitude = longitude || 116.404;
		latitude = latitude || 39.915;
		zoom = zoom || 15;

		// Center around point.
		point = new BMap.Point(longitude, latitude);
		this.map.centerAndZoom(point, zoom);
	};

	EFINDER.Map.prototype.addMarker = function(longitude, latitude, title, content, icon) {
		var point,
			mapIcon,
			self = this;

		if (!this.map || !longitude || !latitude) {
			return;
		}

		icon = icon || {
			image : 'img/marker.png',
			width : 30,
			height : 68,
			anchorWidth : 15,
			anchorHeight : 68
		};

		// Draw a marker at the point.
		point = new BMap.Point(longitude, latitude);
		mapIcon = new BMap.Icon(icon.image, new BMap.Size(icon.width, icon.height), {
			icon : new BMap.Size(icon.iconWidth, icon.iconHeight)
		});
		this.marker = new BMap.Marker(point, {icon: mapIcon});
		this.marker.addEventListener("click", function(e){
			// Create search info window.
			self.addInfoWindow(title, content);
			
			if (!!self.infoWindow) {
				self.infoWindow.open(self.marker);
			}
		});
		this.map.addOverlay(this.marker);
	};

	EFINDER.Map.prototype.clearMarker = function() {
		if (!!this.map && !!this.marker) {
			this.map.removeOverlay(this.marker); 
		}
	};

	EFINDER.Map.prototype.addInfoWindow = function(title, content) {
		// Create info window.
		this.infoWindow = new BMapLib.SearchInfoWindow(this.map, content, {
			title  : title,           //标题
			panel  : "panel",         //检索结果面板
			enableAutoPan : true,     //自动平移
			width  : '280px',
			searchTypes   :[
				BMAPLIB_TAB_SEARCH,   //周边检索
				BMAPLIB_TAB_TO_HERE,  //到这里去
				BMAPLIB_TAB_FROM_HERE //从这里出发
			]
		});
	};

	EFINDER.Map.prototype.drawMarker = function(longitude, latitude, title, content, zoom, icon) {
		// Center around point.
		this.init(longitude, latitude, zoom);
		// Clear the present marker.
		this.clearMarker();
		// Draw a marker at the point.
		this.addMarker(longitude, latitude, title, content, icon);
	};

}(window.jQuery, window.EFINDER));
