window.EFINDER = window.EFINDER || {};

(function($, EFINDER, undefined) {
   "use strict";

   EFINDER.Map = function(div) {
      this.map = null;
      this.zoom = 15;
      this.markers = [];
      this.selected = -1;
      this.data = null;
      this.path = null;

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
      this.map.enableScrollWheelZoom();
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
         if (!!overlay) {
            this.removeOverlay(overlay);
         }
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

   EFINDER.Map.prototype.drawPath = function(points, zoom, code) {
      var longitude,
          latitude,
          bPoints = [],
          startMarker,
          endMarker,
          self = this,
          icon,
          i;

      this.map.clearOverlays();
      this.markers.length = 0;

      if (!points || points.length <= 0) {
         return;
      }

      // Center around the first marker
      longitude = points[0].longitude;
      latitude = points[0].latitude;
      // Center around point.
      this.init(longitude, latitude, zoom);

      for (i = 0; i < points.length; i++) {
         bPoints.push(new BMap.Point(points[i].longitude,
            points[i].latitude));
      }

      this.path = new BMap.Polyline(bPoints, {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});
      this.map.addOverlay(this.path);
      
      // 调整zoom
      this.map.setViewport(bPoints);
    
      // Add start marker. 
      startMarker = '<div class="map-start-marker"></div>';
      startMarker = new BMapLib.RichMarker(startMarker, bPoints[0], {
         "anchor": new BMap.Size(-20, -30),
         "enableDragging": false
      });
      this.markers.push(startMarker);
      this.map.addOverlay(startMarker);
     
      // Add end marker. 
      endMarker = '<div class="map-end-marker"></div>';
      endMarker = new BMapLib.RichMarker(endMarker, bPoints[bPoints.length - 1], {
         "anchor": new BMap.Size(-20, -30),
         "enableDragging": false
      });
      this.markers.push(startMarker);
      this.map.addOverlay(endMarker);

      icon = new BMap.Icon("../img/marker_red_sprite.png", new BMap.Size(39, 25), {imageOffset: new BMap.Size(0, 0)});
      var lushu = new BMapLib.LuShu(this.map, bPoints, {
          defaultContent: code,
          icon: icon,
          speed: 100
      });
      lushu.start();
   };

   EFINDER.Map.prototype.run = function(points) {
      var icon = new BMap.Icon("../img/marker_red_sprite.png", new BMap.Size(39, 25), {imageOffset: new BMap.Size(0, 0)}),
          marker;

      if (!points || points.length <= 0) {
         return;
      }

      marker = new BMap.Marker(points[0],{icon:icon});
      this.map.addOverlay(marker);

      var setMarkerPos = function(i) {
         if (i < points.length) {
            marker.setPosition(points[i]);
            setTimeout(function() {
               i++;
               setMarkerPos(i);
            }, 500);
         }
      };
      
      setTimeout(function() {
         setMarkerPos(1);
      }, 500); 
   };


}(window.jQuery, window.EFINDER));
