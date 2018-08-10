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
<body bgcolor="#BFBFBF">
<!--解决在iframe里局部跳转-->
<c:if test="${sessionScope.user==null}">
	<% 
		response.getWriter().write("<script>top.location.href='"+request.getContextPath()+"/login.jsp'</script>");
	%>
</c:if>
<div style="margin:5% auto auto 5%;">
	<div class="layui-row layui-col-space20">
		<c:forEach var="readerBook" items="${readerBookList}" >
		<div class="layui-col-md3">
	       <div class="layui-card">
			  <div class="layui-card-header"><a href="../readBook/readbook.jsp?bid='${readerBook.bid}'" target="_blank">${readerBook.bookName}</a></div>
			  <div class="layui-card-body">
			  	<a href="../readBook/readbook.jsp?bid='${readerBook.bid}'" target="_blank">
				<img src="${pageContext.request.contextPath}/bookUrl/${readerBook.imgUrl}" width="200" height="284" />
				</a>
			  </div>
		  </div>
	    </div>
	   </c:forEach>
  </div>
</div>
</body>
</html>