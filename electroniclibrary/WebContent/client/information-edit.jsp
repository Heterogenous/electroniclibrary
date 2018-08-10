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
<c:if test="${sessionScope.user==null}">
	<% 
		response.getWriter().write("<script>top.location.href='"+request.getContextPath()+"/login.jsp'</script>");
	%>
</c:if>
<div style="width:420px;margin:0 auto;">
	<form action="${pageContext.request.contextPath}/InforEditServlet" method="post">
	<table class="layui-table" lay-even lay-skin="nob" lay-size="lg" style="width:420px">
		<tr>
			<td colspan="3"></td>
		</tr>
		
	  	<tr>
	      <td>读者ID:</td>
	      <td colspan="2">${sessionScope.user.uid}</td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	      <td>账号:</td>
	      <td colspan="2">${sessionScope.user.username}</td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	      <td>性别:</td>
	      <td colspan="2"><input type="radio" name="sex" value="男" ${sessionScope.user.sex=='男'?'checked':''}/>男&nbsp;&nbsp;<input type="radio" name="sex" value="女" ${sessionScope.user.sex=='女'?'checked':''}/>女</td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	      <td>邮箱:</td>
	      <td><input type="text" id="mail" name="mail" value="${sessionScope.user.mail}" /></td>
	      <td></td>
	    </tr>
	    
	    <tr>
	    	<td></td>
			<td colspan="2"><span id="mailText" style="color:red;width:100px">${mailError}</span></td>
		</tr>
		
	    <tr>
	      <td>用户权限:</td>
	      <td colspan="2">${sessionScope.user.role}</td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	      <td>激活状态:</td>
	      <td colspan="2">${sessionScope.user.state==1?'已激活':'未激活'}</td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	      <td>注册时间:</td>
	      <td>${sessionScope.user.registerTime}</td>
	      <td></td>
	    </tr>
	    
	    <tr>
			<td colspan="3"></td>
		</tr>
		
	    <tr>
	    	<td colspan="3" style="text-align:center"><input type="submit" value="修改" class="layui-btn" onClick="return checkMail();" style="width:100%"/></td>
	    </tr>
	</table>
	</form>
</div>
<script type="text/javascript">
window.onload=function(){
	 var mail=document.getElementById("mail");
	 var errorMsg=document.getElementById("mailText");
}
	 function checkMail(){
			var value=mail.value;
			var regex=/^[a-z0-9A-z]+@[a-z0-9]+\.(com)|(net)$/;
			if(value==""){
				mailText.innerHTML="邮箱不能为空";
				return false;
			}
			if(regex.test(value)){
				mailText.innerHTML="√";
				return true;
			}else{
				mailText.innerHTML="×邮箱格式不正确";
				return false;
			}
		}
</script>
</body>
</html>