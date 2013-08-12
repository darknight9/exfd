<%@ page language="java" import="com.exfd.domain.Ship" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<title>易寻方达 - 搜船</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css" />
	<script type="text/javascript" src="js/map.js"></script>
	<script type="text/javascript" src="js/logger.js"></script>
</head>
<body>
	<div id="header" class="navbar">
		<div class="navbar-inner">
			<ul class="nav">
				<li><a href="#">欢迎来到易寻方达！</a></li>
				<li class="divider-vertical"></li>
				<li><a href="/seal.jsp" class="color-link">搜铅封</a></li>
				<li class="divider-vertical"></li>
				<li><a href="ship.html" class="color-link">搜箱</a></li>
			</ul>
			<ul class="nav float-right">	
				<li><a href="#" class="color-highlight">登录</a></li>
				<li class="divider-vertical"></li>
				<li><a href="#">注册</a></li>
			</ul>
		</div>
	</div>

	<div class="search-site-bar">
		<div class="span2">
			<a href="www.efinder.com.cn">
				<img src="img/logo.png" width="100px" height="70px">
			</a>
		</div>
		<div class="span9 logo-container relative-div">
			<form id="searchForm" class="form-inline">
				<input id="searchInput" type="text" class="input-xlarge search-box-input search-box-input-large" placeholder="搜船" name="code">
				<span class="ship-radio-group">
					<label class="radio inline">
					    <input type="radio" name="type" value="name" checked> 船名
					</label>
					<label class="radio inline">
					    <input type="radio" name="type" value="mmsi"> MMSI
					</label>
					<label class="radio inline">
					    <input type="radio" name="type" value="imo"> IMO
					</label>
					<label class="radio inline">
					    <input type="radio" name="type" value="callsign"> 呼码
					</label>
				</span>
				<button type="submit" class="btn search-box-button">搜索</button>
			</form>
		</div>
	</div>

	<div id="map" class="map-container"></div>

	<% 
		Ship ship = (Ship)request.getAttribute("ship");
		String code = (String)request.getAttribute("code");
		String type = (String)request.getAttribute("type");
	%>
		
	<script type="text/javascript">
		$(function() {
			"use strict";
			
			var getInfoContent = function(ship) {
				return '<div style="margin:0;line-height:20px;padding:2px;">\
					    <table>\
					    	<tr>\
					    		<th>船名:</th>\
					    		<td>' + (ship.shipnamecn || ship.shipname) + '</td>\
					    	</tr>\
					    	<tr>\
					    		<th>MMSI:</th>\
					    		<td>' + ship.mmsi + '</td>\
					    	</tr>\
					    </table>\
		            </div>';
			};

			var init = function() {
				var map,
					self = this,
					code = '',
					type = 'name',
					filter = '',
					tShip = {};
				
				code = '<%= (code != null) ? code : "" %>';
				type = '<%= (type != null) ? type : "" %>';
				tShip.shipname = '<%= (ship != null) ? ship.getShipname() : "" %>';
				tShip.shipnamecn = '<%= (ship != null) ? ship.getShipnamecn() : "" %>';
				tShip.lon = <%= (ship != null) ? ship.getLon() : null %>;
				tShip.lat = <%= (ship != null) ? ship.getLat() : null %>;
				tShip.mmsi = '<%= (ship != null) ? ship.getMmsi() : null %>';

				map = new EFINDER.Map('map');
				map.drawMarker(tShip.lon, tShip.lat, '搜船', getInfoContent(tShip));
				
				if (code !== '') {
					$('#searchInput').val(code);
				}
				if (type !== '') {
					filter = '[value=' + type + ']';
					$('#searchForm input[name="type"]').filter(filter).attr('checked', true);
				}

				$('#searchForm').submit(function(e) {
					e.preventDefault();
	
					code = encodeURIComponent($('#searchInput').val());
					type = encodeURIComponent($("#searchForm input[type='radio']:checked").val());
					
					if (!!code && !!type) {
						$.ajax({
							type: 'GET',
							url: '/servlet/TrackShipInfoServlet?code=' + code + '&type=' + type,
							dataType: 'json',
							cache: false,
							success: function(data) {
								if (!!data) {
									tShip = data;
	
									map.drawMarker(tShip.lon, tShip.lat, '搜船', getInfoContent(tShip));
								}
							},
							error: function() {
								EFINDER.logger.error('Search Seal AJAX request failure!');
							}
						});
					}
				})
			};

			init();
		});
	</script>
</body>
</html>
