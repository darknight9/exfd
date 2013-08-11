<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>搜索结果---搜船</title>

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
					action="${pageContext.request.contextPath }/servlet/TrackShipInfoServlet"
					method="post">
					<div class="input">
						船号：<input class="inputtext" type="text" name="code" />
					</div>
					<div id="btn">
						<input class="btn" type="button" value="注册"
							onclick="window.location.href='register.html'" /> <input
							class="btn" type="submit" value="搜船" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<hr />
	一共搜索到结果数：${shipnumbers } 个.
	<c:forEach var="ship" items="${ships }">
	<table border=1 width=400>
		<tr><td>shipid</td><td>${ship.shipid }</td>
			<td>shipname</td><td>${ship.shipname }</td></tr>
		<tr><td>shipnamecn</td><td>${ship.shipnamecn }</td>
			<td>mmsi</td><td>${ship.mmsi }</td></tr>
		<tr><td>imo</td><td>${ship.imo }</td>
		
			<td>callsign</td><td>${ship.callsign }</td></tr>
		<tr><td>shipflag</td><td>${ship.shipflag }</td>
			<td>shiptype</td><td>${ship.shiptype }</td></tr>
		<tr><td>shiplength</td><td>${ship.shiplength }</td>
			<td>shipwidth</td><td>${ship.shipwidth }</td></tr>
		
		<tr><td>draft</td><td>${ship.draft }</td>
			<td>gpstime</td><td>${ship.gpstime }</td></tr>
		<tr><td>latitude</td><td>${ship.latitude }</td>
			<td>longitude</td><td>${ship.longitude }</td></tr>
		<tr><td>lat</td><td>${ship.lat }</td>
			<td>lon</td><td>${ship.lon }</td></tr>
		
		<tr><td>speed</td><td>${ship.speed }</td>
			<td>direction</td><td>${ship.direction }</td></tr>
		<tr><td>truehending</td><td>${ship.truehending }</td>
			<td>reporttype</td><td>${ship.reporttype }</td></tr>
		<tr><td>state</td><td>${ship.state }</td>
			<td>updatetime</td><td>${ship.updatetime }</td></tr>
		
		<tr><td>gpstimepre</td><td>${ship.gpstimepre }</td>
			<td>latpre</td><td>${ship.latpre }</td></tr>
		<tr><td>lonpre</td><td>${ship.lonpre }</td>
			<td>averagespeed</td><td>${ship.averagespeed }</td></tr>
		<tr><td>distanceMoved</td><td>${ship.distanceMoved }</td>
			<td></td><td></td></tr>
		</table>
		<hr><hr>
	</c:forEach>
	
</body>
</html>

