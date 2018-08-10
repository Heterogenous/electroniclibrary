<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员欢迎页面</title>
</head>
<link rel="stylesheet" href="../layui/css/layui.css">
<link rel="stylesheet" href="./css/xadmin.css">
			<c:if test="${sessionScope.superUser==null}">
				<%
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				%>
			</c:if>
<body>
<div class="x-body layui-anim layui-anim-up">
	<blockquote class="layui-elem-quote">欢迎您管理员:<span class="x-red">${sessionScope.superUser.username}</span>!<br/>tips:如果表格显示的数据不整齐,请点击左上的隐藏侧栏按钮</blockquote>
</div>
</body>
</html>