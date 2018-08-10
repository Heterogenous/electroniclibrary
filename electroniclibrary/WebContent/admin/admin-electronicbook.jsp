<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电子书管理页面</title>
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
<a href="electronicbook-add.jsp"><i class="layui-icon layui-icon-add-circle"></i>添加电子书</a>
<table class="layui-table" lay-even lay-size="lg">
  <thead>
    <tr>
      <th>ID</th>
      <th>图片</th>
      <th>书名</th>
      <th>作者</th>
      <th>类别</th>
      <th>借阅次数</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody>
  <c:forEach var="Books" items="${books}">
  	<tr>
  		<td>${Books.bid}</td>
  		<td><img src="${pageContext.request.contextPath}/bookUrl/${Books.imgUrl}" width="100" height="150" /></td>
  		<td>${Books.bookName}</td>
  		<td>${Books.author}</td>
  		<td>${Books.type}</td>
  		<td>${Books.borrowTimes}</td>
  		<td>
  			<a href="javascript:;" onclick="find('${Books.bid}')"><i class="layui-icon layui-icon-list"></i>详情</a>&nbsp;&nbsp;
  			<a href="javascript:;" onclick="edit('${Books.bid}')"><i class="layui-icon layui-icon-edit"></i>编辑</a>&nbsp;&nbsp;
  			<a href="javascript:;" onclick="del('${Books.bid}')"><i class="layui-icon layui-icon-delete"></i>删除</a>
  		</td>
  	</tr>
  </c:forEach>
  </tbody>
	</table>
</div>
<c:if test="${sessionScope.updateBook!=null}">
	<script>
	alert("${sessionScope.updateBook}");
	</script>
	<%
		request.getSession().removeAttribute("updateBook");
	%>
</c:if>
<%
	String msg=(String)request.getSession().getAttribute("delBook");
	if(msg!=null){
	%>
	<script>
		alert("<%=msg%>");
	</script>
	<%
	request.getSession().removeAttribute("delBook");
	}
%>
<%
	String msg1=(String)request.getSession().getAttribute("AddBook");
	if(msg1!=null){
	%>
	<script>
		alert("<%=msg1%>");
	</script>
	<%
	request.getSession().removeAttribute("AddBook");
	}
%>
<script>
	function del(Bid){
		if(confirm('确定要删除该电子书吗？')){
			window.location='${pageContext.request.contextPath}/ElectronicDelServlet?bid='+Bid;
		}
	}
	function edit(Bid){
		window.location='electronicbook-edit.jsp?bid='+Bid;
	}
	function find(Bid){
		window.location='electronicbook-find.jsp?bid='+Bid;
	}
</script>
</body>
</html>