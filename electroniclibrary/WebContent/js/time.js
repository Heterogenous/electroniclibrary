// JavaScript Document
window.onload=function(){
var today = new Date();
//年月日
var year=today.getYear()+1900;
var month=today.getMonth()+1;
if(month<10)month="0"+month;
var day=today.getDate();
if(day<10)day="0"+day;
document.getElementById("YMD").innerText=year+"年"+month+"月"+day+"日";
//星期
var week; 
if(today.getDay()==0) week="星期日" 
if(today.getDay()==1) week="星期一" 
if(today.getDay()==2) week="星期二" 
if(today.getDay()==3) week="星期三" 
if(today.getDay()==4) week="星期四" 
if(today.getDay()==5) week="星期五" 
if(today.getDay()==6) week="星期六" 
document.getElementById("week").innerText=week;
//调用时分秒
newTs();
}
//时分秒
function newTs(){
var today = new Date();
hrNow = today.getHours();//时
if(hrNow<10)hrNow="0"+hrNow;
mnNow = today.getMinutes();//分
if(mnNow<10)mnNow="0"+mnNow;
scNow = today.getSeconds();//秒
if(scNow<10)scNow="0"+scNow;
document.getElementById("time").innerText = hrNow+':'+ mnNow+':'+scNow;
setTimeout("newTs()",500);
}
