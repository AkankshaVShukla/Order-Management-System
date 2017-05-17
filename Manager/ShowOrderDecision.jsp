<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.OrderItems"%>
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
</head>
<body>
<%
Boolean logg=(Boolean)session.getAttribute("Logged");
if(logg==null||logg==false)
{
//	System.out.println("logged null");

%>

<jsp:forward page="/index.jsp"></jsp:forward>

<%} else{%>
<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">


<%
		
	
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
	
<%
String decision=(String)session.getAttribute("decision");
int orderId=(Integer)session.getAttribute("OrderId");
if(decision=="reject")
{
	out.println("Order Is Rejected of id :"+orderId);
	
}

if(decision=="accepted")
{
	out.println("order with id: "+orderId+" accepted");
	
}
%>


</table>
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