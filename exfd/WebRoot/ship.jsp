<%@ page language="java" pageEncoding="UTF-8"%>

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
	<!--[if lte IE 6]>
	<link href="css/bootstrap-ie6.min.css" rel="stylesheet">
	<link href="css/ie.css" rel="stylesheet">
	<script type="text/javascript" src="js/bootstrap-ie.js"></script>
	<![endif]-->
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
				<li><a href="/seal.jsp" class="color-link">智能铅封</a></li>
				<li class="divider-vertical"></li>
				<li><a href="/container.jsp" class="color-link">搜箱</a></li>
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
               <img src="img/logo.png" width="100px" height="70px">
            </a>
         </div>
         <div class="span7 logo-container relative-div">
            <form id="searchForm" class="form-inline" accept-charset="utf-8">
               <input id="searchInput" type="text" class="input-xlarge search-box-input" placeholder="中远鞍钢" name="code">
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
         <div class="busy-cursor-div">
            <img src="img/loading.gif" id="loadingIndicator" class="hide"/>
         </div>
      </div>
	</div>

	<div id="errorDiv" class="alert alert-error hide">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<span id="errorMsg"></span>
	</div>

	<div id="map" class="map-small-container"></div>

   <div class="map-table">
      <div class="panel-title map-title">
         <h2>船舶详情</h2>
      </div>
      <table id="mapTable" class="table table-bordered"></table>
   </div>

	<footer class="footer footer-margin">
		<div class="container">
			<ul class="footer-links">
	    	   <li><a href="/partners.html">合作伙伴</a></li>
			   <li><a href="/sitemap/about.html">联系我们</a></li>
			   <li><a href="javascript:void(0);">网站地图</a></li>
			   <li><a href="/sitemap/legal.html">法律声明</a></li>
			   <span class="float-right footer-license">版权所有 © 北京易寻方达科技有限公司。  京ICP备13009564号</span>
			</ul>
		</div>
	</footer>

	<% 
		String code = (String)request.getAttribute("code");
		String type = (String)request.getAttribute("type");
	%>
		
	<script type="text/javascript">
		$(function() {
			"use strict";

			var map = null,
				 searchInput = $('#searchInput'),
             mapTable = $('#mapTable');
			
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

         var getShipDetails = function(ship) {
            return '<tbody>\
                   <tr>\
                     <th>ID号</th>\
                     <td>' + ship.shipid + '</td>\
                     <th>更新时间</th>\
                     <td>' + ship.updatetime + '</td>\
                   </tr>\
                   <tr>\
                     <th>英文船名</th>\
                     <td>' + ship.shipname + '</td>\
                     <th>中文船名</th>\
                     <td>' + ship.shipnamecn + '</td>\
                  </tr>\
                  <tr>\
                     <th>MMSI</th>\
                     <td>' + ship.mmsi + '</td>\
                     <th>IMO</th>\
                     <td>' + ship.imo + '</td>\
                  </tr>\
                  <tr>\
                     <th>呼号</th>\
                     <td>' + ship.callsign + '</td>\
                     <th>船籍</th>\
                     <td>' + ship.shipflag + '</td>\
                  </tr>\
                  <tr>\
                     <th>船舶类型</th>\
                     <td>' + ship.shiptype + '</td>\
                     <th>船长</th>\
                     <td>' + ship.shiplength + '</td>\
                  </tr>\
                  <tr>\
                     <th>船宽</th>\
                     <td>' + ship.shipwidth + '</td>\
                     <th>吃水</th>\
                     <td>' + ship.draft + '</td>\
                  </tr>\
                  <tr>\
                     <th>报位时间</th>\
                     <td>' + ship.gpstime + '</td>\
                     <th>报位方式</th>\
                     <td>' + ship.reporttype + '</td>\
                  </tr>\
                  <tr>\
                     <th>经度</th>\
                     <td>' + ship.longitude + '</td>\
                     <th>纬度</th>\
                     <td>' + ship.latitude + '</td>\
                  </tr>\
                  <tr>\
                     <th>航速</th>\
                     <td>' + ship.speed + '</td>\
                     <th>航向</th>\
                     <td>' + ship.direction + '</td>\
                  </tr>\
                  <tr>\
                     <th>航首向</th>\
                     <td>' + ship.truehending + '</td>\
                     <th>航行状态</th>\
                     <td>' + ship.state + '</td>\
                  </tr>\
                  <tr>\
                  </tr>\
               </tbody>';
         }; 

			var successCallbck = function(data) {
				if (!!data) {
					EFINDER.utils.hideError();
					map.drawMarker(data.lon, data.lat, '搜船', getInfoContent(data), 5, {
						image : 'img/cargo-ship.png',
						width: 120,
						height: 106,
						anchorWidth: 60,
						anchorHeight: 53
					});
               mapTable.html(getShipDetails(data));
				} else {
					EFINDER.utils.showError('很抱歉，我们没有查找到航船（' +
						$("#searchForm input[type='radio']:checked").val() + '：' +
						searchInputsearchInput.val() + '）的信息。');
				}
			};

			var submitForm = function() {
				var code, type, url;

				code = encodeURIComponent(searchInput.val() || '中远鞍钢');
				if (!!code) {
					type = encodeURIComponent($("#searchForm input[type='radio']:checked").val());
					url = '/servlet/TrackShipInfoServlet?code=' + code + '&type=' + type;
					
					EFINDER.utils.load(url, successCallbck);
				}
			};

			var init = function() {
				var url,
					code = '',
					type = 'name',
					filter = '';
				
				code = '<%= (code != null) ? code : "" %>';
				type = '<%= (type != null) ? type : "" %>';

				if (code !== '') {
					searchInput.val(code);
				}
				if (type !== '') {
					filter = '[value=' + type + ']';
					$('#searchForm input[name="type"]').filter(filter).attr('checked', true);
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
