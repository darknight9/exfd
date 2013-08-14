<%@ page language="java" pageEncoding="UTF-8"%>

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
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css" />
	<script type="text/javascript" src="js/map.js"></script>
	<script type="text/javascript" src="js/utils.js"></script>
</head>
<body>
	<div id="header" class="navbar">
		<div class="navbar-inner">
			<ul class="nav">
				<li><a href="/index.html">欢迎来到易寻方达！</a></li>
				<li class="divider-vertical"></li>
				<li><a href="/container.jsp" class="color-link">搜箱</a></li>
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
		<div class="span2 offset2">
			<a href="/index.html">
				<img src="img/logo.png" width="100px" height="70px">
			</a>
		</div>
		<div class="span9 logo-container">
			<form id="searchForm" class="form-inline" accept-charset="utf-8">
				<input id="searchInput" type="text" class="input-xlarge search-box-input search-box-input-large" placeholder="搜铅封" name="code">
				<button type="submit" class="btn search-box-button">搜索</button>
			</form>
		</div>
		<div class="busy-cursor-div">
			<img src="img/loading.gif" id="loadingIndicator" class="hide"/>
		</div>
	</div>

	<div id="errorDiv" class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
        <span id="errorMsg"></span>
    </div>

	<div id="map" class="map-container"></div>

	<footer class="footer">
		<div class="container">
			<ul class="footer-links">
			    <li><a href="#">关于易寻方达</a></li>
			    <li><a href="#">合作伙伴</a></li>
			    <li><a href="#">营销中心</a></li>
			    <li><a href="#">联系我们</a></li>
			    <li><a href="#">网站地图</a></li>
			    <li><a href="#">法律声明</a></li>
			    <span class="float-right footer-license">© 2013 efinder.com.cn 版权所有</span>
			</ul>
		</div>
	</footer>

	<% String code = (String)request.getAttribute("code"); %>
		
	<script type="text/javascript">
		$(function() {
			"use strict";

			var map = null,
				searchInput = $('#searchInput');
			
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

			var successCallbck = function(data) {
				if (!!data) {
					EFINDER.utils.hideError();
					map.drawMarker(data.longitude, data.latitude, '搜铅封', getInfoContent(data.code, data.poi));
				} else {
					EFINDER.utils.showError('很抱歉，我们没有查找到铅封（' + searchInput.val() + '）的信息。');
				}
			};

			var submitForm = function() {
				var code, url;

				code = encodeURIComponent(searchInput.val());
				if (!!code) {
					url = '/servlet/TrackSealInfoServlet?code=' + code;

					EFINDER.utils.load(url, successCallbck);
				}
			};

			var init = function() {
				var code = '';

				
				code = '<%= (code != null) ? code : "" %>';

				if (code !== '') {
					searchInput.val(code);
				}

				map = new EFINDER.Map('map');
				submitForm();

				$('#searchForm').submit(function(e) {
					e.preventDefault();
					submitForm();
				})
			};

			init();
		});
	</script>
</body>
</html>
