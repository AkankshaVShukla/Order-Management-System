<%@page import="com.quinnox.ordermanagementsystem.daomodel.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DETAILS</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>
     <% User user=(User)session.getAttribute("user");
    if(user!=null){
     %><P ALIGN="center">
     <table>
     	<tr><td align="left">User Id:</td><td align="LEFT"> <%=user.getUid() %></td></tr>
     	<tr><td align="left">Name: </td><td align="left"> <%=user.getName() %></td></tr>
     	<tr><td align="left">Role:</td><td align="LEFT"><%=user.getRole() %></td></tr>
     	<tr><td align="RIGHT"><input type="button" value="OK" onclick="window.close()"></td></tr>
     </table>
   </P>
    <% }
    else{
    request.getRequestDispatcher("index.jsp").forward(request,response);}%>
    
</body>
</html>