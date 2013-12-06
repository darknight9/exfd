<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证数据</title>
</head>
<body>
	<s:actionerror />
	<s:actionmessage />
	<s:form action="validate" namespace="/study" theme="simple">
	输入内容：<s:textfield name="msg" />
		<s:fielderror>
			<s:param>msg.hello</s:param>
		</s:fielderror>
		<br />
		<s:submit value="登录" />
		<s:reset value="重填" />
	</s:form>
</body>
</html>