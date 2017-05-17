<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accept/reject</title>
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
<%int orderId=(Integer)request.getAttribute("orderId") ;%>
<fieldset><legend align="center">APPROVE/REJECT ORDER</legend>
<H3 align="center">order id: <%=orderId %></H3>
<form action="RejectedOrders" name="RejectOrder" method="post">
<input align="left" type="submit" name="AcceptSend" value="Accept and Send "/>
<input align="right" type="submit" name="Reject" value="Reject the Order" onclick="return valid()" />
<input type="text" name="comment"/>
</form>

	
</fieldset>
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