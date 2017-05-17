<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>
<%Boolean logger=(Boolean)session.getAttribute("Logged");
if(logger==null||logger==false){%>
<%@ include file="/index.jsp"%>
<%}else{%>
	<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">
<fieldset><legend align="center">Orders For Approval</legend>
 <form method="post"  action="SupplierOrderFunctionServlet">
<table align="CENTER" border="1"><tr><th>ORDER ID</th><th>TIME</th><TH>PLACED BY ID</TH><TH>FUNCTION</TH></tr>

<%ArrayList orders=(ArrayList)request.getAttribute("orders");
application.setAttribute("orders",orders);
HashSet orderId=new HashSet();

for(Object obj:orders){
Order order=(Order)obj;

orderId.add(order.getOrderId());
}
application.setAttribute("orderId",orderId);%>
<%
for(Object obj:orderId)
{int i=0;
%>



<%for(Object order:orders){
	
	Order order1=(Order)order;
	if(order1.getOrderId()==(Integer)obj&&i==0){%>
	<tr>	
		<td align="center"><%=order1.getOrderId()%></td>
		
		<td align="center"><%=order1.getTime()%></td>
		<td align="center"><%=order1.getPlacedByName()%></td>
		<td align="center"><input type="submit" value="VIEW ORDER"  name="<%=order1.getOrderId()%>"/></td>
	</tr>
	
	<% i++;
	}else{}}%>
 <%}%>
</table>
</form>
</fieldset>
<%}%>
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