<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>超级用户编辑页面</title>
</head>
<link rel="stylesheet" href="../layui/css/layui.css">
<link rel="stylesheet" href="../css/xadmin.css">
			<c:if test="${sessionScope.superUser==null}">
				<%
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				%>
			</c:if>
<body>
<div style="width:600px;margin:0 auto;">
	<form action="${pageContext.request.contextPath}/ElectronicAddServlet" method="post" enctype="multipart/form-data">
	<table class="layui-table" lay-even lay-skin="nob" lay-size="lg">
	   <tr>
	   		<td>书名</td>
	   		<td><input type="text" name="bookName"  class="layui-input" required/></td>
	   </tr>
	   <tr>
	   		<td>作者</td>
	   		<td><input type="text" name="author"  class="layui-input" required/></td>
	   </tr>
	   <tr>
	   		<td>类型</td>
	   		<td>
	   			<select name="type" id="type">
	   				<option value="">请选择类型</option>
	   				<c:forEach var="t" items="${typelist}">
	   						<option value="${t.type}">${t.type}</option>
	   				</c:forEach>
	   			</select>
	   			<span id="typeText" style="color:red"></span>
	   		</td>
	   </tr>
	   <tr>
	   		<td>出版社</td>
	   		<td><input type="text" name="publisher" class="layui-input"/></td>
	   </tr>
	   <tr>
	   		<td>读书简介</td>
	   		<td><textarea name="introduce" rows="12" cols="50"></textarea></td>
	   </tr>
	   <tr>
	   		<td>图片</td>
	   		<td><input type="file" name="imgUrl" id="imgUrl"/><span id="imgText" style="color:red"></span></td>
	   </tr>
	   <tr>
	   		<td colspan="2"><input type="submit" value="添加" class="layui-btn" style="width:100%" onClick="return checkImgUrl()"/></td>
	   </tr>
	   <tr>
	   		<td colspan="2"><input type="button" value="取消" class="layui-btn layui-btn-warm" style="width:100%" onClick="cancelEdit()" /></td>
	   </tr>
	</table>
	</form>
</div>
<script type="text/javascript">
//取消操作
function cancelEdit(){
	window.location="admin-electronicbook.jsp";
}
//检查上传文件是否为空和检查上传文件类型是否为jpg
function checkImgUrl(){
	var bType=checkType();
	var imgurl=document.getElementById("imgUrl");
	var imgText=document.getElementById("imgText");
	if(imgurl.value=="" || imgurl.value==null){
		imgText.innerHTML="上传文件不能为空";
		return false;
	}
	var strArray=imgurl.value.split(".");
	if(strArray[1]!="jpg"){
		imgText.innerHTML="上传文件的类型不是jpg";
		return false && bType;
	}
	imgText.innerHTML="";
	return true && bType;
}
//检查类型是否选择空的
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