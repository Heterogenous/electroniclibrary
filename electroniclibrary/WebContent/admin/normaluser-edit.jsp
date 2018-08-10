<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户编辑页面</title>
</head>
<link rel="stylesheet" href="../layui/css/layui.css">
<link rel="stylesheet" href="../css/xadmin.css">
			<c:if test="${sessionScope.superUser==null}">
				<%
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				%>
			</c:if>
<body>
	<% 
		int uid=Integer.parseInt(request.getParameter("uid"));
		request.setAttribute("uid", uid);
	%>
<div style="width:420px;margin:0 auto;">
	<form action="${pageContext.request.contextPath}/NormalUserEditServlet?uid=${uid}" method="post">
	<table class="layui-table" lay-even lay-skin="nob" lay-size="lg" style="width:420px">
	<c:forEach var="Users" items="${normalUsers}">
	<c:if test="${Users.uid==uid}">
		<tr>
			<td colspan="3"></td>
		</tr>
		
	  	<tr>
	      <td>ID:</td>
	      <td colspan="2">${Users.uid}</td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	      <td>账号:</td>
	      <td colspan="2">${Users.username}</td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
		<tr>
	      <td>密码:</td>
	      <td colspan="2"><input type="text" id="password" name="password" value="${Users.password}" /></td>
	    </tr>
	    
	    <tr>
	    	<td></td>
			<td colspan="2"><span id="passwordText" style="color:red;width:100px"></span></td>
		</tr>
		
	    <tr>
	      <td>性别:</td>
	      <td colspan="2"><input type="radio" name="sex" value="男" ${Users.sex=='男'?'checked':''}/>男&nbsp;&nbsp;<input type="radio" name="sex" value="女" ${Users.sex=='女'?'checked':''}/>女</td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	      <td>邮箱:</td>
	      <td><input type="text" id="mail" name="mail" value="${Users.mail}" /></td>
	      <td></td>
	    </tr>
	    
	    <tr>
	    	<td></td>
			<td colspan="2"><span id="mailText" style="color:red;width:100px"></span></td>
		</tr>
		
	    <tr>
	      <td>用户权限:</td>
	      <td colspan="2">${Users.role}</td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	      <td>激活状态:</td>
	      <td colspan="2"><input type="text" id="state" name="state" value="${Users.state}" /><br/>0代表未激活,1代表激活</td>
	    </tr>
	    
	    <tr>
	    	<td></td>
			<td colspan="2"><span id="stateText" style="color:red;width:100px"></span></td>
		</tr>
		
	    <tr>
	      <td>注册时间:</td>
	      <td>${Users.registerTime}</td>
	      <td></td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	    	<td colspan="3" style="text-align:center"><input type="submit" value="修改" class="layui-btn" onClick="return checkForm();" style="width:100%"/></td>
	    </tr>
	     <tr>
	    	<td colspan="3" style="text-align:center"><input type="button" value="取消" class="layui-btn layui-btn-warm" onClick="cancelEdit()" style="width:100%"/></td>
	    </tr>
	</c:if>    
	</c:forEach>    
	</table>
	</form>
</div>
<script type="text/javascript">
var password;
var passwordText;
var mail;
var mailText;
var state;
var stateText;
window.onload=function(){
	password=document.getElementById("password");
	passwordText=document.getElementById("passwordText");
	mail=document.getElementById("mail");
	mailText=document.getElementById("mailText");
	state=document.getElementById("state");
	stateText=document.getElementById("stateText");
}
//取消操作
function cancelEdit(){
	window.location="admin-normaluser.jsp";
}
//修改操作
function checkForm(){
	var bpassword=checkPassword();
	var bmail=checkMail();
	var bstate=checkState();
	return bpassword && bmail && bstate;
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
		passwordText.innerHTML="√格式正确";
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
		mailText.innerHTML="√格式正确";
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
		stateText.innerHTML="√填写正确";
		return true
	}else{
		stateText.innerHTML="×激活状态只允许填写0或1";
		return false;
	}
}
</script>
</body>
</html>