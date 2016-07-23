<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>登录系统</title>
		<script type="text/javascript">
			function trim(s){
					return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
			}
			
			function submitForm(){
				var name = document.getElementById("name").value;
				var passwd = document.getElementById("pss").value;
				document.getElementById("tip").innerHTML="";
				document.getElementById("tip2").innerHTML="";
				document.getElementById("errorMsg").innerHTML="";
				if(trim(name)=="" || trim(passwd)==""){
					if(trim(name)==""){
						document.getElementById("tip").innerHTML="用户名不能为空";
						document.getElementById("name").value="";
					}
					if(trim(passwd)==""){
						document.getElementById("tip2").innerHTML="密码不能为空";
						document.getElementById("pss").value="";
					}
					return false;
				}
				return true;
			}
		</script>
	</head>
	
	<body>
		<h2>Welcome to Sign in!</h2>
		<font id="errorMsg" color="red"><c:out value="${errorMsg }"></c:out></font>
		<form id="form1" action="login.html" method="post" onSubmit="return submitForm();">
			用户名：<input id="name" name="userName" type="text" /><span><font id="tip" color="red"></font></span><p>
			密码：<input id="pss" name="passwd" type="password" /><span><font id="tip2" color="red"></font></span><p>
			<input value="登录" type="submit" /><p>
			没有用户名？去<a href="<%=basePath%>/register">注册</a>
		</form>
		<div>
		<img src="<%=basePath%>/download.html">下载测试
		</div>
<%-- 		<form:form action="login">
			用户名：<form:input path="username"/><p>
			密  码：<form:password path="passwd"/><p>
			<input type="submit" value="提交"><p>
		</form:form> --%>
	</body>
</html>
