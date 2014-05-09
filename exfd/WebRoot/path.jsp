<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<title>易寻方达 - 智能铅封</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="css/baidu_searchinfowindow.css" />
   <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" />
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
   <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
   <script type="text/javascript" src="js/dateformat.js"></script>
	<!--[if lte IE 6]>
	<link href="css/bootstrap-ie6.min.css" rel="stylesheet">
	<link href="css/ie.css" rel="stylesheet">
	<script type="text/javascript" src="js/bootstrap-ie.js"></script>
	<![endif]-->
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=sBY8xc3rlpQuHyN5VispqMH1"></script>
	<script type="text/javascript" src="js/baidu/map_min.js"></script>
	<script type="text/javascript" src="js/baidu/searchinfowindow_min.js"></script>
   <script type="text/javascript" src="js/baidu/richmarker_min.js"></script>
   <script type="text/javascript" src="js/baidu/curveline.min.js"></script>
   <script type="text/javascript" src="js/baidu/LuShu_min.js"></script>
	<script type="text/javascript" src="js/map.js"></script>
	<script type="text/javascript" src="js/utils.js"></script>
</head>
<body>
	<div id="header" class="navbar">
		<div class="navbar-inner">
			<ul class="nav">
				<li><a href="/index.html">欢迎来到易寻方达！</a></li>
				<li class="divider-vertical"></li>
            <li><a href="/seal.jsp" class="color-link">智能锁</a></li>
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
         <div class="span2">
            <a href="/index.html">
					<img src="/img/logo.png" class="logo-img">
            </a>
         </div>
         <div>
            <form id="searchForm" class="form-inline" accept-charset="utf-8">
               <div>
                  <label for="searchInput" style="width: 70px; text-align: right;">铅封号：</label>
                  <input id="searchInput" type="text" class="input-xlarge search-path-box-input" placeholder="2127" name="code">
                  <button type="submit" class="btn search-path-box-button">搜索</button>
               </div>
               <div class="logo-container">
                  <label for="startTimePicker">起始时间：</label>
                  <div id="startTimePicker" class="input-append date">
                     <input data-format="dd/MM/yyyy hh:mm:ss" type="text"></input>
                     <span class="add-on">
                        <i class="icon-calendar" data-time-icon="icon-time" data-date-icon="icon-calendar">
                        </i>
                     </span>
                  </div>
                  <label for="endTimePicker">终止时间：</label>
                  <div id="endTimePicker" class="input-append date">
                     <input data-format="dd/MM/yyyy hh:mm:ss" type="text"></input>
                     <span class="add-on">
                        <i class="icon-calendar" data-time-icon="icon-time" data-date-icon="icon-calendar">
                        </i>
                     </span>
                  </div>
               </div>
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

	<div id="map" class="map-right-container"></div>

   <div class="marker-panel">
      <table id="markerTable" cellspacing="0" cellpadding="0" class="marker-list">
      </table>
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

	<% String code = (String)request.getAttribute("code"); %>
	
	<script type="text/javascript">
		$(function() {
			"use strict";

			var map = null,
             markerTable = $('#markerTable'),
				 searchInput = $('#searchInput');

         var getMarkerList = function(list) {
            var htmlStr, data, id, i;

            if (!list || list.length < 1) {
               return '';
            }

            htmlStr = '<tbody>';

            for (i = 0; i < list.length; i++) {
               data = list[i];

               htmlStr +=
                  '<tr class="marker-list-row" id="' + i + '">\
                     <td>\
                        <div class="relative-div marker-icon">\
                           <img src="img/marker.png" class="map-marker-icon">\
                           <div class="map-marker-text">' + String.fromCharCode(65 + (i % 26)) + '</div>\
                        </div>\
                     </td>\
                     <td>\
                        <div class="marker-detail">\
                           <p class="marker-title">' + data.code + '</p>\
                           <p>' + data.time + '</p>\
                        </div>\
                     </td>\
                  </tr>\
                  <tr style="height: 5px">\
                     <td></td>\
                     <td style="font-size: 0;"></td>\
                  </tr>';
            }
            
            htmlStr += '</tbody>';
            return htmlStr;
         };

         var initMarkerList = function() {
            var markerList = $('.marker-list-row'),
                id;
            
            markerList
               .on('mouseover', function() {
                  markerList.removeClass('focus');
                  $(this).addClass('focus');
               })
               .on('click', function() {
                  id = parseInt($(this).prop('id'));
                  map.selectMarker(id);
               });
         };
          
			var successCallback = function(data) {
				if (!!data) {
					EFINDER.utils.hideError();
					map.drawPath(data, 11);
               markerTable.html(getMarkerList(data));
               initMarkerList();
				} else {
					EFINDER.utils.showError('很抱歉，我们没有查找到铅封（' + searchInput.val() + '）的信息。');
				}
			};

			var submitForm = function() {
				var code, startPicker, start, endPicker, end, url, starts, ends;

				code = encodeURIComponent(searchInput.val() || '2127');
            url = '/servlet/TrackSealInfoServlet?code=' + code;
            // Start time.
            startPicker = $('#startTimePicker').data('datetimepicker');
            if (!!startPicker) {
               start = startPicker.getLocalDate();
            }
            start = start || new Date();
            starts = encodeURIComponent(start.Format("yyyy-MM-dd hh:mm:ss"));
            url += '&start=' + starts;
            // End time.
            endPicker = $('#endTimePicker').data('datetimepicker');
            if (!!endPicker) {
               end = endPicker.getLocalDate();
            }
            end = end || new Date();
            ends = encodeURIComponent(end.Format("yyyy-MM-dd hh:mm:ss"));
            url += '&end=' + ends;
            EFINDER.utils.load(url, successCallback);
			};

			var init = function() {
				var code = '';
				
				code = '<%= (code != null) ? code : "" %>';

				if (code !== '') {
					searchInput.val(code);
				}

				map = new EFINDER.Map('map');

				$('#searchForm').submit(function(e) {
					e.preventDefault();
					submitForm();
				});

            $('#startTimePicker').datetimepicker({
               language: 'en'
            });
            
            $('#endTimePicker').datetimepicker({
               language: 'en'
            });
			};

			init();
		});
	</script>
</body>
</html>
