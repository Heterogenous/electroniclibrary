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
<div style="margin:2% 2% auto 2%;">
	<table class="layui-table" lay-even lay-size="lg">
	  	<thead>
    <tr>
      <th style="width:100px">书名</th>
      <th>作者</th>
      <th>类型</th>
      <th>借书日期</th>
      <th>还书日期</th>
      <th>剩余阅读天数</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody>
  <c:forEach var="readerBook" items="${readerBookList}" >
    <tr>
      <td>${readerBook.bookName}</td>
      <td>${readerBook.author}</td>
      <td>${readerBook.type}</td>
      
      		<c:forEach var="borrowBookTime" items="${borrowBookList}">
      			<c:if test="${borrowBookTime.bid==readerBook.bid}">
      				<td>${borrowBookTime.borrowTime}</td>
      				<td>${borrowBookTime.returnTime}</td>
  					<td>${borrowBookTime.readDay}</td>
      			</c:if>
      		</c:forEach>
      
      <td colspan="2" style="text-align:center">
      	<a href="${pageContext.request.contextPath}/ReturnBookTimeServlet?bid=${readerBook.bid}&uid=${sessionScope.user.uid}"><i class="layui-icon layui-icon-release"></i>还书</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
	</table>
</div>





<%
String msg = (String)request.getSession().getAttribute("deleteBorrowBook");
if(msg != null) {
%>
<script type="text/javascript">
alert("<%=msg%>"); 
</script>
<%
request.getSession().removeAttribute("deleteBorrowBook");
}
%>
</body>
</html>