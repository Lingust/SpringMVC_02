<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>登录系统</title>
		<script type="text/javascript">
			function loadXMLDoc(){
				checkU('tip');
				var xmlHttp;
				if(window.XMLHttpRequest){
					xmlHttp = new XMLHttpRequest();
				} else {	//for IE6，没必要存在
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlHttp.onreadystatechange = function () {
					if(xmlHttp.readyState==4 && xmlHttp.status==200){
						if(xmlHttp.responseText == "true"){
							document.getElementById("tip").innerHTML="用户名不可用";
						} else {
							document.getElementById("tip").innerHTML="用户名可用";
						}
					}
				}
				var a = document.getElementById("name").value;
				xmlHttp.open("GET","checkUsername?username="+a);
				xmlHttp.send();
			}
			
			function trim(s){
					return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
			}
			function checkU(element){
				var name = document.getElementById("name").value;
				var passwd = document.getElementById("pss").value;
				if(trim(name)=="" && element=="tip"){
					document.getElementById("tip").innerHTML="用户名不能为空";
					document.getElementById("name").value="";
				} else {
					
				}
				if(trim(passwd)=="" && element=="tip2"){
					document.getElementById("tip2").innerHTML="密码不能为空";
					document.getElementById("pss").value="";
				}
			}
			function delData(element){
				var c = document.getElementById(element).innerHTML;
				if(c!="" && c!="用户名可用" && element=="tip"){
					document.getElementById("name").value="";
					document.getElementById("tip").innerHTML="";
				}
				if(c!="" && element=="tip2"){
					document.getElementById("pss").value="";
					document.getElementById("tip2").innerHTML="";
				}
			}
		</script>
	</head>
	<body>
		<h2>Welcome to Login</h2>
		<form id="form1" action="login" method="post" >
			用户名：<input id="name" name="username" type="text" onblur="loadXMLDoc();" onfocus="delData('tip');" /><span><font id="tip" color="red"></font></span><p>
			密码：<input id="pss" name="passwd" type="password" onblur="checkU('tip2');" onfocus="delData('tip2');"/><span><font id="tip2" color="red"></font></span><p>
			<input value="提交" type="submit" /><p>
		</form>
<%-- 		<form:form action="login">
			用户名：<form:input path="username"/><p>
			密  码：<form:password path="passwd"/><p>
			<input type="submit" value="提交"><p>
		</form:form> --%>
	</body>
</html>
