<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电子书城</title>
<link rel="stylesheet" href="layui/css/layui.css">
<link rel="stylesheet" href="css/indexCss.css">
</head>

<body>
<!--顶部导航条-->
<div class="top">
<ul class="layui-nav layui-bg-green">
	<li class="layui-nav-item"><a href="index.jsp">首页</a></li>
	<li class="layui-nav-item"><a href="index-xs.jsp">小说</a></li>
	<li class="layui-nav-item"><a href="index-wx.jsp">文学</a></li>
	<li class="layui-nav-item"><a href="index-xl.jsp">心理</a></li>
	<li class="layui-nav-item"><a href="index-zx.jsp">哲学</a></li>
	<li class="layui-nav-item"><a href="index-jj.jsp">经济</a></li>
	<li class="layui-nav-item"><a href="index-ls.jsp">历史</a></li>
	<li class="layui-nav-item layui-this"><a href="index-jsj.jsp">计算机</a></li>
	<li class="layui-nav-item"><a href="index-sjmz.jsp">世界名著</a></li>
	<span class="searchContent">
		<input type="text" name="search" placeholder="请输入搜索内容"/>
		<a href=""><i class="layui-icon layui-icon-search"></i></a>
	</span>
		<c:choose>
		<c:when test="${sessionScope.user==null}">
		<span class="searchContent1">
			<span class="layui-breadcrumb" lay-separator="|">
			  <a href="login.jsp">登陆</a>
			  <a href="register.jsp">注册</a>		
			</span>	
		</span>		
		</c:when>
		<c:otherwise>
			<li class="layui-nav-item" style="margin-left:20%">
				<!--姓名最大的长度为10个字符,否则会涨爆导航条-->
    			<a href="client/personal.jsp"><img src="SystemImg/systemhead.jpg" class="layui-nav-img">我</a>
			    <dl class="layui-nav-child">
			      <dd><a href="client/personal.jsp">读者中心</a></dd>
			      <dd><a href="${pageContext.request.contextPath}/LogoutServlet">退出</a></dd>
			    </dl>
  			</li>
		</c:otherwise>
	</c:choose>
</ul>
</div>
<!--中部内容-->
<div class="center">
<c:forEach var="book" items="${booklist}" varStatus="varState">
<c:if test="${book.type=='计算机'}">
<div class="bookShow" style="margin-right:18px">
<div class="picture"><a href="${pageContext.request.contextPath}/FindBookDetailServlet?bid=${book.bid}" target="_blank"><img src="bookUrl/${book.imgUrl}" width="200" height="284" /></a></div>
<div class="bookName"><a href="${pageContext.request.contextPath}/FindBookDetailServlet?bid=${book.bid}" target="_blank">${book.bookName}</a></div>
</div>
</c:if>
</c:forEach>
</div>
<!--底部信息-->
<div class="bottom">
	<div class="content-top">
		<ul>
			<li>学校:广东建设职业技术学院(夏茅校区)</li>
			<li>系部:建筑信息系</li>
			<li>专业:软件技术</li>
			<li>班级:16软件1班</li>
			<li>完成日期:06月30日</li>
		</ul>
	</div>
	<div class="content-bottom">版权所有©2018-2020 侵权必究</div>
</div>
</body>
<script src="layui/layui.all.js"></script>
</html>