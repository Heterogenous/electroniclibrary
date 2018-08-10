<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电子书编辑页面</title>
</head>
<link rel="stylesheet" href="../layui/css/layui.css">
<link rel="stylesheet" href="../css/xadmin.css">
			<c:if test="${sessionScope.superUser==null}">
				<%
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				%>
			</c:if>
<body>
	<% 
		String bid=request.getParameter("bid");
		request.setAttribute("bid", bid);
	%>
<div style="width:600px;margin:0 auto;">
	<form action="${pageContext.request.contextPath}/ElectronicEditServlet?bid=${bid}" method="post">
	<table class="layui-table" lay-even lay-skin="nob" lay-size="lg">
	<c:forEach var="Books" items="${books}">
	<c:if test="${bid==Books.bid}">
	   <tr>
	   		<td>ID</td>
	   		<td>${Books.bid}</td>
	   </tr>
	   <tr>
	   		<td>书名</td>
	   		<td><input type="text" name="bookName" value="${Books.bookName}" class="layui-input" required/></td>
	   </tr>
	   <tr>
	   		<td>作者</td>
	   		<td><input type="text" name="author" value="${Books.author}" class="layui-input" required/></td>
	   </tr>
	   <tr>
	   		<td>类型</td>
	   		<td>
	   			<select name="type" id="type">
	   				<option value="">请选择类型</option>
	   				<c:forEach var="t" items="${typelist}">
	   					<c:if test="${t.type==Books.type}">
	   						<option value="${Books.type}" selected>${Books.type}</option>
	   					</c:if>
	   					<c:if test="${t.type!=Books.type}">
	   						<option value="${t.type}">${t.type}</option>
	   					</c:if>
	   				</c:forEach>
	   			</select>
	   			<span id="typeText" style="color:red"></span>
	   		</td>
	   </tr>
	   <tr>
	   		<td>出版社</td>
	   		<td><input type="text" name="publisher" value="${Books.publisher}" class="layui-input"/></td>
	   </tr>
	   <tr>
	   		<td>读书简介</td>
	   		<td><textarea name="introduce" rows="12" cols="50">${Books.introduce}</textarea></td>
	   </tr>
	   <tr>
	   		<td>图片名称</td>
	   		<td><input type="text" name="imgUrl" value="${Books.imgUrl}" class="layui-input" required/></td>
	   </tr>
	   <tr>
	   		<td>借阅次数</td>
	   		<td>${Books.borrowTimes}</td>
	   </tr>
	   <tr>
	   		<td>上传时间</td>
	   		<td>${Books.uploadTime}</td>
	   </tr>
	   <tr>
	   		<td colspan="2"><input type="submit" value="修改" class="layui-btn" style="width:100%" onclick="return checkType()" /></td>
	   </tr>
	   <tr>
	   		<td colspan="2"><input type="button" value="取消" class="layui-btn layui-btn-warm" style="width:100%" onClick="cancelEdit()" /></td>
	   </tr>
	</c:if>
	</c:forEach>
	</table>
	</form>
</div>
<c:if test="${sessionScope.imgAlready!=null}">
	<script>
	alert("${sessionScope.imgAlready}");
	</script>
	<%
		request.getSession().removeAttribute("imgAlready");
	%>
</c:if>
<script type="text/javascript">
//取消操作
function cancelEdit(){
	window.location="admin-electronicbook.jsp";
}
//检查类型是否为空
function checkType(){
	var type=document.getElementById("type");
	var typeText=document.getElementById("typeText");
	if(type.value==""){
		typeText.innerHTML="请选择分类!";
		return false;
	}
	typeText.innerHTML="";
	return true;
}
</script>
</body>
</html>