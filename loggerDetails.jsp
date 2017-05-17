<%@page import="com.quinnox.ordermanagementsystem.daomodel.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>logGer</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>

<%
	Boolean logged=(Boolean)session.getAttribute("Logged");
	
	
	if(logged==null && logged==false ){ %>
	
	
	<h3 align="center">User please login</h3>
	<p align="center"><a href="index.jsp">GO TO LOGIN PAGE</a></p>
	<%}else{ %>
	
	<div id="header">
				<div id="logo">
					<img src="images/Europcar-logo.jpg" width="93%" height="100%">
					
				</div>
				
				
				
				
		 </div>
	<table width="100%" height="25%">
		
        
		<tr>
			<td width="100%" height="5%" bgcolor="#73B05E">

			<H2 ALIGN="left" style="color: white">Welcome <%=((User)session.getAttribute("user")).getName()%> , <%=((User)session.getAttribute("user")).getRole() %> </H2>
			
			<form action="FunctionServlet" method="post">
					<p align="right">
						<input type="SUBMIT"  value="Home" name="home" width="50pt" height="50pt" />
						<input type="button" value="Change Password" onclick="window.open('http://localhost:8082/OrderManagementSystem_10_APRIL_10am/Users/changePassword.jsp','window','width=300,height=300')"/>
						<input type="submit" name="logout" value="Logout" />
					</p>
				</form>
			</td>
		</tr>
		
		
	</table>
	<%} %>
</body>
</html>