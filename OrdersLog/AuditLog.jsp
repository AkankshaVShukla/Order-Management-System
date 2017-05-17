<%@page import="com.quinnox.ordermanagementsystem.daomodel.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Audit Log</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
function redirect()
{
document.forms[frm].submit;
}

</script>
</head>
<body><%Boolean logg=(Boolean)session.getAttribute("Logged");
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
				<legend ALIGN="CENTER">AUDIT LOG</legend>
				<table align="center" border="1">
				<tr>
				<th>ORDER ID</th><th>PLACED BY USER</th><th>STATUS</th><th>TIME</th><th>APPR/REJECT ROLE</th><th>COMMENTS</th>
				</tr>
				
<% ArrayList orders=(ArrayList)request.getAttribute("orders");
if(orders!=null)
{
	for(Object object:orders)
	{Order order=(Order)object;
	%><tr>
	<td><%=order.getOrderId() %></td>
	<td><%=order.getPlacedByName() %></td>
	<td><%=order.getStatus() %></td>
	<td><%=order.getTime() %></td>		
	<td><%=order.getRole() %></td>			
	<td><%=order.getComments()%></td>			
</tr>
	<%}%>
	
</table>
	</fieldset>
	<%} else{%>
	<h3 align="center">no audit log</h3>
	
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