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
			<div class="busy-cursor-div">
				<img src="img/loading.gif" id="loadingIndicator" class="hide"/>
			</div>
		</div>

		<div id="errorDiv" class="alert alert-error hide">
			<button type="button" class="close" data-dismiss="alert">×</button>
	        <span id="errorMsg"></span>
	    </div>

		<div class="container" style="width: 80% !important;">
			<div class="row-fluid">
				<div class="span2 thumbnail-container">
					<ul class="thumbnails">
					  <li>
					    <div class="thumbnail thumbnail-div">
					      <img data-src="holder.js/300x200" alt="" src="img/container-icon.png" class="thumbnail-img">
					      <p id="containerId" class="thumbnail-label"></p>
					      <p id="containerSize" class="thumbnail-label"></p>
					      <p id="containerCompany" class="thumbnail-label"></p>
					    </div>
					  </li>
					</ul>
				</div>

				<div id="tableContainer" class="offset1 span9 table-container"></div>
			</div>
		</div>
	</div>

	<footer class="footer footer-margin">
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

	<% 
		String code = (String)request.getAttribute("code");
	%>

	<script type="text/javascript">
		$(function() {
			"use strict";

			var searchInput = $('#searchInput');

			var successCallback = function(data) {
				var i, obj,
					htmlStr = '';

				if (!!data) {
					$('#containerId').text('箱号： ' + data.code);
					$('#containerSize').text('大小： ' + data.size);
					$('#containerCompany').text('公司： ' + data.company);

					htmlStr += '<div class="table-div">\
						<span class="table-header">物流状态</span>\
						<table class="table table-striped">';

					if (!!data.statusTitle) {
						htmlStr += '<tr>\
								<th>' + data.statusTitle.time + '</th>\
								<th>' + data.statusTitle.event + '</th>\
								<th>' + data.statusTitle.location + '</th>';
						
						if (!!data.statusTitle.vessel) {
							htmlStr += '<th>' + data.statusTitle.vessel + '</th>';
						}
						if (!!data.statusTitle.voyage) {
							htmlStr += '<th>' + data.statusTitle.voyage + '</th>';
						}

						htmlStr += '</tr>';
					}

					if (!!data.statusRecord) {
						htmlStr += '<tr>\
								<td>' + data.statusRecord.time + '</td>\
								<td>' + data.statusRecord.event + '</td>\
								<td>' + data.statusRecord.location + '</td>';

						if (!!data.statusTitle.vessel) {
							htmlStr += '<td>' + data.statusRecord.vessel + '</td>';
						}
						if (!!data.statusTitle.voyage) {
							htmlStr += '<td>' + data.statusRecord.voyage + '</td>';
						}
						
						htmlStr += '</tr>';
					}

					htmlStr += '</table></div>\
						<div class="table-div">\
						<span class="table-header">历史记录</span>\
						<table class="table table-striped">';

					if (!!data.historyTitle) {
						htmlStr += '<tr>\
								<th>' + data.historyTitle.time + '</th>\
								<th>' + data.historyTitle.event + '</th>\
								<th>' + data.historyTitle.location + '</th>';
						
						if (!!data.historyTitle.vessel) {
							htmlStr += '<th>' + data.historyTitle.vessel + '</th>';
						}
						if (!!data.historyTitle.voyage) {
							htmlStr += '<th>' + data.historyTitle.voyage + '</th>';
						}
						
						htmlStr += '</tr>';
					}
					
					if (!!data.historyRecords) {
						for (i = 0; i < data.historyRecords.length; i++) {
							obj = data.historyRecords[i];
							if (!!obj) {
								htmlStr += '<tr>\
									<td>' + obj.time + '</td>\
									<td>' + obj.event + '</td>\
									<td>' + obj.location + '</td>';
						
								if (!!data.historyTitle.vessel) {
									htmlStr += '<td>' + obj.vessel + '</td>';
								}
								if (!!data.historyTitle.voyage) {
									htmlStr += '<td>' + obj.voyage + '</td>';
								}
						
								htmlStr += '</tr>';
							}
						}
					}

					htmlStr += '</table></div>';

					EFINDER.utils.hideError();
				} else {
					EFINDER.utils.showError('很抱歉，我们没有查找到箱号（' + searchInput.val() + '）的信息。');
				}

				$('#tableContainer').html(htmlStr);
			};

			var submitForm = function() {
				var code = encodeURIComponent(searchInput.val()),
				    url = '/servlet/TrackContainerInfoServlet?code=' + code;
				
				EFINDER.utils.load(url, successCallback);
			};

			var init = function() {
				var code = '';
				
				code = '<%= (code != null) ? code : "" %>';

				if (code !== '') {
					searchInput.val(code);
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
