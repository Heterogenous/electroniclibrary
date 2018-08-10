<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电子书城</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<body bgcolor="#BFBFBF">
<c:if test="${sessionScope.user==null}">
	<% 
		response.getWriter().write("<script>top.location.href='"+request.getContextPath()+"/login.jsp'</script>");
	%>
</c:if>
	<div style="width:400px;margin:0 auto;">
		<i class="layui-icon layui-icon-face-surprised" style="font-size: 100px; color: #1E9FFF;"></i>
		<span style="font-size:50px;color:red">非常抱歉！该阅读功能暂时不开放！</span>
	</div>
</body>
</html>