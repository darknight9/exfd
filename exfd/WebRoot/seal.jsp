<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<title>易寻方达 - 智能锁</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="css/baidu_searchinfowindow.css" />
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<!--[if lte IE 6]>
	<link href="css/bootstrap-ie6.min.css" rel="stylesheet">
	<link href="css/ie.css" rel="stylesheet">
	<script type="text/javascript" src="js/bootstrap-ie.js"></script>
	<![endif]-->
	<script type="text/javascript" src="js/baidu/map_min.js"></script>
	<script type="text/javascript" src="js/baidu/searchinfowindow_min.js"></script>
   <script type="text/javascript" src="js/baidu/richmarker_min.js"></script>
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
            <li><a href="javascript:void(0);" class="color-highlight">VIP入口</a></li>
            <li class="divider-vertical"></li>
            <li><a href="javascript:void(0);">注册</a></li>
         </ul>
      </div>
   </div>

   <div class="search-site-bar">
      <div class="container">
         <div class="span2 offset1">
            <a href="/index.html">
            <img src="/img/logo.png" class="logo-img">
            </a>
         </div>
         <div class="span7 logo-container">
            <form id="searchForm" class="form-inline" accept-charset="utf-8">
               <input id="searchInput" type="text" class="input-xlarge search-box-input" placeholder="2127" name="code">
               <button type="submit" class="btn search-box-button">搜索</button>
            </form>
         </div>
         <div class="busy-cursor-div">
            <img src="img/loading.gif" id="loadingIndicator" class="hide"/>
         </div>
      </div>
   </div>

   <div id="errorDiv" class="alert alert-error hide">
      <button type="button" class="close" data-dismiss="alert">×</button>
      <span id="errorMsg"></span>
   </div>

   <div id="map" class="map-container"></div>

   <footer class="footer">
      <div class="container">
         <ul class="footer-links">
            <li><a href="/partners.html">合作伙伴</a></li>
            <li><a href="/sitemap/about.html">联系我们</a></li>
            <li><a href="javascript:void(0);">网站地图</a></li>
            <li><a href="/sitemap/legal.html">法律声明</a></li>
            <span class="float-right footer-license">版权所有 © 北京易寻方达科技有限公司  京ICP备13009564号</span>
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
					map.drawMarkers([{
	                   longitude: data.longitude,
	                   latitude: data.latitude,
	                   title: '智能锁',
	                   content: getInfoContent(data.code, data.poi)
	                }]);
				} else {
					EFINDER.utils.showError('很抱歉，我们没有查找到铅封（' + searchInput.val() + '）的信息。');
				}
			};

			var submitForm = function() {
				var code, url;

				code = searchInput.val();
				if (!code) {
					code = '2127';
					searchInput.val('2127');
				}
				
				url = '/servlet/TrackSealInfoServlet?code=' + encodeURIComponent(code);
				EFINDER.utils.load(url, successCallbck);
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
				});
			};

			init();
		});
	</script>
</body>
</html>
