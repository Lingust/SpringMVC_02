<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<script type="text/javascript">
				function loadXMLDoc(){
					if(checkU('tip')) return;
					var xmlHttp;
					if(window.XMLHttpRequest){
						xmlHttp = new XMLHttpRequest();
					} else {	//for IE6，没必要存在
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlHttp.onreadystatechange = function () {
						if(xmlHttp.readyState==4 && xmlHttp.status==200){
							if(xmlHttp.responseText == "true"){
								document.getElementById("tip").innerHTML="用户名已存在";
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
						if(element="tip") return true;
					} else {
						if(element="tip") return false;
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
				function submitForm(){
					var name = document.getElementById(tip).innerHTML;
					if(name == "用户名已存在") return false;
					else return true;
				}
			</script>
		<title>用户登录</title>
	</head>
	<body>
		<h2>Welcome to Login</h2>
		<form id="form1" action="registUser" method="post" onSubmit="return submitForm();">
			用户名：<input id="name" name="userName" type="text" onblur="loadXMLDoc();" onfocus="delData('tip');" /><span><font id="tip" color="red"></font></span><p>
			密码：<input id="pss" name="passwd" type="password" onblur="checkU('tip2');" onfocus="delData('tip2');"/><span><font id="tip2" color="red"></font></span><p>
			<input value="注册" type="submit" /><p>
		</form>
	</body>
</html>