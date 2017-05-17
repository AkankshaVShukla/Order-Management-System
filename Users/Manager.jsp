<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.quinnox.ordermanagementsystem.manager.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
<script language="JavaScript">
	javascript: window.history.forward(1);
</script>

</head>
<body>
<%
	Boolean logg = (Boolean) session.getAttribute("Logged");
	if (logg == null || logg == false) {
		//	System.out.println("logged null");
%>

<jsp:forward page="/index.jsp"></jsp:forward>

<%
	} else {
%>
<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">
<div id="mainbody">
<div id="page">
<fieldset ><legend></legend>
<p align="center">
<form action="RedirectingServlet" method="post" name="admin">
<input type="submit" value="SHOP" name="shop" /> <input type="submit"
	value="ORDER STATUS" name="orderStatus" /> <input type="submit"
	value="ORDER FOR APPROVAL" name="orderForApproval" /> <input
	type="submit" value="SHOW ENITRE LOG" name="showLog" /> <input
	type="submit" value="SHOW REJECTED ORDERS" name="showRejected" />
</form>
</p>
</fieldset>
<%
	}
%> <br class="clearfix" />
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