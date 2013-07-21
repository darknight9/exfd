<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>搜箱</title>
    
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
				<form action="${pageContext.request.contextPath }/servlet/TrackContainerServlet" method="post">
					<div class="input">
						箱号：<input class="inputtext" type="text" name="code" />
					</div>
					<div class="input">
						船公司：<input class="inputtext" type="text" name="company" />
					</div>
					<div id="btn">
						<input class="btn" type="button" value="注册"
							onclick="window.location.href='register.html'" /> <input
							class="btn" type="submit" value="搜箱" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
  </body>
</html>
