<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta content="text/html;charset=utf-8" http-equiv="content-type">
<title>易寻方达，智能定位</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div id="wrapper">
  <div id="content">
    <div id="u"> <a name="tj_setting" href="http://www.baidu.com/gaoji/preferences.html">搜索设置</a> |
      <c:if test="${user!=null }"> 欢迎您：${user.nickname } <a
				href="${pageContext.request.contextPath }/servlet/LogoutServlet">注销</a> </c:if>
      <c:if test="${user==null }"> <a id="lb" onclick="return false;" name="tj_login" href="${pageContext.request.contextPath }/servlet/LoginUIServlet">登录</a> <a class="reg" name="tj_reg" target="_blank" href="${pageContext.request.contextPath }/servlet/RegisterUIServlet">注册</a> </c:if>
    </div>
    <div id="m">
      <p id="lg"> <img width="270" height="129" src="images/mlogo.jpg">
      <p id="nv"> <b>搜 箱</b> <a href="http://tieba.baidu.com" name="tj_tieba">搜 船</a> <a href="http://zhidao.baidu.com" name="tj_zhidao">智能铅封</a> </p>
      <div id="fm">
        <form action="/s" name="f">
          <span class="s_ipt_wr">
          <input id="kw" class="s_ipt" type="text" maxlength="100" name="wd" autocomplete="off">
          </span>
          <input type="hidden" value="0" name="checkno">
        </form>
        <span class="s_btn_wr">
        <input id="su" class="s_btn" type="submit" onmouseout="this.className='s_btn'" onmousedown="this.className='s_btn s_btn_h'" value="查询一下">
        </span> </div>
    </div>
  </div>
  <div id="ftCon">
    <div id="ftConw">
      <p id="lh"> <a href="#" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.efinder.com.cn');">将易寻方达设为首页</a> | <a href="http://home.baidu.com" onmousedown="return ns_c({'fm':'behs','tab':'tj_about'})">关于易寻方达</a> </p>
      <p id="cp"> ©2013 Efinder <a name="tj_duty" href="/duty/">使用易寻方达前必读</a> 京ICP证03XXXXXX号
    </div>
  </div>
</div>
<h2>xxxx网站</h2>
<br />
<div style="text-align:center;"> <br />
  <hr />
  <a href="${pageContext.request.contextPath }/servlet/TrackContainerUIServlet">搜箱</a> <a href="${pageContext.request.contextPath }/servlet/TrackShipUIServlet">搜船</a> <a href="${pageContext.request.contextPath }/servlet/TrackSealUIServlet">搜铅封</a> </div>
<hr />
</body>
</html>
