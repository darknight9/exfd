<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>搜索结果---铅封</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<div id="container">
		<div id="image">
			<div id="form">
				<form
					action="${pageContext.request.contextPath }/servlet/TrackSealInfoServlet"
					method="post">
					<div class="input">
						铅封号：<input class="inputtext" type="text" name="code" />
					</div>
					<div id="btn">
						<input class="btn" type="button" value="注册"
							onclick="window.location.href='register.html'" /> <input
							class="btn" type="submit" value="搜铅封" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<hr />
	<table border=1 width=400>
		<tr>
			<td>铅封号</td>
			<td>${seal.code }</td>
		</tr>
		<tr>
			<td>longitude</td>
			<td>${seal.longitude }</td>
		</tr>
		<tr>
			<td>latitude</td>
			<td>${seal.latitude }</td>
		</tr>
		<tr>
			<td>ctime</td>
			<td>${seal.ctime }</td>
		</tr>
		<tr>
			<td>mtime</td>
			<td>${seal.mtime }</td>
		</tr>
		<tr>
			<td>gpstime</td>
			<td>${seal.gpstime }</td>
		</tr>
		<tr>
			<td>markdel</td>
			<td>${seal.markdel }</td>
		</tr>
		<tr>
			<td>remark</td>
			<td>${seal.remark }</td>
		</tr>
		<tr>
			<td>location</td>
			<td>${seal.poi }</td>
		</tr>
	</table>
</body>
</html>

