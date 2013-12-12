<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" import="java.util.*,com.gs.util.CookieUtils" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'my.jsp' starting page</title>
    
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
    <% 
    	CookieUtils cu = new CookieUtils();
    	if(cu.getc(request)) 
    	System.out.println("");
    	else 
    	response.sendRedirect("notlogin.jsp");
    %>
    This is my JSP page. <br>
    <input type="button" onclick="window.location.href='logout.jsp'" value="ÍË³öµÇÂ¼">
  </body>
</html>
