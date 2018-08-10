<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借阅表管理页面</title>
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
  <thead>
    <tr>
      <th>借阅ID</th>
      <th>电子书ID</th>
      <th>借阅者ID</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody>
  <c:forEach var="Borrows" items="${borrows}">
  	<tr>
  		<td>${Borrows.borrowId}</td>
  		<td>${Borrows.bid}</td>
  		<td>${Borrows.uid}</td>
  		<td>
  			<a href="javascript:;" onclick="find(${Borrows.borrowId},'${Borrows.bid}',${Borrows.uid})"><i class="layui-icon layui-icon-list"></i>查看详情</a>
  		</td>
  	</tr>
  </c:forEach>
  </tbody>
	</table>
</div>
<%
	String msg=(String)request.getSession().getAttribute("delBorrowBook");
	if(msg!=null){
		%>
		<script>
			alert('<%=msg%>');
		</script>
		<%	
	request.getSession().removeAttribute("delBorrowBook");
	}
%>
<script>
	function find(borrowId,bid,uid){
		window.location="${pageContext.request.contextPath}/BorrowDetailServlet?borrowId="+borrowId+"&bid="+bid+"&uid="+uid;
	}
</script>
</body>
</html>