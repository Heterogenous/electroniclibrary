<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
</head>
<link rel="stylesheet" href="../layui/css/layui.css">
<link rel="stylesheet" href="./css/xadmin.css">
			<c:if test="${sessionScope.superUser==null}">
				<%
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				%>
			</c:if>
<body bgcolor="#BFBFBF">
<div style="width:400px;margin:0 auto;">
		<i class="layui-icon layui-icon-face-surprised" style="font-size: 100px; color: #1E9FFF;"></i>
		<span style="font-size:50px;color:red">非常抱歉！只允许在同一浏览器中开启一个后台管理页面</span><br/>
		<span style="font-size: 30px;">如果您不小心把后台管理页面×掉,请点击<a href="javascript:;" target="_self" onclick="exitAdmin()">退出系统</a>重新登陆</span>
	</div>
</body>
<script type="text/javascript">
function exitAdmin(){
	window.location="http://localhost:8080${pageContext.request.contextPath}/AdminLogoutServlet";
}
</script>
</html>