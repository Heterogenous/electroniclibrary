<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
</head>
<link rel="stylesheet" href="../layui/css/layui.css">
<link rel="stylesheet" href="../css/indexCss.css">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="./css/font.css">
<link rel="stylesheet" href="./css/xadmin.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="./js/xadmin.js"></script>
<script type="text/javascript" src="../layui/layui.js"></script>
<body>
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="">管理中心</a></div>
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
        </div>
        <span id="YMD" style="line-height:45px;margin-left:10%;color:white"></span><span id="week" style="margin-left:3%;color:white"></span><span id="time" style="margin-left:3%;color:white"></span>
        <c:choose>
			<c:when test="${sessionScope.superUser==null}">
				<%
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				%>
			</c:when>
		<c:otherwise>
	        <ul class="layui-nav right">
				<li class="layui-nav-item">
					<a href="javascript:;">${sessionScope.superUser.username}</a>
					<dl class="layui-nav-child"> <!-- 二级菜单 -->
					  <dd><a href="${pageContext.request.contextPath}/AdminLogoutServlet">退出系统</a></dd>
					</dl>
			  	</li>
	         	<li class="layui-nav-item to-index"><a href="../index.jsp" target="_blank">网站首页</a></li>
	        </ul>
         </c:otherwise>
     </c:choose>
     </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
	<div style="width:1100px; margin:0 auto;">
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>用户管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="admin-normaluser.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>普通用户管理</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="admin-superuser.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>超级用户管理</cite>
                        </a>
                    </li >
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>产品管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="admin-electronicbook.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>电子书管理</cite>
                        </a>
                    </li >
                     <li>
                        <a _href="admin-borrowbook.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>借阅管理</cite>
                        </a>
                    </li >
                </ul>
            </li>
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='welcome.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
	</div>
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <div class="copyright" style="text-align:center">版权所有©2018-2020 侵权必究</div>  
    </div>
    <!-- 底部结束 -->
    <script src="../js/time.js" type="text/javascript"></script>
</body>
</html>