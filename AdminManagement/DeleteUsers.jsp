<%@page import="com.quinnox.ordermanagementsystem.daomodel.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page errorPage="ShowError.jsp" %>
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
<script type="text/javascript">
function chkUserId()
{
	
	 var userId = document.delUser.userId.value;
		if(userId==null || userId=="" || isNaN(userId))
		{
			//document.getElementById("unameError").innerHTML="Enter Proper Username ";
			alert("Enter correct User ID");
			flag=0;
			return false;
		}
		
		
		return true;
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
<fieldset >
<div>
<form action="DeleteUsers" method="post" name="delUser" >
Enter The User ID to be Deleted:
<input type="text" name="userId" value=""/>
<input type="submit" value="Retrieve Details" name="submit" onclick="return chkUserId()"/>
</form>
</div>

<div>
<% ArrayList userDetails=new ArrayList();
	userDetails=(ArrayList)request.getAttribute("userDetails");
	User user=new User();
	int uId,managerId;
	String uName,role;
	if(userDetails!=null)
	{
		for(Object obj:userDetails)
		{	
			user=(User)obj;
			uId=user.getUid();
			uName=user.getName();
			role=user.getRole();
			managerId=user.getManagerId();
			%>
	<table cellpadding="10" border="2">	
	<tr>
		
	<th>USER ID </th>	
	<th>USERNAME </th>
	<th>ROLE</th>
	<th>MANAGER ID </th>
	<th>Delete</th>
	<th> </th>
	
	</tr>
	
	<tr>
	<td><%=uId%></td>	
	<td> <%=uName%></td>
	<td><%=role%></td>
	<td><%=managerId%> </td>
	<td>
	<%session.setAttribute("userId",uId); %>
	<form action="ConfrimDelete" method="post" >
	<input type="submit" value="confirmdelete" name="ConfirmDelete" />
	</form>
	</td>
	</tr>
	<%}
		} %>
</table>

</div>
<div>
<% 
	ArrayList employees=new ArrayList();
	employees=(ArrayList)request.getAttribute("employees");
	User user1=new User();
	int uId1;
	String uName1,role1,phoneNumber,email;
	if(employees!=null)
	{
		for(Object obj:employees)
		{	
			user1=(User)obj;
			uId=user1.getUid();
			uName=user1.getName();
			phoneNumber=user1.getPhoneNumber();
			email=user1.getEmail();
			%>
	<table cellpadding="10" border="2">	
	<tr>
		
	<th>USER ID </th>	
	<th>USERNAME </th>
	<th>PHONE NUMBER</th>
	<th>EMAIL</th>
	
	

	</tr>
	
	<tr>
	<td><%=uId%></td>	
	<td> <%=uName%></td>
	<td><%=phoneNumber%></td>
	<td><%=email%> </td>
	<td> </td>
	
	</table>
	<%
	    }
	}
		else
		{
			String msg= (String)request.getAttribute("message");
			if(msg!=null)
			{
				out.println("<h3>"+msg+"</h3>");
			}
			
		}
	
	 %>
	 <%
	    if(request.getAttribute("delUser")!=null)
	    {
	    	String msg1= (String)request.getAttribute("delUser");
	 		if(msg1!=null)
	 		{
	 			out.println("<h3>"+msg1+"</h3>");
	 		}
	 	 	    	
	    }
	    else if(request.getAttribute("delUser1")!=null)
	    {
	    	String msg1= (String)request.getAttribute("delUser1");
	 		if(msg1!=null)
	 		{
	 			out.println("<h3>"+msg1+"</h3>");
	 		}
	 	 	    	
	    }
	   
	 %>
</div>
</fieldset>
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