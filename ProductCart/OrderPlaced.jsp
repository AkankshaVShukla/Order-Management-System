<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order placed</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />

</head>
<body>
<%Boolean log=(Boolean)session.getAttribute("Logged") ;
if(log!=null&&log==true){%>
<%int orderId=(Integer)request.getAttribute("orderId");
%>

<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">
<%@ page
	import="java.util.Collection,com.quinnox.ordermanagementsystem.daomodel.CartItem,com.quinnox.ordermanagementsystem.daoclasses.Cart"%>
<% Cart cart=(Cart)request.getAttribute("cart");
			Collection cartItems=cart.getCartItems();
			Iterator it=cartItems.iterator();
		%>
<table width="100%" height="100%">
	<tr>
		<td width="100%" height="20%" bgcolor="#73B05E">&nbsp;</td>
	</tr>

	<tr>
		<td width="100%" height="60%" bgcolor="white">
		`<H4 align="center">YOUR ORDER HAS BEEN PLACED SUCCESSFULLY</H4>
		<table border="1" cellpadding="10" cellspacing="0" align="center">
			<caption style="font-size: medium;color: red;">ORDER ID:<%=orderId %></caption>
			<tr>
				<th>Item Name</th>
				<th>Supplier</th>
				<th>Quantity</th>
				<th>Rate</th>
				<th>Quote Price</th>			
			</tr>

			<%
								CartItem cartItem=null;
								while(it.hasNext())
								{
									cartItem=(CartItem)it.next();
							%>
			<tr>
				<td><%=cartItem.getItem().getName() %></td>
				<td><%=cartItem.getItem().getSupplierOrg()%></td>
				<td align="center"><%=cartItem.getQuantity() %></td>
				<td>INR <%=cartItem.getItem().getPrice() %></td>
				<td><%=cartItem.getItem().getQuotedPrice() %></td>
				</tr>
			<% }%>

			<tr>
				<td></td>
				<td colspan="2" align="right">Total Amount</td>

				<td>INR <%=cart.getCartValue() %></td>
				<td>INR <%=cart.getCartQvalue()%></td>
				</tr>
			<tr>
			</tr>
		</table>
		</td>
	</tr>
<tr>
	<td align="center"><form action="GetItems1" method="post">

				<input type="submit" value="PURCHASE MORE"  name="shopping" /> 
				<!--<input type="submit" value="VIEW ORDER STATUS" name="history" /> 
			--></form>
</td></tr>
	<tr>
		<td width="100%" height="20%" bgcolor="#73B05E">&nbsp;</td>
	</tr>


</table>




<%}else{
	request.getRequestDispatcher("/index.jsp").forward(request,response);
}%>
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