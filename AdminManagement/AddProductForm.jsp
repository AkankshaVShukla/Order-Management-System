<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Collection" %>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Products</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
<script src="js/myScript.js"></script>

</head>
<script type="text/javascript">

function validateItem()
{
	var itemName = document.addProduct.itemName.value;
	if(itemName==null || itemName=="")
		{
		
			alert("Enter Item Name");
		}
	if(itemName.length>20)
	{
	
		alert("Item Name Can't Exceed 20 Characters");
	}
	
}
</script>
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
<div id="outer">
<%@include file="/loggerDetails.jsp"%>
<div id="wrapper">
	
	<div id="mainbody">
<div id="page">

<%
	HashMap<Integer, String> supplierNames = ((HashMap<Integer, String>) request
			.getAttribute("supplierNames"));
	Set<Integer> keys = supplierNames.keySet();//return set of keys
	Collection<String> values = supplierNames.values();
	Iterator<Integer> it = keys.iterator();
	int uid = 0;
	String supplierName = null;
%>
<h3>Add Product </h3>
<form name="addProduct" action="AddProducts" method="POST" onsubmit="validateItem()" align="center">
	<table name="t1" >
	<tr>
		<th>ITEM NAME </th>
		<td><input type="text" name="itemName" value=""/></td>
		<td>
		
		</td>
	</tr>
	
	<!-- <tr>
		<th>UPLOAD IMAGE </th>
		<td><input type="file" name="imagePath" size="50" value=""/></td>
		<td></td>
	</tr> -->
	</table>
	<br/><br>
	<fieldset>
	<legend><b>Supplier Details</b> </legend>
	<table>
	<tr>
		<th>SUPPLIRS LIST</th>
		<th>STOCK</th>
		<th>PRICE</th>
	</tr>
		
		<%
			while (it.hasNext()) {
				Integer key = (Integer) it.next();
				uid = key;
				supplierName = (String) supplierNames.get(key);
				
		%>
		
		<tr>
			<td><input type="checkbox" name="<%=uid%>" value="ON"><%=supplierName%></td>
			<td><input type="text" name="stock<%=uid%>" value=""/></td>
			<td><input type="text" name="price<%=uid%>" value=""/></td>
		<tr>
		<%
			}
		%>

			
	</table>
		</fieldset>
	
	<table>
		<tr>
			<td><input type="submit" name="ADD PRODUCT" value="ADD PRODUCT" /></td>
			<td><input type="reset" name="RESET" value="CLEAR" /></td>
	
			<td></td>
	
		</tr>
	</table>
</form>
 <%
    	String message = (String) request.getAttribute("message1");
    	if (message != null) {
    		out.println("<br/><h3>"+message+"</h3>");
    	}
    %>
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