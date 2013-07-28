<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>搜船</title>
    
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
				<form action="${pageContext.request.contextPath }/servlet/TrackShipServlet" method="post">
					<div class="input">
						<input class="inputtext" type="text" name="code" /><br>
						<input type="radio" name="type" value="name">船名<br>
						<input type="radio" name="type" value="mmsi">MMSI<br>
						<input type="radio" name="type" value="imo">IMO<br>
						<input type="radio" name="type" value="callsign">呼码<br>
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
  </body>
</html>
