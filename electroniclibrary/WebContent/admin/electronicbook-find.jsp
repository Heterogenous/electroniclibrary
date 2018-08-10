<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电子书详情表</title>
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
<table class="layui-table" lay-even lay-size="lg">
<%
	String bid=request.getParameter("bid");
	request.setAttribute("bid", bid);
%>
<c:forEach var="Books" items="${books}">
<c:if test="${bid==Books.bid}">
 	<tr>
 		<td style="width:10%"><img src="${pageContext.request.contextPath}/bookUrl/${Books.imgUrl}" width="200" height="284" /></td>
 		<td>
 			<table class="layui-table" lay-even lay-size="lg">
 				<tr>
 					<td>ID</td>
 					<td>${Books.bid}</td>
 				</tr>
 				<tr>
 					<td>书名</td>
 					<td>${Books.bookName}</td>
 				</tr>
 				<tr>
 					<td>作者</td>
 					<td>${Books.author}</td>
 				</tr>
 				<tr>
 					<td>出版社</td>
 					<td>${Books.publisher}</td>
 				</tr>
 				<tr>
 					<td>类型</td>
 					<td>${Books.type}</td>
 				</tr>
 				<tr>
 					<td>读书简介</td>
 					<td>${Books.introduce}</td>
 				</tr>
 				<tr>
 					<td>借阅次数</td>
 					<td>${Books.borrowTimes}</td>
 				</tr>
 				<tr>
 					<td>上传时间</td>
 					<td>${Books.uploadTime}</td>
 				</tr>
 			</table>
 		</td>
 	</tr>
 	<tr>
 		<td colspan="2"><input type="button" value="返回" class="layui-btn" style="width:100%" onClick="goBack()" /></td>
 	</tr>
</c:if>
</c:forEach>
</table>
</div>
<script type="text/javascript">
	function goBack(){
		window.location='admin-electronicbook.jsp';
	}
</script>
</body>
</html>