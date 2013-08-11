<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易寻方达查询系统</title>
<style type="text/css">
body{text-align:center;font-size:14px;font-family:arial,tahoma,sans-serif;}
div,ul,li{margin:0;padding:0;}
li{list-style:none;}
a{color:#0000FF;}
a:visited{text-decoration:underline;color:#0000FF;}
a:active,a:hover{color:#0000FF; text-decoration:underline;}
.tarea2{height:50px;text-align:center;width:650px;}
a.curr{text-decoration:none;color:#000000;}
.tbcon{
	text-align:left;
	padding:0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left: 8px;
}
.s_ipt {
	MARGIN: 5px 0px 0px 7px; OUTLINE-STYLE: none; OUTLINE-COLOR: invert; OUTLINE-WIDTH: 0px; WIDTH: 405px; FONT: 16px/22px arial; BACKGROUND: #fff; HEIGHT: 22px; -webkit-appearance: none
}
.s_btn {
	WIDTH: 95px; BACKGROUND: url(images/i-1_0_0.png) #ddd; HEIGHT: 32px; FONT-SIZE: 14px; CURSOR: pointer; PADDING-TOP: 2px
}
.s_btn_h {
	BACKGROUND-POSITION: -100px 0px
}
.s_btn_wr {
	Z-INDEX: 0; WIDTH: 97px; DISPLAY: inline-block; BACKGROUND: url(images/i-1_0_0.png) no-repeat -202px 0px; HEIGHT: 34px; VERTICAL-ALIGN: top
}
#Layer2 {
	position:absolute;
	left:618px;
	top:255px;
	width:95px;
	height:69px;
	z-index:1;
	visibility: hidden;
}
.STYLE2 {color: #0000FF; font-family:Arial, Helvetica, sans-serif}
.STYLE4 {color: #0000FF; font-family: Arial, Helvetica, sans-serif; font-size: 12px; text-decoration:underline;}
.STYLE5 {font-size: 12px; color:#666666}
.STYLE7 {font-size: 12px}
</style> 
<script type="text/javascript"> 
function setCookie(name, value, expires, path, domain, secure) 
{         
var curCookie = name + "=" + escape(value) + ((expires) ? "; expires=" + expires.toGMTString() : "") + ((path) ? "; path=" + path : "") + ((domain) ? "; domain=" + domain : "") +((secure) ? "; secure" : "");         
if ( (name + "=" + escape(value)).length <= 4000)
{document.cookie = curCookie;}         
else 
 if (confirm("Cookie exceeds 4KB and will be cut!")) 
 document.cookie = curCookie;} 
 function getCookie(name) 
 {         var prefix = name + "=";
          var cookieStartIndex = document.cookie.indexOf(prefix);
	   if (cookieStartIndex == -1)                 
	   return null;
		var cookieEndIndex = document.cookie.indexOf(";", cookieStartIndex + prefix.length);
	 if (cookieEndIndex == -1)                 
	 cookieEndIndex = document.cookie.length;
	  return unescape(document.cookie.substring(cookieStartIndex + prefix.length, cookieEndIndex));
 } 
 function savecookie(){          
 var cont1=document.getElementById("txt1").value;  
 if(cont1){          
 var data=new Date();          
 data.setTime(data.getTime() + 1*24*3600*1000*1000); //          
 setCookie("txt1",cont1,data);          
 }          
 var cont2=document.getElementById("txt2").value;  
 if(cont2){          
 var data=new Date();          
 data.setTime(data.getTime() + 1*24*3600*1000*1000); //          
 setCookie("txt2",cont2,data);          
 }          
 var cont3=document.getElementById("txt3").value;  
 if(cont3){          
 var data=new Date();          
 data.setTime(data.getTime() + 1*24*3600*1000*1000); //          
 setCookie("txt3",cont3,data);          
 }
 } 
 window.onload=function(){
 document.getElementById('txt1').focus();
 var cont=getCookie("txt1");     
 if(cont) document.getElementById("txt1").value=cont; 
 var cont=getCookie("txt2");     
 if(cont) document.getElementById("txt2").value=cont;
 var cont=getCookie("txt3");     
 if(cont) document.getElementById("txt3").value=cont;
 } 
 </script>
<script language="javascript" type="text/javascript" src="scripts/main.js">
</script>
<script>
function showShipbox()
{
	//document.getElementById("Layer2").style.visibility="visible";
//	document.getElementById("Layer2").style.left=event.screenX+"px";
//	document.getElementById("Layer2").style.top=document.getElementById("sclist").style.top+150+"px";
}
function sbmSearch(str)
{
	savecookie();
	if(str=="findCon")
	{
		if(document.form1.txt1.value=="" && document.form1.txt_ship.value=="")
		alert("请输入查询条件！");
		else
		window.location.href="main.jsp?op=findCon&par1="+document.form1.txt1.value+"&par2="+document.form1.txt_ship.value;
		//document.form1.submit();
	}
	if(str=="findShp")
	{
		if(document.form1.txt2.value=="")
		alert("请输入查询条件！");
		else
		window.location.href="main.jsp?op=findShp&par1="+document.form1.txt2.value+"&par2=";
		//document.form1.submit();
	}
	if(str=="findGps")
	{
		if(document.form1.txt3.value=="")
		alert("请输入查询条件！");
		else
		window.location.href="main.jsp?op=findGps&par1="+document.form1.txt3.value+"&par2=";
		//document.form1.submit();
	}
}
</script>
</head>
<body>
<form id="form1" name="form1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="80" colspan="3" align="center"></td>
  </tr>
  <tr>
    <td colspan="3" align="center"><img src="images/mlogo.jpg" width="127" height="73" /></td>
    </tr>
	<tr>
    <td colspan="3" align="center" height="50"></td>
    </tr>
   <tr>
    <td colspan="3" align="center">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="center"><a href="#" class="curr" id="tabap1_btn_0" onclick="tabit(this)">搜箱</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="STYLE2" id="tabap1_btn_1" onclick="tabit(this)">搜船</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="STYLE2" id="tabap1_btn_2" onclick="tabit(this)">智能铅封</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
          </tr>
        </table>
	  </td>
  </tr>
  
  <tr>
  <td width="29%" align="center">&nbsp;</td>
  <td width="53%" align="center" valign="top"><div class="tarea2" id="tarea2">
    <div class="tbcon" id="tabap1_div_0">
      <label>
      <input name="txt1" type="text" class="s_ipt" id="txt1" maxlength="100" />
	  <select name="select" style=" height:25px;" onchange="document.getElementById('txt_ship').value=this.value;">
		<option value="0">自动</option>
		<option value="APL">APL</option>
		<option value="CHINASHIPPING">CHINASHIPPING</option>
		<option value="CMA">CMA</option>
		<option value="CNC">CNC</option>
		<option value="COSCO">COSCO</option>
		<option value="CSAV">CSAV</option>
		<option value="DELMAS">DELMAS</option>
		<option value="EVERGREEN">EVERGREEN</option>
		<option value="HANJIN">HANJIN</option>
		<option value="KMTC">KMTC</option>
		<option value="MAERSK">MAERSK</option>
		<option value="MCC">MCC</option>
		<option value="MSC">MSC</option>
		<option value="MOL">MOL</option>
		<option value="SAFMARINE">SAFMARINE</option>
		<option value="TSline">TSline</option>
		<option value="WANHAI">WANHAI</option>
		<option value="YANGMING">YANGMING</option>
	  </select>
      <!--<input name="sclist" type="button" id="sclist" value="自动▼ " style="background-color:#FFFFFF; height:25px;" onclick="showShipbox()"/>-->
      </label>
      <label>
      <input type="button" name="findCon" value="查询一下" style="width:80px; height:30px;" onclick="sbmSearch('findCon');"/>
	  <!--<SPAN class=s_btn_wr><input class=s_btn onMouseOut="this.className='s_btn'" onMouseDown="this.className='s_btn s_btn_h'" value="查询一下" onclick="sbmSearch('findCon');"/></SPAN>-->
      <input name="text" type="text" id="txt_ship" style="visibility:hidden; width:1px;" value="0"/>
      </label>
    </div>
    <div class="tbcon" id="tabap1_div_1" style="display: none">
      <label>
      <input type="text" id="txt2" name="txt2" class="s_ipt" />
      </label>
      <label>
      <input type="button" name="findShp" value="查询一下" style="width:80px; height:30px;" onclick="sbmSearch('findShp');"/>
      </label>
    </div>
    <div class="tbcon" id="tabap1_div_2" style="display: none">
      <label>
      <input type="text" id="txt3" name="txt3" class="s_ipt" />
      </label>
      <label>
      <input type="button" name="findGps" value="查询一下" style="width:80px; height:30px;" onclick="sbmSearch('findGps');"/>
      </label>
    </div>
  </div></td>
  <td width="18%" align="center">&nbsp;</td>
  </tr>
  <tr>
  <td colspan="3" align="center">
    <span class="STYLE4" id="tip" style="visibility:visible">提示：进行搜箱操作时可以点击自动按钮选择船公司进行指定搜索！</span></td>
  </tr>
   <tr>
  <td colspan="3" height="100">
  </td>
  </tr>
  <tr>
  <td colspan="3" align="center"><p>
    <a href="#" class="STYLE4">关于易寻方达</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.efinder.com.cn');" class="STYLE4">将易</a><a href="#" class="STYLE4">寻</a><a href="#" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.efinder.com.cn');" class="STYLE4">方达设为首页</a></td>
  </tr>
  <tr>
  <td colspan="3" align="center"><p>
    <span class="STYLE5">@efinder&nbsp;&nbsp;</span><a href="#" class="STYLE7">使用易寻方达前必读</a></td>
  </tr>
</table>
</form>
</body>
</html>
