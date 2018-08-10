<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/xadmin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
<body>
<!--解决在iframe里局部跳转-->
<c:if test="${sessionScope.user==null}">
	<% 
		response.getWriter().write("<script>top.location.href='"+request.getContextPath()+"/login.jsp'</script>");
	%>
</c:if>
<div style="width:400px;margin:0 auto;">
<form action="${pageContext.request.contextPath}/UpdatePasswordServlet" method="post" >
	<table class="layui-table" lay-even lay-skin="nob" lay-size="lg">
	  	<tr>
	    	<td colspan="2"><input type="hidden" id="password" value="${sessionScope.user.password}" /></td>
	    </tr>
	  	<tr>
	      <td>原密码:</td>
	      <td><input type="password" name="oldPassword" id="oldPassword" /></td>
	    </tr>
	    <tr>
	    	<td colspan="2"><span id="oldPasswordText" style="color:red;width:100px"></span></td>
	    </tr>
	    <tr>
	      <td>新密码:</td>
	      <td><input type="password" name="newPassword" id="newPassword"/></td>
	    </tr>
	    <tr>
	    	<td colspan="2"><span id="newPasswordText" style="color:red;width:100px"></span></td>
	    </tr>
	    <tr>
	      <td>确认密码:</td>
	      <td><input type="password" name="newPassword1" id="newPassword1"/></td>
	    </tr>
	    <tr>
	    	<td colspan="2"><span id="newPassword1Text" style="color:red;width:100px"></span></td>
	    </tr>
	    <tr>
	    	<td colspan="2" style="text-align:center"><input type="submit" value="修改" class="layui-btn" style="width:100%" onClick="return checkUpdatePassword();" /></td>
	    </tr>
	</table>
</form>
</div>
<!-- 修改密码情况 -->
<%
String msg = (String)request.getAttribute("updatePassword");
String msg1= (String)request.getAttribute("successOrfail");
if(msg != null && msg1!=null) {
	//1代表成功,0代表失败
	if("1".equals(msg1)){
		
	
%>
<script type="text/javascript">
alert("<%=msg%>");
<!--整个页面跳转加上parent,因为该页面在iframe-->
parent.window.location='${pageContext.request.contextPath}/LogoutServlet';
</script>
<%
	}else{
		%>
	<script type="text/javascript">
		alert("<%=msg%>"); 
	</script>
		<%}	
}
%>

<!--检查表单的内容是否正确 -->
<script>
var password;
var oldPassword;
var newPassword;
var newPassword1;
var oldPasswordText;
var newPasswordText;
var newPassword1Text;
window.onload=function(){
	//获取输入框
	password=document.getElementById("password");
	//alert(password.value);
	newPassword=document.getElementById("newPassword");
	newPasswordText=document.getElementById("newPasswordText");
	newPassword1=document.getElementById("newPassword1");
	newPassword1Text=document.getElementById("newPassword1Text");
	oldPassword=document.getElementById("oldPassword");
	oldPasswordText=document.getElementById("oldPasswordText");
}
//检查是否通过
function checkUpdatePassword(){
	var bOldPassword=checkOldPassword();
	var bNewPassword=checkPassword();
	var bNewPassword1=checkConfirm();
	return bOldPassword && bNewPassword && bNewPassword1;
}
//检查原密码
function checkOldPassword(){
	var value=password.value;
	var value1=oldPassword.value;
	if(value1==""){
		oldPasswordText.innerHTML="原密码不能为空!";
		return false;
	}
	if(value!=value1){
		oldPasswordText.innerHTML="×与原密码不一致!";
		return false;
	}else{
		oldPasswordText.innerHTML="√正确";
		return true;
	}
}

//检查新密码
function checkPassword(){
	var value=newPassword.value;
	var regex=/^[a-z0-9A-Z]{4,20}$/;
	if(value==""){
		newPasswordText.innerHTML="新密码不能为空!";
		return false;
	}
	if(regex.test(value)){
		newPasswordText.innerHTML="√正确";
		return true;
	}else{
		newPasswordText.innerHTML="×新密码格式不正确!";
		return false;
	}
	
}
//检验两次密码是否一致
function checkConfirm(){
	var value=newPassword.value;
	var value1=newPassword1.value;
	if(value1==""){
		newPassword1Text.innerHTML="确认密码不能为空!";
		return false;
	}
	if(value==value1){
		newPassword1Text.innerHTML="√正确";
		return true;
	}else{
		newPassword1Text.innerHTML="×两次密码不一致!";
		return false;
	}
	
}
</script>
</body>
</html>