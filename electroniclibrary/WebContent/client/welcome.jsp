<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>读者欢迎页面</title>
</head>
<link rel="stylesheet" href="../layui/css/layui.css">
<link rel="stylesheet" href="./css/xadmin.css">
<body>
<div class="x-body layui-anim layui-anim-up">
	<blockquote class="layui-elem-quote">欢迎您读者:<span class="x-red">${sessionScope.user.username}</span>!</blockquote>
</div>
</body>
</html>