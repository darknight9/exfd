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
	<!--[if lte IE 6]>
	<link href="css/bootstrap-ie6.min.css" rel="stylesheet">
	<link href="css/ie.css" rel="stylesheet">
	<script type="text/javascript" src="js/bootstrap-ie.js"></script>
	<![endif]-->
	<script type="text/javascript" src="js/utils.js"></script>
</head>
<body>
	<div class="main-wrap">
		<div id="header" class="navbar">
			<div class="navbar-inner">
				<ul class="nav">
					<li><a href="/index.html">欢迎来到易寻方达！</a></li>
					<li class="divider-vertical"></li>
					<li><a href="/seal.jsp" class="color-link">智能铅封</a></li>
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
         <div class="container relative-div">
            <div class="span2 offset1">
               <a href="/index.html">
                  <img src="img/logo.png" width="100px" height="70px">
               </a>
            </div>
            <div class="span7 logo-container">
               <form id="searchForm" class="form-inline" accept-charset="utf-8">
                  <input id="searchInput" type="text" class="input-xlarge search-box-input" placeholder="CMAU1396117" name="code">
                  <input id="companyInput" type="hidden" name="company" value="default">
                  <button type="submit" class="btn search-box-button">搜索</button>
               </form>
            </div>
            <div class="busy-cursor-div">
               <img src="img/loading.gif" id="loadingIndicator" class="hide"/>
            </div>
            <div class="select-div">
               <span id="companyName" class="color-link">选择船公司</span>
               <a id="selectShipCompany" class="select-btn">更改 ></a>
            </div>
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

				<div id="companyContainer" class="offset2 span6 hide">
               <div class="panel-title">
                  <h2>请指定船公司：</h2>
               </div>
					
               <ul class="shipping-list">
						<li class="shipping-list-unit">
							<div class="shipping-list-clearfix">
								<div class="shipping-list-logo">
									<img src="/img/shipping/MAERSK.jpg" alt="MAERSK" width="76px" height="76px">
								</div>
								<div class="shipping-list-clearfix">
									<div class="shipping-list-check">
										<button type="submit" name="shipping" value="MAERSK" class="btn btn-info" data-name="马士基">确定</button>
									</div>
									<div class="shipping-list-middle">
										<div class="shipping-list-title">
											<a href="http://www.maerskline.com/">马士基</a>
										</div>
										<div class="shipping-list-text">
											Maersk Line
										</div>
									</div>
								</div>
							</div>
						</li>
						<li class="shipping-list-unit">
							<div class="shipping-list-clearfix">
								<div class="shipping-list-logo">
									<img src="/img/shipping/MSC.jpg" alt="MSC" width="76px" height="76px">
								</div>
								<div class="shipping-list-clearfix">
									<div class="shipping-list-check">
										<button type="submit" name="shipping" value="MSC" class="btn btn-info" data-name="地中海">确定</button>
									</div>
									<div class="shipping-list-middle">
										<div class="shipping-list-title">
											<a href="http://www.mscgva.ch/">地中海</a>
										</div>
										<div class="shipping-list-text">
											Mediterranean Shipping Company
										</div>
									</div>
								</div>
							</div>
						</li>
						<li class="shipping-list-unit">
							<div class="shipping-list-clearfix">
								<div class="shipping-list-logo">
									<img src="/img/shipping/CMA.jpg" alt="CMA" width="76px" height="76px">
								</div>
								<div class="shipping-list-clearfix">
									<div class="shipping-list-check">
										<button type="submit" name="shipping" value="CMA" class="btn btn-info" data-name="法国达飞">确定</button>
									</div>
									<div class="shipping-list-middle">
										<div class="shipping-list-title">
											<a href="http://www.cma-cgm.com/">法国达飞</a>
										</div>
										<div class="shipping-list-text">
											CMA CGM S.A.
										</div>
									</div>
								</div>
							</div>
						</li>
						<li class="shipping-list-unit">
							<div class="shipping-list-clearfix">
								<div class="shipping-list-logo">
									<img src="/img/shipping/EVERGREEN.jpg" alt="EVERGREEN" width="76px" height="76px">
								</div>
								<div class="shipping-list-clearfix">
									<div class="shipping-list-check">
										<button type="submit" name="shipping" value="EVERGREEN" class="btn btn-info" data-name="长荣海运">确定</button>
									</div>
									<div class="shipping-list-middle">
										<div class="shipping-list-title">
											<a href="http://www.evergreen-marine.com/">长荣海运</a>
										</div>
										<div class="shipping-list-text">
											Evergreen Marine Corporation
										</div>
									</div>
								</div>
							</div>
						</li>
						<li class="shipping-list-unit">
							<div class="shipping-list-clearfix">
								<div class="shipping-list-logo">
									<img src="/img/shipping/COSCO.jpg" alt="COSCO" width="76px" height="76px">
								</div>
								<div class="shipping-list-clearfix">
									<div class="shipping-list-check">
										<button type="submit" name="shipping" value="COSCO" class="btn btn-info" data-name="中远集团">确定</button>
									</div>
									<div class="shipping-list-middle">
										<div class="shipping-list-title">
											<a href="http://www.cosco.com/">中远集团</a>
										</div>
										<div class="shipping-list-text">
											China Ocean Shipping Company
										</div>
									</div>
								</div>
							</div>
						</li>
						<li class="shipping-list-unit">
							<div class="shipping-list-clearfix">
								<div class="shipping-list-logo">
									<img src="/img/shipping/APL.jpg" alt="APL" width="76px" height="76px">
								</div>
								<div class="shipping-list-clearfix">
									<div class="shipping-list-check">
										<button type="submit" name="shipping" value="APL" class="btn btn-info" data-name="美国总统轮船">确定</button>
									</div>
									<div class="shipping-list-middle">
										<div class="shipping-list-title">
											<a href="http://www.apl.com/">美国总统轮船</a>
										</div>
										<div class="shipping-list-text">
											Asia Pacific Marine Container Lines
										</div>
									</div>
								</div>
							</div>
						</li>
						<li class="shipping-list-unit">
							<div class="shipping-list-clearfix">
								<div class="shipping-list-logo">
									<img src="/img/shipping/CSAV.jpg" alt="CSAV" width="76px" height="76px">
								</div>
								<div class="shipping-list-clearfix">
									<div class="shipping-list-check">
										<button type="submit" name="shipping" value="CSAV" class="btn btn-info" data-name="南美邮轮">确定</button>
									</div>
									<div class="shipping-list-middle">
										<div class="shipping-list-title">
											<a href="http://www.csav.com/">南美邮轮</a>
										</div>
										<div class="shipping-list-text">
											CSAV Compañía Sud Americana de Vapores
										</div>
									</div>
								</div>
							</div>
						</li>
						<li class="shipping-list-unit">
							<div class="shipping-list-clearfix">
								<div class="shipping-list-logo">
									<img src="/img/shipping/HANJIN.jpg" alt="HANJIN" width="76px" height="76px">
								</div>
								<div class="shipping-list-clearfix">
									<div class="shipping-list-check">
										<button type="submit" name="shipping" value="HANJIN" class="btn btn-info" data-name="韩进">确定</button>
									</div>
									<div class="shipping-list-middle">
										<div class="shipping-list-title">
											<a href="http://www.hanjin.com/">韩进</a>
										</div>
										<div class="shipping-list-text">
											Hanjin Shipping Co. Ltd
										</div>
									</div>
								</div>
							</div>
						</li>
						<li class="shipping-list-unit">
							<div class="shipping-list-clearfix">
								<div class="shipping-list-logo">
									<img src="/img/shipping/CHINASHIPPING.jpg" alt="CHINASHIPPING" width="76px" height="76px">
								</div>
								<div class="shipping-list-clearfix">
									<div class="shipping-list-check">
										<button type="submit" name="shipping" value="CHINASHIPPING" class="btn btn-info" data-name="中海集装箱运输股份有限公司">确定</button>
									</div>
									<div class="shipping-list-middle">
										<div class="shipping-list-title">
											<a href="http://www.cscl.com.cn/">中海集装箱运输股份有限公司</a>
										</div>
										<div class="shipping-list-text">
											China Shipping Container Lines
										</div>
									</div>
								</div>
							</div>
						</li>
						<li class="shipping-list-unit">
							<div class="shipping-list-clearfix">
								<div class="shipping-list-logo">
									<img src="/img/shipping/MOL.jpg" alt="MOL" width="76px" height="76px">
								</div>
								<div class="shipping-list-clearfix">
									<div class="shipping-list-check">
										<button type="submit" name="shipping" value="MOL" class="btn btn-info" data-name="商船三井">确定</button>
									</div>
									<div class="shipping-list-middle">
										<div class="shipping-list-title">
											<a href="http://www.cscl.com.cn/">商船三井</a>
										</div>
										<div class="shipping-list-text">
											Mitsui O.S.K. Lines
										</div>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
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
	%>

	<script type="text/javascript">
		$(function() {
			"use strict";

			var searchInput = $('#searchInput'),
             companyInput = $('#companyInput'),
             tableDiv = $('#tableContainer'),
             companyDiv = $('#companyContainer'),
             companyName = $('#companyName');

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

				tableDiv.html(htmlStr);
			};

			var submitForm = function() {
				var code = encodeURIComponent(searchInput.val() || 'CMAU1396117'),
				    url = '/servlet/TrackContainerInfoServlet?code=' + code
                  + '&company=' + companyInput.val();
				
            if (!companyDiv.hasClass('hide')) {
               companyDiv.addClass('hide');
            }
				
            EFINDER.utils.load(url, successCallback);
			};

			var init = function() {
				var code = '<%= (code != null) ? code : "" %>';

            $('#selectShipCompany').on('click', function() {
               if (!tableDiv.hasClass('hide')) {
                  tableDiv.addClass('hide');
               }

               companyDiv.removeClass('hide');
            });

            $('button[name="shipping"]').on('click', function() {
               var me = this,
                   self = $(this);

               $('button[name="shipping"]').each(function() {
                  var btn = $(this);

                  if (this === me) {
                     if (!btn.hasClass('disabled')) {
                        btn.addClass('btn-danger disabled');
                     }
                  } else {
                     if (btn.hasClass('disabled')) {
                        btn.removeClass('btn-danger disabled');
                     }
                  }
               });

               companyName.text(self.data('name'));
               companyInput.val(self.val());
            });
				
            $('#searchForm').submit(function(e) {
					e.preventDefault();
	
					submitForm();
				});

				if (code !== '') {
					searchInput.val(code);
					submitForm();
				}
			};

			init();
		});
	</script>
</body>
</html>
