<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<script type="text/javascript" src="js/form.js" ></script>
<body>
<form action="${pageContext.request.contextPath }/RegisterServlet" method="post">
	<table>
		<tr>
			<td>用户名:</td>
			<td><input type="text" id="username" name="username" placeholder="请填写注册的用户名" value="${sessionScope.username}"/></td>
			<td><div id="usernameText" style="color:red">${sessionScope.UsernameAlready}</div></td>
		</tr>
		<tr>
			<td>密码:</td>
			<td><input type="password" id="password" name="password" placeholder="请填写注册的密码" value="${sessionScope.password}" /></td>
			<td><div id="passwordText" style="color:red"></div></td>
		</tr>
		<tr>
			<td>确认密码:</td>
			<td><input type="password" id="password1" name="password1" placeholder="请再次填写注册的密码" value="${sessionScope.password}" /></td>
			<td><div id="password1Text" style="color:red"></div></td>
		</tr>
		<tr>
			<td>性别:</td>
			<td>
				<input type="radio"  name="sex" value="男" ${sex==null?'checked':(sex=='男'?'checked':'') }/>男&nbsp;&nbsp;
				<input type="radio"  name="sex" value="女" ${sex=='女'?'checked':'' }/>女
			</td>
			<td></td>
		</tr>
		<tr>
			<td>电子邮件:</td>
			<td><input type="text" id="mail" name="mail" placeholder="邮件格式:xxx@xxx.com" value="${sessionScope.mail}" /></td>
			<td><div id="mailText" style="color:red">${sessionScope.MailAlready}</div></td>
		</tr>
		<tr>
			<td colspan="3"><input type="submit" value="  注  册  " onClick="return checkForm();"/></td>
		</tr>
	</table>
</form>
<hr/>
<h3 style="color:red">
	温馨提示:<br/>
	填写电子邮件一定需要正确的，用来接收服务器发送的注册激活码，否则将收不到就注册不成功!
</h3>
</body>
</html>