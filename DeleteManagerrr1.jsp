<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>
<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">

<%
    	String message = (String) request.getAttribute("msg");
    	if (message != null) {
    		out.println("<br/><h3>"+message+"</h3>");
    	}
    %>
	
<%
    	String message1 = (String) request.getAttribute("msg1");
    	if (message1 != null) {
    		out.println("<br/><h3>"+message1+"</h3>");
    	}
    %>
	

<form action="AdminManagement/DeleteUsers1.jsp" >
<input type="submit" value="Go Back">
</form>




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