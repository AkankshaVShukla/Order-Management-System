<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>
<script language="JavaScript">
  javascript:window.history.forward(1);
</script>
<%Boolean logger=(Boolean)session.getAttribute("Logged");
if(logger==null||logger==false){%>
<%@ include file="/index.jsp"%>
<%}else{%>

<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">
<%@ page import="java.util.Collection,com.quinnox.ordermanagementsystem.daomodel.CartItem,com.quinnox.ordermanagementsystem.daoclasses.Cart"%>
<% Cart cart=(Cart)session.getAttribute("cart");
			Collection cartItems=cart.getCartItems();
			Iterator it=cartItems.iterator();
		%>
<table width="100%" height="100%">
	<tr>
		<td width="100%" height="20%" bgcolor="#73B05E">&nbsp;</td>
	</tr>

	<tr>
		<td width="100%" height="60%" bgcolor="WHITE">
		<form method="post" action="DecisionServlet" name="Decision">`
		<table border="1" cellpadding="10" cellspacing="0" align="center">
			<caption>Your Cart</caption>
			<tr>
				<th>Item Name</th>
				<TH>Supplier</TH>
				<th>Quantity</th>
				<th>Rate</th>
				<TH>Quote Price</TH>
				<th>Remove</th>
			</tr>

			<%
								CartItem cartItem=null;
								while(it.hasNext())
								{
									cartItem=(CartItem)it.next();
							%>
			<tr>
				<td><%=cartItem.getItem().getName() %></td>
				<td><%=cartItem.getItem().getSupplierOrg() %></td>
				<td align="center"><input type="text"
					name="T<%=cartItem.getCartItemId()%>" size="1"
					value="<%=cartItem.getQuantity() %>"></td>
				<td>INR <%=cartItem.getItem().getPrice() %></td>

				<td><input type="text" name="q<%=cartItem.getCartItemId()%>"
					value="<%=cartItem.getItem().getQuotedPrice() %>"></td>
				<td align="center"><input type="checkbox"
					name="<%=cartItem.getCartItemId()%>" value="ON"></td>

					
			</tr>
			
			<% }%>

			<tr>
				<td></td>
				<td colspan="2" align="right">Total Amount</td>

				<td>INR <%=cart.getCartValue() %></td>
				<td>INR <%=cart.getCartQvalue()%></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4"><input type="submit" value="Update Cart"
					name="update" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="Shop More" name="shop">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="PLACE ORDER" name="order">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		</form>
		</td>
	</tr>

	<tr>
		<td width="100%" height="20%" bgcolor="#73B05E">&nbsp;</td>
	</tr>
	

</table>

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