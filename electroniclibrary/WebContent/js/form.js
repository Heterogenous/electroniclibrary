//1.定义变量
var username;
var password;
var password1;
var mail;
//1.1定义提示框
var usernameText;
var passwordText;
var password1Text;
var mailText;
//当页面加载完成时获取表单信息
window.onload=function(){
	//获取文本框元素
	username=document.getElementById("username");
	password=document.getElementById("password");
	password1=document.getElementById("password1");
	mail=document.getElementById("mail");
	//获取错误框元素
	usernameText=document.getElementById("usernameText");
	passwordText=document.getElementById("passwordText");
	password1Text=document.getElementById("password1Text");
	mailText=document.getElementById("mailText");
	
}

//检验整个表单
function checkForm(){
	var bUsername=checkUsername();
	var bPassword=checkPassword();
	var bConfirm=checkConfirm();
	var bMail=checkMail();
	return bUsername && bPassword && bConfirm && bMail;
}
//检验用户名格式
function checkUsername(){
	var value=username.value;
	var regex=/^[a-z]\w{3,19}$/;//不知为何text类型的文本框至少4最大20是{3,19}
	if(value==""){
		usernameText.innerHTML="用户名不能为空";
		return false;
	}
	if(regex.test(value)){
		usernameText.innerHTML="√";
		return true;
	}else{
		usernameText.innerHTML="×用户格式不正确";
		return false;
	}
	
}
//检验密码格式
function checkPassword(){
	var value=password.value;
	var regex=/^[a-z0-9A-Z]{4,20}$/;
	if(value==""){
		passwordText.innerHTML="密码不能为空";
		return false;
	}
	if(regex.test(value)){
		passwordText.innerHTML="√";
		return true;
	}else{
		passwordText.innerHTML="×密码格式不正确";
		return false;
	}
	
}
//检验两次密码是否一致
function checkConfirm(){
	var value=password.value;
	var value1=password1.value;
	if(value1==""){
		password1Text.innerHTML="确认密码不能为空";
		return false;
	}
	if(value==value1){
		password1Text.innerHTML="√";
		return true;
	}else{
		password1Text.innerHTML="×两次密码不一致";
		return false;
	}
	
}
//检验邮箱格式
function checkMail(){
	var value=mail.value;
	var regex=/^[a-z0-9A-z]+@[a-z0-9]+\.(com)|(net)$/;
	if(value==""){
		mailText.innerHTML="邮箱不能为空";
		return false;
	}
	if(regex.test(value)){
		mailText.innerHTML="√";
		return true;
	}else{
		mailText.innerHTML="×邮箱格式不正确";
		return false;
	}
}




