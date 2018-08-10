<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借阅表详情页面</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/xadmin.css">
			<c:if test="${sessionScope.superUser==null}">
				<%
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				%>
			</c:if>
<body>
<div class="x-body layui-anim layui-anim-up">
<form action="${pageContext.request.contextPath}/SuperReturnBookServlet?borrowId=${bborrow.borrowId}" method="post">
<table class="layui-table" lay-even lay-size="lg">
    <tr>
      	<td>借阅ID</td>
      	<td>${bborrow.borrowId}</td>
    </tr> 
    <tr>
      	<td>书名</td>
      	<td>${bbook.bookName}</td>
    </tr>
    <tr>
      	<td>读者账号</td>
      	<td>${buser.username}</td>
    </tr>
    <tr>
      	<td>借阅时间</td>
      	<td>${bborrow.borrowTime}</td>
    </tr>
    <tr>
      	<td>归还时间</td>
      	<td>${bborrow.returnTime}</td>
    </tr>
    <tr>
      	<td>剩余阅读时间</td>
      	<td>${bborrow.readDay}<input type="hidden" value="${bborrow.readDay}" id="readDay" /></td>
    </tr>
    <tr>
      	<td colspan="2"><input type="submit" value="归还电子书" class="layui-btn" onclick="return checkReadDay()" style="width:100%"/></td>
    </tr>
    <tr>
      	<td colspan="2"><input type="button" value="返回" class="layui-btn layui-btn-warm" onclick="backTo()" style="width:100%"/></td>
    </tr>
	</table>
</form>
</div>
<script>
//检查天数是否已经到
function checkReadDay(){
	var readDay=document.getElementById("readDay");
	if(readDay.value!=0){
		alert("未到归还期,不允许归还!");
		return false
	}
	return true
}
//返回借阅表
	function backTo(){
		window.location='${pageContext.request.contextPath}/admin/admin-borrowbook.jsp';
	}
</script>
</body>
</html>