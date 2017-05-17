<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.quinnox.ordermanagementsystem.daoclasses.UserDAO"%>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
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
		//	System.out.println("logged null");
%>

<jsp:forward page="/index.jsp"></jsp:forward>

<%
	} else {
%>
<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page"><%!User user;%> <%
 	/* String message=(String)request.getAttribute("message");
 																																																																																					 if(message!=null)
 																																																																																					 {
 																																																																																					 out.println(""+message);	
 																																																																																					 }
 		 */
 %> <%
 	HashMap userDetails = userDetails = (HashMap) request
 				.getAttribute("UserDetails");
 		Set keys = userDetails.keySet();
 		Collection values = userDetails.values();
 		Iterator it = keys.iterator();
 		int orderId = 0;
 		int userId = 0;
 		String userName = null;
 %>




<fieldset style="width: 240pt height :                     150pt">
<div align="right">

<form method="post" action="OrderItemRetrieval1">

<table align="center" border="1">
	<caption>ORDERS PENDING</caption>

	<tr>

		<th align="CENTER">EMPLOYEE ID</th>
		<th align="CENTER">EMPLOYEE NAME</th>
		<th align="CENTER">Order ID</th>
		<th align="CENTER">VIEW ORDER</th>
	</tr>
	<%
		request.setAttribute("orders", keys);
			for (Object obj : keys) {
				int key1 = (Integer) obj;
				orderId = key1;
				User user1 = (User) userDetails.get(key1);
				userId = user1.getUid();
				userName = user1.getName();
				//  System.out.println(orderId+"........................");
				// request.setAttribute("orderId",orderId);
	%>

	<tr>

		<td><%=userId%></td>
		<td><%=userName%></td>
		<td><%=orderId%></td>
		<td><input type="submit" value="VIEW ORDER" name="<%=orderId%>" />

		</td>
	</tr>
	<%
		}
	%>
</table>

</form>

</div>
</fieldset>


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