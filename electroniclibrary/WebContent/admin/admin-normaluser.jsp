<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户管理页面</title>
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
<a href="normaluser-add.jsp"><i class="layui-icon layui-icon-add-circle"></i>添加普通用户</a>
<table class="layui-table" lay-even lay-size="lg">
  <thead>
    <tr>
      <th style="width:30px">ID</th>
      <th style="width:30px">用户名</th>
      <th style="width:30px">密码</th>
      <th style="width:20px">性别</th>
      <th style="width:80px">电子邮件</th>
      <th>用户权限</th>
      <th>激活状态</th>
      <th>激活码</th>
      <th style="width:80px">注册时间</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody>
  <c:forEach var="Users" items="${normalUsers}">
  	<tr>
  		<td>${Users.uid}</td>
  		<td>${Users.username}</td>
  		<td>${Users.password}</td>
  		<td>${Users.sex==null?'未填写':Users.sex}</td>
  		<td>${Users.mail==null?'未填写':Users.mail}</td>
  		<td>${Users.role}</td>
  		<td>${Users.state==1?'已激活':'未激活'}</td>
  		<td>${Users.state==1?'已使用':(Users.activeCode==null?'无激活码':Users.activeCode)}</td>
  		<td>${Users.registerTime}</td>
  		<td>
  			<a href="javascript:;" onclick="edit(${Users.uid})"><i class="layui-icon layui-icon-edit"></i>编辑</a>&nbsp;&nbsp;
  			<a href="javascript:;" onclick="del(${Users.uid})"><i class="layui-icon layui-icon-delete"></i>删除</a>
  		</td>
  	</tr>
  </c:forEach>
  </tbody>
	</table>
</div>
<%
String msg = (String)request.getSession().getAttribute("updateSuc");
if(msg != null) {
%>
<script type="text/javascript">
alert("<%=msg%>"); 
</script>
<%
request.getSession().removeAttribute("updateSuc");
}
%>
<%
String msg1 = (String)request.getSession().getAttribute("delSuc");
if(msg1 != null) {
%>
<script type="text/javascript">
alert("<%=msg1%>"); 
</script>
<%
request.getSession().removeAttribute("delSuc");
}
%>
<%
String msg2 = (String)request.getSession().getAttribute("adminAdd");
if(msg2!= null) {
%>
<script type="text/javascript">
alert("<%=msg2%>"); 
</script>
<%
request.getSession().removeAttribute("adminAdd");
}
%>
<script>
	function del(Uid){
		if(confirm('确定要删除该用户吗？')){
			window.location='${pageContext.request.contextPath}/NormalUserDelServlet?uid='+Uid;
		}
	}
	function edit(Uid){
		window.location='normaluser-edit.jsp?uid='+Uid;
	}
</script>
</body>
</html>