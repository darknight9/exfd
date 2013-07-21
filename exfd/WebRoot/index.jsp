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

<title>首页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body style="text-align:center;">

	<h2>xxxx网站</h2>
	<br />

	<div style="text-align:center;">
		<c:if test="${user!=null }">
	欢迎您：${user.nickname }	   <a
				href="${pageContext.request.contextPath }/servlet/LogoutServlet">注销</a>
		</c:if>

		<c:if test="${user==null }">
			<a
				href="${pageContext.request.contextPath }/servlet/RegisterUIServlet">注册</a>
			<a href="${pageContext.request.contextPath }/servlet/LoginUIServlet">登陆</a>
		</c:if>
		
		<br />
		<hr />
		<a href="${pageContext.request.contextPath }/servlet/TrackContainerUIServlet">搜箱</a>
		<a href="${pageContext.request.contextPath }/servlet/TrackShipUIServlet">搜船</a>
		<a href="${pageContext.request.contextPath }/servlet/TrackSealUIServlet">搜铅封</a>
		
	</div>
	<hr />
</body>
</html>
