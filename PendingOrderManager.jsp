<%@page import="com.quinnox.ordermanagementsystem.daomodel.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>
<%
	Boolean logg = (Boolean) session.getAttribute("Logged");
	if (logg == null || logg == false) {
		System.out.println("logged null");
%>
<jsp:forward page="/index.jsp"></jsp:forward>

<%
	} else {
%>

<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">
Employees Under This Manager:
<% ArrayList managerEmployees=(ArrayList)request.getAttribute("managerEmployees");
ArrayList managerNames=(ArrayList)request.getAttribute("managerNames");

	int uId=0;

	String phone=null;
	String email=null;
	String uName=null;
%>
	<table cellpadding="10" border="2">	
	<tr>
		
	<th>USER ID </th>	
	<th>USERNAME </th>
	<th>PHONE</th>
	<th>EMAIL ID </th>
	<th> </th>
	
	</tr>
	<%
		for(Object obj:managerEmployees)
		{	
			User user=(User)obj;
			uId=user.getUid();
			uName=user.getName();
			phone=user.getPhoneNumber();
			email=user.getEmail();
		%>
	<tr>
	<td><%=uId%></td>	
	<td> <%=uName%></td>
	<td><%=phone%></td>
	<td><%=email%> </td>
	<td>
	</td>
	</tr>
	<%}
		%>
		</table>
		
	
		
	<div>	
		
		
	<%	int managerId=0;
	String managerName=null;
	%>
	<form action="DeleteManagerrr" name="">
					
	<select id="mana1" name="managerName">
	<option value=0 selected>Select A New Manager To Assign</option>

			<%
			if(managerNames!=null)
			{
			for(Object obj:managerNames)
			{	
				User user=(User)obj;
				managerId=user.getUid();
				managerName=user.getName();
			%>
			
			<option value=<%=managerId%> id=<%=managerId%>><%=managerName%></option>
						<%
							}
			}
						%>
					</select>
					<% %>
					<input type="submit" value="Update Manager" name="managerSubmit" />
					<%%>
					</form>
					</div>
			<%
	}
%> 

<br class="clearfix" />
<br class="clearfix" />
<br class="clearfix" />
<br class="clearfix" />
<br class="clearfix" />
</div>
<br class="clearfix" />

</div>
</div>


<%@ include file="/footer.jsp"%></div>
					

</body>
</html>