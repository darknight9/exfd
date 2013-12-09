window.EFINDER = window.EFINDER || {};

(function($, EFINDER, undefined) {
   "use strict";

   EFINDER.Map = function(div) {
      this.map = null;
      this.zoom = 15;
      this.markers = [];
      this.selected = -1;
      this.data = null;

      if (!!div) {
         this.map = new BMap.Map(div);
         // Scale controle.
         this.map.addControl(new BMap.NavigationControl());
         // Proportion control.
         this.map.addControl(new BMap.ScaleControl());
         // Overview control.
         this.map.addControl(new BMap.OverviewMapControl({isOpen:false, anchor: BMAP_ANCHOR_TOP_RIGHT}));
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
      this.zoom = zoom || 15;

      // Center around point.
      point = new BMap.Point(longitude, latitude);
      this.map.centerAndZoom(point, this.zoom);
   };

   EFINDER.Map.prototype.addMarker = function(longitude, latitude, title, content, text) {
      var point,
          self = this,
          div = '',
          marker;

      if (!this.map || !longitude || !latitude) {
         return;
      }

      div = '<div class="map-marker-div">\
               <img src="../img/marker.png" class="map-marker-icon">\
               <div class="map-marker-text">' + text + '</div>\
            </div>';
      
      // Draw a marker at the point.
      point = new BMap.Point(longitude, latitude);
      marker = new BMapLib.RichMarker(div, point, {
         "anchor": new BMap.Size(-17, -20),
         "enableDragging": false
      });
      this.markers.push(marker);
      
      // Create search info window.
      marker.infoWindow = this.createInfoWindow(title, content);
      marker.addEventListener("click", function(e){
         this.infoWindow.open(this.getPosition());
      });
      
      this.map.addOverlay(marker);
   };

   EFINDER.Map.prototype.clear = function() {
      var marker, overlay, length, i;

      length = this.markers.length;
      for (i = 0; i < length; i++) {
         marker = this.markers[i];
         overlay = marker.infoWindow;
         this.removeOverlay(marker);
         this.removeOverlay(overlay);
      }
      this.markers.length = 0;
   };

   EFINDER.Map.prototype.removeOverlay = function(overlay) {
      if (!this.map || !overlay) {
         return;
      }

      this.map.removeOverlay(overlay);
   };

   EFINDER.Map.prototype.createInfoWindow = function(title, content) {
      // Create info window.
      return new BMapLib.SearchInfoWindow(this.map, content, {
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

   EFINDER.Map.prototype.drawMarkers = function(markers, zoom) {
      var longitude,
          latitude,
          marker,
          code,
          i;

      // Clear the present marker.
      this.clear();

      this.data = markers;      
      if (!!markers && markers.length > 0) {
         // Center around the first marker
         longitude = markers[0].longitude;
         latitude = markers[0].latitude;
         this.selected = 0;
      }
      // Center around point.
      this.init(longitude, latitude, zoom);
      
      for (i = 0; i < markers.length; i++) {
         // Draw a marker at the point.
         marker = markers[i];
         code = 65 + (i % 26);
         this.addMarker(marker.longitude, marker.latitude, 
            marker.title, marker.content, String.fromCharCode(code));
      }
   };

   EFINDER.Map.prototype.selectMarker = function(index) {
      var selectedMarker,
          point;

      if (index < 0 || index >= this.markers.length) {
         return;
      }

      selectedMarker = this.data[index];             
      // Center around point.
      this.init(selectedMarker.longitude, selectedMarker.latitude, this.zoom);
   };

}(window.jQuery, window.EFINDER));
