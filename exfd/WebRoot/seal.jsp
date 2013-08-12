<%@ page language="java" import="com.exfd.domain.Seal" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<title>易寻方达 - 搜铅封</title>
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
				<li><a href="container.html" class="color-link">搜箱</a></li>
				<li class="divider-vertical"></li>
				<li><a href="/ship.jsp" class="color-link">搜船</a></li>
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
		<div class="span9 logo-container">
			<form id="searchForm" class="form-inline">
				<input id="searchInput" type="text" class="input-xlarge search-box-input search-box-input-large" placeholder="搜铅封" name="code">
				<button type="submit" class="btn search-box-button">搜索</button>
			</form>
		</div>
	</div>

	<div id="map" class="map-container"></div>

	<% Seal seal = (Seal)request.getAttribute("seal"); %>
		
	<script type="text/javascript">
		$(function() {
			"use strict";
			
			var getInfoContent = function(code, location) {
				return '<div style="margin:0;line-height:20px;padding:2px;">\
					    <table>\
					    	<tr>\
					    		<th>铅封号:</th>\
					    		<td>' + code + '</td>\
					    	</tr>\
					    	<tr>\
					    		<th>地址:</th>\
					    		<td>' + location + '</td>\
					    	</tr>\
					    </table>\
		            </div>';
			};

			var init = function() {
				var map,
					self = this,
					code = '<%= (seal != null) ? seal.getCode() : "" %>',
					longitude = <%= (seal != null) ? seal.getLongitude() : null %>,
					latitude = <%= (seal != null) ? seal.getLatitude() : null %>,
					location = '<%=(seal != null) ? seal.getPoi() : "" %>';

				map = new EFINDER.Map('map');
				map.drawMarker(longitude, latitude, '搜铅封', getInfoContent(code, location));
				
				if (code !== '') {
					$('#searchInput').val(code);
				}

				$('#searchForm').submit(function(e) {
					e.preventDefault();
	
					code = encodeURIComponent($('#searchInput').val());
					
					if (!!code) {
						$.ajax({
							type: 'GET',
							url: '/servlet/TrackSealInfoServlet?code=' + code,
							dataType: 'json',
							cache: false,
							success: function(data) {
								if (!!data) {
									code = data.code;
									longitude = data.longitude;
									latitude = data.latitude;
									location = data.poi;
	
									map.drawMarker(longitude, latitude, '搜铅封', getInfoContent(code, location));
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
