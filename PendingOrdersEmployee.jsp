<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
		System.out.println("logged null");
%>
<jsp:forward page="/index.jsp"></jsp:forward>

<%
	} else {
%>

<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">

<%
ArrayList employeePendingOrders=new ArrayList();
int oId=0;
employeePendingOrders=(ArrayList)request.getAttribute("employeePendingOrders");
%>
<table>
<tr>
<th>ORDER ID</th>
</tr>
<%
for(Object object:employeePendingOrders)
{	oId=(Integer)object;
%>
<tr>

<td>
<%=oId%>
</td>
</tr>
<%} %>

</table>
<%
	}
%> 

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