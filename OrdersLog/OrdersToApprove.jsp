<%@page import="com.quinnox.ordermanagementsystem.daomodel.Order"%>
<%@page import="java.util.ArrayList"%>
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
<title>OrderToApprove</title>
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

<%ArrayList orders=(ArrayList)request.getAttribute("orders"); 
application.setAttribute("orders",orders);

%>
        <fieldset style="width: 240pt height : 150pt">
                <div align="right">
           
                       <form method="post"  action="OrderItemsRetrieval">
                                <table align="center" border="1">
                                        <caption>ORDERS PENDING</caption>

                                        <tr>

                                                <th align="CENTER">Order ID</th>
                                                <th align="CENTER">PLACED BY</th>
                                                <th align="center">TIME</th>
                                                <th align="CENTER">VIEW ORDER</th>
                                        </tr>
                                        <%
                                        for(Object obj:orders)
                                        {
                                        	Order order=(Order)obj;
                                        System.out.print(order.getOrderId());	
                                        %>
                                        <tr>
                                        <td><%=order.getOrderId() %></td>
                                        <td><%=order.getPlacedByName() %></td>
                                        <td><%=order.getTime() %></td>
                                        <td align="center"><input type="submit" value="VIEW ORDER"  name="<%=order.getOrderId() %>"/></td>
                                        
                                        </tr>
                                        
                                        <%} %>
                                </table>
                                </form>
                           
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