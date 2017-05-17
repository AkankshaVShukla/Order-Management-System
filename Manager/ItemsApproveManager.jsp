<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.OrderItems"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
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
</head>
<body>
<%Boolean logg=(Boolean)session.getAttribute("Logged");
if(logg==null||logg==false)
{%>		
<%@include file="/index.jsp" %>
	<%}
else{%>

<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">

<%
		int orderId=Integer.parseInt(""+request.getAttribute("OrderId"));
		application.setAttribute("orderId",orderId);
		HashMap itemDetails = (HashMap) request.getAttribute("itemsUnderOrder");
		Set keys = itemDetails.keySet();
		Collection values = itemDetails.values();
		Iterator it = keys.iterator();
		int itemId= 0;
		int suppId = 0;
		int quantity=0;
		double price=0.0;
		String itemName=null;
		String message=null;

		%>
<%! 
OrderItems orderItems;
%>

<fieldset style="width: 240pt height :150pt">
<div align="center">

<table align="center" border="1">
	<caption>Order Items</caption>

	<tr>
		<th align="CENTER">ITEM ID</th>
		<th align="CENTER">ITEM NAME</th>
		<th align="CENTER">QUANTITY</th>
		<th align="CENTER">PRICE</th>
		<th align="CENTER">SUPPLIER ID</th>
	</tr>

	<%			

					if(itemDetails!=null)
					{
				
       				 for(Object obj:keys)
        				{
							int key1=(Integer)obj;
                            itemId=key1;
                            OrderItems orderItems=(OrderItems)itemDetails.get(key1);
							quantity=orderItems.getQuantity();
							price=orderItems.getPrice();
							suppId=orderItems.getSuppId();
							itemName=orderItems.getItemName();
							//request.setAttribute("orderId1",request.getAttribute("orderId"));
							//System.out.println(request.getAttribute("orderId"));
						//	System.out.println(itemId+"..."+quantity+"..."+price+"..."+suppId);
        			
					
	%>
	<tr>

		<td><%=itemId%></td>
		<td><%=itemName%></td>
		<td><%=quantity%></td>
		<td><%=price%></td>
		<td><%=suppId%></td>
	</tr>
	
	<%
        				}
					}
	%>
</table>


</div>
<form action="RejectedOrders1" name="RejectOrder" method="post">
<div align="left">
<input align="left" type="submit" name="AcceptSend" value="Accept and Send "/>
</div>
<div align="right">
<input align="right" type="submit" name="Reject" value="Reject the Order" onclick="return valid()" />
<input type="text" name="comment"/>
</div>

</form>
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