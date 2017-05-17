<%@page import="com.quinnox.ordermanagementsystem.daomodel.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders History</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>
<%Boolean logg=(Boolean)session.getAttribute("Logged");
	if(logg==null&&logg==false)
	{request.getRequestDispatcher("/index.jsp").forward(request,response);
	}
	else{
	%>
	<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">
<fieldset><legend align="center">ORDERS HISTORY</legend>
<form action="ViewAuditLogServlet">
<table border="1" align="center">
	<tr>
		<th align="CENTER">OID</th>
				<th align="CENTER">STATUS</th>
				<th align="CENTER">TIME</th>
				<th align="center">AUDITLOG</th>
				<th align="center">ORDER DETAILS</th>
	</tr>
<%ArrayList orders=(ArrayList)request.getAttribute("orders"); 
ArrayList orderIds=new ArrayList();
for(Object obj:orders){
orderIds.add(((Order)obj).getOrderId());%>
<tr><td><%=((Order)obj).getOrderId() %></td>
	<td><%=((Order)obj).getStatus() %></td>
	<td><%=((Order)obj).getTime() %></td>
	<td><input type="SUBMIT" VALUE="VIEW AUDITLOG" name="a<%=((Order)obj).getOrderId()%>"/></td>
	<td><input type="SUBMIT" VALUE="VIEW ORDER" name="o<%=((Order)obj).getOrderId()%>"/></td></tr>
<%} %>	
		</table>
		</form>
		
		</fieldset>
		<%session.setAttribute("orderIds",orderIds);}  %>
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