<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<%-- 
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/Calendar1.js"></script>
--%>

</head>

<body>
	<div id="header"></div>
	<div id="main">
		<div id="notice">
			<h2>注册须知：</h2>
			1. 这个是第一条注册须知。 2. 这个是第二条注册须知。
		</div>
		<br /> <br />

		<form
			action="${pageContext.request.contextPath }/servlet/RegisterServlet"
			method="post">
			<table id="formtable">
				<tr>
					<td class="td1">登陆账号：</td>
					<td><input class="userinput" type="text" name="username" value="${form.username }">
						<span class="message">${form.errors.username }</span></td>
				</tr>
				<tr>
					<td class="td1">重复密码：</td>
					<td><input class="userinput" type="password" name="password" value="${form.password }">
						<span class="message">${form.errors.password }</span></td>
				</tr>
				<tr>
					<td class="td1">确认密码：</td>
					<td><input class="userinput" type="password" name="password2" value="${form.password2 }">
						<span class="message">${form.errors.password2 }</span></td>
				</tr>
				<tr>
					<td class="td1">电子邮箱：</td>
					<td><input class="userinput" type="text" name="email" value="${form.email }">
						<span class="message">${form.errors.email }</span></td>
				</tr>
				<tr>
					<td class="td1">生日：</td>
					<td><input class="userinput" type="text"
						name="birthday" id="birthday" value="${form.birthday }" /> <span class="message">${form.errors.birthday
							}</span></td>
				</tr>
				<tr>
					<td class="td1">您的昵称：</td>
					<td><input class="userinput" type="text" name="nickname" value="${form.nickname }">
						<span class="message">${form.errors.nickname }</span></td>
				</tr>
				<tr>
					<td class="td1">图片认证：</td>
					<td><input class="userinput" type="text" name="checknode">
					</td>
				</tr>

			</table>
			<div id="formsubmit">
				<span><input class="btn" type="reset" name="reset" value="重置">
				</span> <span><input class="btn" type="submit" name="submit"
					value="注册"> </span>
			</div>
		</form>
	</div>
	<div id="footer"></div>


</body>
</html>