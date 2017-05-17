<%@page import="java.util.HashSet"%>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders to approve</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript">
 function valid()
{
	var rej=document.RejectOrder.comment.value;
	if(rej==null || rej=="")
	{
	alert("Enter Comments");
	return false;
		}
	return true;
}
 
</script>
<%Boolean logger=(Boolean)session.getAttribute("Logged");
if(logger==null||logger==false){%>
<%@ include file="/index.jsp"%>
<%}else{%>
<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">
<%ArrayList orders=(ArrayList)request.getAttribute("order");
application.setAttribute("orders",orders);
int orderIdClicked=(Integer)request.getAttribute("orderId");
application.setAttribute("orderId",orderIdClicked);
%>
<fieldset><legend>Order Details</legend>
<table align="CENTER" border="1">
<tr>
<th>ORDER ID</th>
<th>ORDER PLACED BY</th>
<th>TIME</th>
<th>ITEM NAME</th>
<th>QUANTITY</th>
<th>QUOTED PRICE</th>
<th>SUPPLIER PRICE</th>

</tr>

<%int i=0; 
for(Object obj:orders){
			
	Order order1=(Order)obj;
	//	System.out.print(order1.getItemId());
		if(i==0)
		{%>
		
		<tr>
		<td align="center" bordercolor="WHITE"><%=order1.getOrderId()%></td>
		<td align="center" bordercolor="white"><%=order1.getPlacedByName()%></td>
		<td align="center"><%=order1.getTime()%></td>
		<td align="center"><%=order1.getItemName()%></td>
		<td align="center"><%=order1.getQuantity()%></td>
		<td align="center"><%=order1.getQuotedPrice()%></td>
		<td align="center"><%=order1.getSupplierPrice() %></td>
	
	</tr>
	<%i=1;}else{ %>
		<tr>
		<td bordercolor="WHITE"></td>
		<td bordercolor="WHITE"></td>
		<td bordercolor="WHITE"></td>
		<td align="center"><%=order1.getItemName()%></td>
		<td align="center"><%=order1.getQuantity()%></td>
		<td align="center"><%=order1.getQuotedPrice()%></td>
		<td align="center"><%=order1.getSupplierPrice() %></td>
		</tr>
	

	 <%}}%>
	 	</table>
 

<form action="RejectedOrders" name="RejectOrder" method="post">
<input align="left" type="submit" name="AcceptSend" value="Accept and Send "/>
<input align="right" type="submit" name="Reject" value="Reject the Order" onclick="return valid()" />
<input type="text" name="comment"/>
</form>

</fieldset>
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