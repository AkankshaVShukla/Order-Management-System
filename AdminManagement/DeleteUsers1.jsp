<%@page import="com.quinnox.ordermanagementsystem.daomodel.User"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete User</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>
<%
	Boolean logg = (Boolean) session.getAttribute("Logged");
	if (logg == null || logg == false) {
		System.out.println("logged null");
		response.sendRedirect("http://localhost:8082/OrderManagementSystem_10_APRIL_10am/index.jsp");
		
%>


<%
	} else {
%>

<div id="outer">
<%@include file="/loggerDetails.jsp"%>
<div id="wrapper">
<div id="mainbody">
<div id="page">

<div>
<form action="..//DeleteUsers1" method="post" name="delUser">
Enter The User ID to be Deleted:
	 <input type="text" name="userId" value="" />
	 <input type="submit" value="Retrieve Details" name="submit" />
	</form>
</div>
<div>
<%
	ArrayList populatedUsers = (ArrayList) request
				.getAttribute("populatedUsers");
		User user = new User();
		int uId, managerId;
		String uName, role;
		if (populatedUsers != null) {
			for (Object obj : populatedUsers) {
				user = (User) obj;
				uId = user.getUid();
				uName = user.getName();
				role = user.getRole();
				managerId = user.getManagerId();
%>
<table cellpadding="10" border="2">
	<tr>

		<th>USER ID</th>
		<th>USERNAME</th>
		<th>ROLE</th>
		<th>MANAGER ID</th>
		<th>Delete</th>
		<th></th>

	</tr>

	<tr>
		<td><%=uId%></td>
		<td><%=uName%></td>
		<td><%=role%></td>
		<td><%=managerId%></td>
		<td>
		<%
			session.setAttribute("userId", uId);
						session.setAttribute("role", role);
		%>
		<form action="DeleteUsers1" method="post"><input type="submit"
			value="confirm delete" name="confirmdelete" /></form>
		</td>
	</tr>
	<%
		}
			}
	%>
</table>

</div>


<%
	}
%> <br class="clearfix" />
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