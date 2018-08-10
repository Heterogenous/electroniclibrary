<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详细信息</title>
<link rel="stylesheet" href="layui/css/layui.css">
<link rel="stylesheet" href="css/indexCss.css">
</head>
<body bgcolor="#BFBFBF">
<c:if test="${sessionScope.user==null}">
	<% 
		response.getWriter().write("<script>top.location.href='"+request.getContextPath()+"/login.jsp'</script>");
	%>
</c:if>
<table class="layui-table" lay-skin="row">
	<tr>
		<td>
			<img src="bookUrl/${bookDetail.imgUrl}"/>
		</td>
		<td>
			<table class="layui-table" lay-even lay-skin="nob">
				<tr>
					<td>书名</td>
					<td>${bookDetail.bookName}</td>
				</tr>
				<tr>
					<td>作者</td>
					<td>${bookDetail.author}</td>
				</tr>
				<tr>
					<td>类型</td>
					<td>${bookDetail.type}</td>
				</tr>
				<tr>
					<td>出版社</td>
					<td>${bookDetail.publisher}</td>
				</tr>
				<tr>
					<td>读书简介</td>
					<td>${bookDetail.introduce}</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${isBorrow==false}">
			<button class="layui-btn" style="width:100%" onclick="borrowBook('${bookDetail.bid}')">
  			<i class="layui-icon layui-icon-read"></i>借阅
			</button>
			</c:if>
			<c:if test="${isBorrow==true}">
			<button class="layui-btn layui-btn-disabled" style="width:100%">
  			<i class="layui-icon layui-icon-read"></i>已借阅
			</button>
			</c:if>
		</td>
	</tr>
</table>
<c:if test="${borrowSuc!=null}">
	<script>
		alert("${borrowSuc}");
		window.close();
	</script>
</c:if>
<script>
function borrowBook(bid){
	window.location='${pageContext.request.contextPath}/BorrowBookServlet?bid='+bid;
}
</script>
</body>
</html>