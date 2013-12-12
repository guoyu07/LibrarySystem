<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@taglib prefix="s"  uri="/struts-tags" %>>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Two Passwords are Different!</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   Two Passwords are Different! <br>
    <input type="button" onclick="window.location.href='register/register.jsp'" value="·µ»Ø">
    <s:debug></s:debug>
  </body>
</html>
