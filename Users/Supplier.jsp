<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SUPPLIER</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
<script language="JavaScript">
  javascript:window.history.forward(1);
</script>
</head>
<body>
<%Boolean log=(Boolean)session.getAttribute("Logged");
if(log!=null&&log==true){%>

<div id="outer"><%@ include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">


<div align="center">
<fieldset ><legend></legend>

<form action="FunctionServlet" method="post">


<input type="submit" value="VIEW PENDING ORDERS" name="approveOrders"/>
<!--<input type="submit" value="Order Stauts" name="orderStatus"/>-->
<!--<input type="submit" value="AUDIT LOG" name="AuditLogSupplier"/>

--></form>

</fieldset>
</div>

<%}else{ %>
<%@include file="/index.jsp" %>
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