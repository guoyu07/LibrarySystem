<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>�û�ע��</title>
    
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
  <form name="form1" method="post" action="userAction">
    <table width="399" border="0" align="center">
      <tr>
        <td width="163">�û�����</td>
        <td width="226"><input type="text" name="username"></td>
      </tr>
      <tr>
        <td>����:</td>
        <td><input type="password" name="password"></td>
      </tr>
      <tr>
        <td>ȷ�����룺</td>
        <td><input type="password" name="password2"></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value="��"></td>
      </tr>
    </table>
  </form>
  </body>
</html>
