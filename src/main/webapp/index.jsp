<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>登录系统</title>
		<script type="text/javascript">
			function submitUser(){
				var name = document.getElementById("name").value;
				var passwd = document.getElementById("pss").value;
				if(trim(name)=="" || trim(passwd)==""){
					if(trim(passwd)==""){
						document.getElementById("tip2").innerHTML="密码不能为空";
					} else document.getElementById("tip2").innerHTML="";
					if(trim(name)==""){
						document.getElementById("tip").innerHTML="用户名不能为空";
					} else document.getElementById("tip").innerHTML="";
					return false;
				}
				return true;
			}
			function trim(s){
				return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
			}
		</script>
	</head>
	<body>
		<h2>Welcome to Login</h2>
		<form id="form1" action="login" method="post" onSubmit="return submitUser();">
			用户名：<input id="name" name="username" type="text" /><span><font id="tip" color="red"></font></span><p>
			密码：<input id="pss" name="passwd" type="password" /><span><font id="tip2" color="red"></font></span><p>
			<input value="提交" type="submit" /><p>
		</form>
<%-- 		<form:form action="login">
			用户名：<form:input path="username"/><p>
			密  码：<form:password path="passwd"/><p>
			<input type="submit" value="提交"><p>
		</form:form> --%>
	</body>
</html>
