<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>hello!!!</title>
	</head>
	<body>
		<h2>Welcome to Login</h2>
		<form action="login" method="post">
			用户名：<input name=username type="text" /><p>
			密码：<input name="passwd" type="password" /><p>
			<input value="提交" type="submit" />
		</form>
	</body>
</html>
