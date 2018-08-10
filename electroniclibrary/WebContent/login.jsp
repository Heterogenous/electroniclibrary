<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<link rel="stylesheet" href="layui/css/layui.css">
</head>
<style>
#icon,#icon1{
	position: absolute;
	z-index: 5;
}
#username,#password{
	padding-left: 18px;
}
</style>
<body>
	<!-- 防止超级用户和普通用户在同一浏览器登陆 -->
	<c:choose>
		<c:when test="${sessionScope.user!=null || sessionScope.superUser!=null}">
				<c:if test="${sessionScope.user!=null }" >
					<%
						response.sendRedirect("index.jsp");
					%>
				</c:if>	
				<c:if test="${sessionScope.superUser!=null }" >
					<%
						response.sendRedirect(request.getContextPath()+"/admin/admin-only.jsp");
					%>
				</c:if>
		</c:when>
		<c:otherwise>
			<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
				<table>
					<tr>
						<td>
							<i class="layui-icon layui-icon-username" id="icon"></i>
							<input type="text" id="username" name="username" placeholder="用户名" required/>
						</td>
					</tr>
					<tr>
						<td>
							<i class="layui-icon layui-icon-password" id="icon1"></i>
							<input type="password" id="password" name="password" placeholder="密码" required/>
						</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="autologin" value="${60*60*24*7}"/>一周免登陆</td>
					</tr>
					<tr>
						<td><input type="submit" value="  登  陆  " /></td>
					</tr>
					<tr>
						<td><input type="reset" value="  重  置  " /></td>
					</tr>
					<tr>
						<td><hr/></td>
					</tr>
					<tr>
						<td style="color:red">${ErrorMessages}</td>
					</tr>
				</table>
			</form>
		</c:otherwise>
	</c:choose>	
</body>
</html>