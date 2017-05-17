<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,com.quinnox.ordermanagementsystem.daomodel.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Users</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>

<body>
<script language="javascript">

	
	
	function back()
	{
		document.view.action="Users/administrator.jsp";
		document.view.method="POST"; 
		document.view.submit(); 
	}
</script>
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

<%
	ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
	Iterator<User> it = users.iterator();
	User user = null;
%>
<form name="view" action="" method="">
<table border="1">
	<tr bgcolor="#73B05E">
		<th>UserId</th>
		<th>UserName</th>
		<th>Role</th>
		<th>Manager Id</th>
		<th>Date of Birth</th>
		<th>Phone Number</th>
		<th>Email</th>
		
	</tr>
	<%
		while (it.hasNext()) {
			user = (User) it.next();
	%>
	<tr>
		<td><input type="text" name="uid" value="<%=user.getUid()%>" readonly></td>
		<td><input type="text" name="uname" value="<%=user.getName()%>" readonly></td>
		<td><input type="text" name="role" value="<%=user.getRole()%>" readonly></td>
		<td><input type="text" name="managerId" value="<%=user.getManagerId()%>" readonly></td>
		<td><input type="text" name="dob" value="<%=user.getDob()%>" readonly></td>
		<td><input type="text" name="phoneNumber" value="<%=user.getPhoneNumber()%>" readonly></td>
		<td><input type="text" name="email" value="<%=user.getEmail()%>" readonly></td>
	</tr>

	<%
		}
	%>
	
</table>

</form>
<%} %>

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