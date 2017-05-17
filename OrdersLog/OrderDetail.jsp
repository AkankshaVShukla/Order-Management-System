<%@page import="java.util.ArrayList"%>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>order details</title>
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
<fieldset>
				<legend ALIGN="CENTER">ORDER DETAILS</legend>
				<table align="center" border="1">
				<tr>
				<th>ORDER ID</th><th>ITEMNAME</th><th>SUPPLIER</th><th>QUOTED PRICE</th>
				</tr>
				
<% ArrayList orders=(ArrayList)request.getAttribute("orders");
if(orders!=null)
{	int i=0;
	for(Object object:orders)
	{Order order=(Order)object;
	%><tr>
		<td><%=order.getOrderId() %></td>
		<td><%=order.getItemName() %></td>
		<td><%=order.getStatus() %></td>
		<td><%=order.getQuotedPrice() %></td>
		
	</tr>
	<%}%>
	
</table>
	</fieldset>
	<%} else{%>
	<h3 align="center">NO ITEM</h3>
	
<%}%>

	
<% }%>
		
		
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