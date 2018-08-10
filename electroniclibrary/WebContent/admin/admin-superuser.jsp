<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>超级用户管理页面</title>
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
<!-- 只有root才有权限添加超级用户 -->
<c:if test="${sessionScope.superUser.uid==1}">
<a href="superuser-add.jsp"><i class="layui-icon layui-icon-add-circle"></i>添加超级用户</a>
</c:if>
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
  <c:forEach var="Users" items="${superUsers}">
  	<tr>
  		<td>${Users.uid}</td>
  		<td>${Users.username}</td>
  		<td>
  			<!-- 只有root才可以看到所有人的密码,其余的只能看到自己的密码 -->
  			<c:choose>
  			<c:when test="${sessionScope.superUser.uid==1}">
  				${Users.password}
  			</c:when>
  			<c:otherwise>
  				<c:if test="${Users.uid==sessionScope.superUser.uid}">
  				${Users.password}
 				</c:if>
 				<c:if test="${Users.uid!=sessionScope.superUser.uid}">
  				*****
 				</c:if>
 			</c:otherwise>
 			</c:choose>
  		</td>
  		<td>${Users.sex==null?'未填写':Users.sex}</td>
  		<td>${Users.mail==null?'未填写':Users.mail}</td>
  		<td>${Users.role}</td>
  		<td>${Users.state==1?'已激活':'未激活'}</td>
  		<td>${Users.state==1?'已使用':(Users.activeCode==null?'无激活码':Users.activeCode)}</td>
  		<td>${Users.registerTime}</td>
  		<td>
  			<!-- 只有root才是最高权限,可以删除超级用户和编辑用户,而不是root的只可以编辑自己信息,不可以删除自己、别人和编辑别人 -->
  			<c:choose>
  			<c:when test="${sessionScope.superUser.uid==1}">
  				<a href="javascript:;" onclick="edit(${Users.uid})"><i class="layui-icon layui-icon-edit"></i>编辑</a>&nbsp;&nbsp;
  				<c:if test="${Users.uid!=1}">
  				<a href="javascript:;" onclick="del(${Users.uid})"><i class="layui-icon layui-icon-delete"></i>删除</a>
  				</c:if>
  			</c:when>
  			<c:otherwise>
  				<c:if test="${Users.uid==sessionScope.superUser.uid}">
  				<a href="javascript:;" onclick="edit(${Users.uid})"><i class="layui-icon layui-icon-edit"></i>编辑</a>&nbsp;&nbsp;
 				</c:if>
 			</c:otherwise>
 			</c:choose>
  		</td>
  	</tr>
  </c:forEach>
  </tbody>
	</table>
</div>
<%
String msg = (String)request.getSession().getAttribute("upSuc");
if(msg != null) {
%>
<script type="text/javascript">
alert("<%=msg%>"); 
</script>
<%
request.getSession().removeAttribute("upSuc");
}
%>
<%
String msg1 = (String)request.getSession().getAttribute("dSuc");
if(msg1 != null) {
%>
<script type="text/javascript">
alert("<%=msg1%>"); 
</script>
<%
request.getSession().removeAttribute("dSuc");
}
%>
<%
String msg2 = (String)request.getSession().getAttribute("adminAddsuper");
if(msg2!= null) {
%>
<script type="text/javascript">
alert("<%=msg2%>"); 
</script>
<%
request.getSession().removeAttribute("adminAddsuper");
}
%>
<script>
	function del(Uid){
		if(confirm('确定要删除该用户吗？')){
			window.location='${pageContext.request.contextPath}/SuperUserDelServlet?uid='+Uid;
		}
	}
	function edit(Uid){
		window.location='superuser-edit.jsp?uid='+Uid;
	}
</script>
</body>
</html>