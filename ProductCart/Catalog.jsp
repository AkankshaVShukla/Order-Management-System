<%@page import="com.quinnox.ordermanagementsystem.daomodel.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.quinnox.ordermanagementsystem.daomodel.Item"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Catalog</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>


<%Boolean log=(Boolean)session.getAttribute("Logged");
if(log!=null&&log==true){ %>
<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">
<fieldset>
<table width="100%" height="100%">
	<tr>
		<td width="100%" height="20%" bgcolor="#73B05E">&nbsp;</td>
	</tr>

	<tr>
		<td width="100%" height="20%" bgcolor="white">
		<form name="LoginForm" method="POST" action="GetItems">
		<table align="center">
			<tr>
				<th align="CENTER"><h3 style="color:black;" align="center">select supplier</h3></th>
			</tr>

			<tr>
				<td align="CENTER"><select name="supplier">
					<%
							ArrayList supplier=(ArrayList)request.getAttribute("supplier");
						  	Iterator it=supplier.iterator();
						  	User user=null;
						  	while(it.hasNext())
						 	{
						 		user=(User)it.next();
						 			%>

					<option value="<%=user.getUid()%>"><%=user.getOrganisation()%></option>

					<% } %>
				</select></td>


				<td colspan="4" align="center"><input type="submit"
					name="submit" value="VIEW ITEMS" /></td>
			</tr>
		</table>
		</form>
		</td>
	</tr>

	<tr>
		<td width="100%" height="20%" bgcolor="#73B05E">&nbsp;</td>
	</tr>
	
</table>
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