<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<title>易寻方达－有科技，无距离</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link href="/css/bootstrap.min.css" rel="stylesheet">
	<link href="/css/style.css" rel="stylesheet">
	<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
	<!--[if lte IE 6]>
	<link href="/css/bootstrap-ie6.min.css" rel="stylesheet">
	<link href="/css/ie.css" rel="stylesheet">
	<script type="text/javascript" src="/js/bootstrap-ie.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-wrap-no-scroll">
      <div class="login-wrap body">
         <div class="logo clearfix">
            <a href="/index.html" class="logo-home-img">
               <img src="/img/logo-home.png" class="logo-home-img">
            </a>
         </div>

         <div class="info">
            <span>
               <a click="javascript:void(0);" class="register">注册新帐号<i class="icon-chevron-right"></i></a>
            </span>
         </div>

         <div class="clearfix login">
            <div id="errorDiv" class="clearfix alert alert-error hide">
               <i class="icon-remove"></i>&nbsp;用户名或密码错误。
            </div>

            <div class="login-form">
               <form id="loginForm" action="/servlet/LoginServlet" method="post">
                  <fieldset>
                     <label for="username">帐号</label>
                     <input type="text" name="username" placeholder="帐号">
                     <label for="password">密码</label>
                     <input type="password" name="password" placeholder="密码">
                     <button type="submit" class="btn btn-large btn-block btn-primary">立即登陆</button>
                  </fieldset>
               </form>
            </div>
         </div>
      </div>
      
      <footer class="footer-no-scroll">
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
   </div>

	<% String error = (String)request.getAttribute("error"); %>
		
	<script type="text/javascript">
      $(function() {
		   var error = '<%= (error != null) ? error : "" %>';

         if (!!error) {
            $('#errorDiv').show();
         }
      });
   </script>
</body>
</html>
