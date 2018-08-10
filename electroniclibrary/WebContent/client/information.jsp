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
<div style="width:355px;margin:0 auto;">
	<table class="layui-table" lay-even lay-skin="nob" lay-size="lg" style="width:350px">
	  	<tr>
	      <td>读者ID:</td>
	      <td>${sessionScope.user.uid}</td>
	    </tr>
	    <tr>
	      <td>账号:</td>
	      <td>${sessionScope.user.username}</td>
	    </tr>
	    <tr>
	      <td>性别:</td>
	      <td>${sessionScope.user.sex}</td>
	    </tr>
	    <tr>
	      <td>邮箱:</td>
	      <td>${sessionScope.user.mail}</td>
	    </tr>
	    <tr>
	      <td>用户权限:</td>
	      <td>${sessionScope.user.role}</td>
	    </tr>
	    <tr>
	      <td>激活状态:</td>
	      <td>${sessionScope.user.state==1?'已激活':'未激活'}</td>
	    </tr>
	    <tr>
	      <td>注册时间:</td>
	      <td>${sessionScope.user.registerTime}</td>
	    </tr>
	    <tr>
	    	<td colspan="2" style="text-align:center"><a href="information-edit.jsp"><i class="layui-icon layui-icon-edit"></i>编辑</a></td>
	    </tr>
	</table>
</div>
<%
String msg = (String)request.getSession().getAttribute("updateSuccess");
if(msg != null) {
%>
<script type="text/javascript">
alert("<%=msg%>"); 
</script>
<%
request.getSession().removeAttribute("updateSuccess");
}
%>
</body>
</html>