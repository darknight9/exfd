<%@ page language="java" import="com.exfd.domain.Container" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<title>易寻方达-搜箱</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/utils.js"></script>
</head>
<body>
	<div class="main-wrap">
		<div id="header" class="navbar">
			<div class="navbar-inner">
				<ul class="nav">
					<li><a href="/index.html">欢迎来到易寻方达！</a></li>
					<li class="divider-vertical"></li>
					<li><a href="/seal.jsp" class="color-link">搜铅封</a></li>
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
					<input id="searchInput" type="text" class="input-xlarge search-box-input search-box-input-large" placeholder="搜箱" name="code">
					<button type="submit" class="btn search-box-button">搜索</button>
				</form>
			</div>
		</div>

		<div id="tableContainer" class="container table-container"></div>
	</div>

	<footer class="footer">
		<div class="container">
			<ul class="footer-links">
			    <li><a href="#">关于易方寻达</a></li>
			    <li><a href="#">合作伙伴</a></li>
			    <li><a href="#">营销中心</a></li>
			    <li><a href="#">联系我们</a></li>
			    <li><a href="#">网站地图</a></li>
			    <li><a href="#">法律声明</a></li>
			    <span class="float-right footer-license">© 2013 efinder.com.cn 版权所有</span>
			</ul>
		</div>
	</footer>

	<% 
		String code = (String)request.getAttribute("code");
	%>

	<script type="text/javascript">
		$(function() {
			"use strict";

			var successCallback = function(data) {
				var i, obj,
					htmlStr = '';

				if (!!data) {
					for (i = 0; i < data.length; i++) {
						obj = data[i];
						if (obj.header) {
							if (htmlStr !== '') {
								htmlStr += '</table></div>';	
							} 

							htmlStr += '<div class="table-div">\
								<span class="table-header">物流状态</span>\
								<table class="table table-striped">\
								<tr>\
									<th>时间</th>\
									<th>' + obj.event + '</th>\
									<th>' + obj.location + '</th>\
									<th>' + obj.vessel + '</th>\
									<th>' + obj.voyage + '</th>\
								</tr>';
						} else {
							htmlStr += '<tr>\
								<td>' + obj.time + '</td>\
								<td>' + obj.event + '</td>\
								<td>' + obj.location + '</td>\
								<td>' + obj.vessel + '</td>\
								<td>' + obj.voyage + '</td>\
							</tr>';
						}
					}
				}

				if (htmlStr !== '') {
					htmlStr += '</table></div>';
				}

				$('#tableContainer').html(htmlStr);
			};

			var submitForm = function() {
				var code = encodeURIComponent($('#searchInput').val()),
				    url = '/servlet/TrackContainerInfoServlet?code=' + code;
				
				EFINDER.load(url, successCallback);
			};

			var init = function() {
				var code = '';
				
				code = '<%= (code != null) ? code : "" %>';

				if (code !== '') {
					$('#searchInput').val(code);
					submitForm();
				}

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
