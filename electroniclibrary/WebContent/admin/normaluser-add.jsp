<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户添加页面</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
			<c:if test="${sessionScope.superUser==null}">
				<%
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				%>
			</c:if>
<body>
<div class="x-body layui-anim layui-anim-up">
	<div style="width:420px;margin:0 auto;">
	<form action="${pageContext.request.contextPath}/NormalUserAddServlet" method="post">
	<table class="layui-table" lay-even lay-skin="nob" lay-size="lg" style="width:420px">
		<tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	      <td>账号:</td>
	      <td colspan="2"><input type="text" id="username" name="username" value="${userName}" /></td>
	    </tr>
	    
	    <tr>
			<td></td>
			<td colspan="2"><span id="usernameText" style="color:red;width:100px">${usernameAlready}</span>
		</tr>
		
		<tr>
	      <td>密码:</td>
	      <td colspan="2"><input type="password" id="password" name="password" value="${passWord}"/></td>
	    </tr>
	    
	    <tr>
	    	<td></td>
			<td colspan="2"><span id="passwordText" style="color:red;width:100px"></span></td>
		</tr>
		
	    <tr>
	      <td>性别:</td>
	      <td colspan="2"><input type="radio" name="sex" value="男" ${seX==null?'checked':(seX=='男'?'checked':'')}/>男&nbsp;&nbsp;<input type="radio" name="sex" value="女" ${seX=='女'?'checked':''} />女</td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	      <td>邮箱:</td>
	      <td colspan="2"><input type="text" id="mail" name="mail" value="${maiL}"/></td>
	    </tr>
	    
	    <tr>
	    	<td></td>
			<td colspan="2"><span id="mailText" style="color:red;width:100px">${mailAlready}</span></td>
		</tr>
		
		<tr>
	      <td>激活状态:</td>
	      <td colspan="2">
	      	<input type="text" id="state" name="state" value="${statE}" /><br/>
	      	0代表未激活,1代表激活
	      </td>
	    </tr>
	    
	    <tr>
	    	<td></td>
			<td colspan="2"><span id="stateText" style="color:red;width:100px"></span></td>
		</tr>
		
	    <tr>
	    	<td colspan="3" style="text-align:center"><input type="submit" value="添加" class="layui-btn" onClick="return checkForm();" style="width:100%"/></td>
	    </tr>
	     <tr>
	    	<td colspan="3" style="text-align:center"><input type="button" value="取消" class="layui-btn layui-btn-warm" onClick="cancelAdd()" style="width:100%"/></td>
	    </tr>
	</table>
	</form>
</div>
</div>
<script>
//1.定义变量
var username;
var password;
var mail;
var state
//1.1定义提示框
var usernameText;
var passwordText;
var stateText;
var mailText;
//当页面加载完成时获取表单信息
window.onload=function(){
	//获取文本框元素
	username=document.getElementById("username");
	password=document.getElementById("password");
	state=document.getElementById("state");
	mail=document.getElementById("mail");
	//获取错误框元素
	usernameText=document.getElementById("usernameText");
	passwordText=document.getElementById("passwordText");
	stateText=document.getElementById("stateText");
	mailText=document.getElementById("mailText");
	
}

//取消操作
function cancelAdd(){
	window.location="admin-normaluser.jsp";
}
//检查整个格式
function checkForm(){
	var bUsername=checkUsername();
	var bPassword=checkPassword();
	var bState=checkState();
	var bMail=checkMail();
	return bUsername && bPassword && bState && bMail;
}
//检验用户名格式
function checkUsername(){
	var value=username.value;
	var regex=/^[a-z]\w{3,19}$/;//不只为何text类型的文本框至少4最大20是{3,19}
	if(value==""){
		usernameText.innerHTML="账号不能为空!";
		return false;
	}
	if(regex.test(value)){
		usernameText.innerHTML="√正确";
		return true;
	}else{
		usernameText.innerHTML="×用户格式不正确!";
		return false;
	}
	
}
//检查密码格式
function checkPassword(){
	var value=password.value;
	var regex=/^[a-z0-9A-Z]{4,20}$/;
	if(value==""){
		passwordText.innerHTML="密码不能为空!";
		return false;
	}
	if(regex.test(value)){
		passwordText.innerHTML="√正确";
		return true;
	}else{
		passwordText.innerHTML="×密码格式不正确!";
		return false;
	}
}
//检查邮箱格式
function checkMail(){
	var value=mail.value;
	var regex=/^[a-z0-9A-z]+@[a-z0-9]+\.(com)|(net)$/;
	if(value==""){
		mailText.innerHTML="邮箱不能为空!";
		return false;
	}
	if(regex.test(value)){
		mailText.innerHTML="√正确";
		return true;
	}else{
		mailText.innerHTML="×邮箱格式不正确!";
		return false;
	}
}
//检查激活状态
function checkState(){
	var value=state.value;
	if(value==""){
		stateText.innerHTML="激活状态不能为空!";
		return false;
	}
	if(value==0||value==1){
		stateText.innerHTML="√正确";
		return true
	}else{
		stateText.innerHTML="×激活状态只允许填写0或1";
		return false;
	}
}
</script>
</body>
</html>