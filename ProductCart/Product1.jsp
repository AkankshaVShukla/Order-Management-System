<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.ItemSupplierPrice"%>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.User"%>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.Item"%>
<%@page
	import="com.quinnox.ordermanagementsystem.productcart.FetchSuppliersServlet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Items</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />

<script type="text/javascript">

function showSelectedItem(iName) 
{	
	//alert(iName);
    var itemName = document.getElementById("itemName");
	var itemIdValue = itemName.options[itemName.selectedIndex].value;
	if(itemIdValue!=0)
	{
		
		document.selectProduct.action="\GetItems1?dimeti="+itemIdValue;
		document.selectProduct.method="POST"; 
		document.selectProduct.submit(); 
		
	}
	else
	{
		document.selectProduct.action="\GetItems1?dimeti="+0;
		document.selectProduct.method="POST"; 
		document.selectProduct.submit(); 
		
	}
	

	//document.selectProduct.action="\AddItemsServlet"; 
	//document.selectProduct.method="POST"; 
	//document.selectProduct.submit(); 
   
}
function showSelectedSupplierDetail(sId)
{
	var sName = document.getElementById("supplierList");
	var sIdValue = sName.options[sName.selectedIndex].value;

	  var itemName = document.getElementById("itemName");
	  var itemIdValue = itemName.options[itemName.selectedIndex].value;
	
		if(sIdValue!=0)
		{			
			document.selectProduct.action="\GetItems1?dis="+sIdValue+"&itemIdValue="+itemIdValue;
			document.selectProduct.method="POST"; 
			document.selectProduct.submit(); 
			
		}
		else
		{
			document.selectProduct.action="\GetItems1?dis="+0;
			document.selectProduct.method="POST"; 
			document.selectProduct.submit(); 
			
		}
}



	function showCart() 
	{
		document.selectProduct.action = "\TempSessionCart";
		document.selectProduct.method = "POST";
		document.selectProduct.submit();

	}
	function viewCart()
	{
		document.selectProduct.action="\AddItemsServlet";
		document.selectProduct.method="POST";
		document.selectProduct.submit();
	}
</script>

</head>
<body>
<%Boolean logger=(Boolean)session.getAttribute("Logged");
if(logger==null||logger==false){%>
<%@ include file="/index.jsp"%>
<%}else{%>

<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">
<%
	int itemIdValue = 0;
	String itemName1 = "";
	if (session.getAttribute("itemIdValue") != null) {
		itemIdValue = (Integer) session.getAttribute("itemIdValue");
		itemName1 = (String) session.getAttribute("itemName");
	}

	int suppId = 0;
	String userOrg = "";
	if (session.getAttribute("suppId") != null) {
		suppId = (Integer) session.getAttribute("suppId");
		userOrg = (String) session.getAttribute("userOrg");
	}
%>
<table width="100%" height="100%">

	<tr>
		<td width="100%" height="20%" bgcolor="#73B05E">&nbsp;</td>
	</tr>

	<tr>
		<td width="100%" height="20%" >
		<fieldset><legend align="center">
		<h3 style="color:#73B05E;">SELECT PRODUCTS</h3>
		</legend>
		<form name="selectProduct" method="" action="">
		<table align="center">
		<col width="200">
		<col width="200">
		<col width="200">
		<col width="200">	
		
		
			<tr>
				<th align="CENTER">SELECT ITEM</th>
				<th align="CENTER">SELECT SUPPLIER</th>
				<th align="CENTER">STOCK</th>
				<th align="CENTER">PRICE PER UNIT</th>
				<th align="CENTER">&nbsp;</th>
			</tr>
			

			<tr>
				
				
				
				<!-- *****************************ITEM NAME***************************** -->
								<%
									Double price = 0.0;
									int stock = 0;
									if (request.getAttribute("items") != null) {
										ArrayList list = (ArrayList) request.getAttribute("items");
										Iterator it = list.iterator();
										Item item = null;
										int itemId;
										String itemName;
								%>
								<td align="left"><select name="itemName" id="itemName"
									onchange="showSelectedItem(this.value)">
										<option value=0 selected>Select Item</option>
										<%
											if (itemIdValue != 0) {
										%>
												<option value=<%=itemIdValue%> selected><%=itemName1%></option>
										<%
											}
										%>		
										<%
													while (it.hasNext()) {
															item = (Item) it.next();
															itemId = item.getId();
															itemName = item.getName();
												%>
										<option value=<%=itemId%>><%=itemName%></option>
										<%
											}
											}
										%>
								</select>
								</td>

							<!-- ********************************SUPPLIER LIST ******************************** -->
			
							<td align="left"><select name="supplierList" id="supplierList"
								width="300" style="width: 300px;"
								onchange="showSelectedSupplierDetail(this.value)">
			
								<option value=0>Select Supplier</option>
								<%
									if (suppId != 0) {
								%>
												<option value=<%=suppId%> selected><%=userOrg%></option>
										<%
											}
										%>	
								<%
										if (request.getAttribute("supplierList") != null) {
											ArrayList supplierList = (ArrayList) request
													.getAttribute("supplierList");
											Iterator iterator = supplierList.iterator();
											User user = null;
											int uid;
											String userOrg1;
									%>
								<%
									while (iterator.hasNext()) {
											user = (User) iterator.next();
											uid = user.getUid();
											userOrg1 = user.getOrganisation();
								%>
								<option value=<%=uid%>><%=userOrg1%></option>
								<%
									}
									}
								%>
			
							</select></td>
			
							<!-- ********************************** STOCK & PRICE ************************************** -->


				<%
					if (request.getAttribute("itemSuppList") != null) {
						ArrayList itemSuppList = (ArrayList) request
								.getAttribute("itemSuppList");
						Iterator iterator = itemSuppList.iterator();
						ItemSupplierPrice itemSupplierPrice = null;
				%>
				<%
					while (iterator.hasNext()) {
							itemSupplierPrice = (ItemSupplierPrice) iterator.next();
							price = itemSupplierPrice.getPrice();
							stock = itemSupplierPrice.getStock();

						}
					}
				%>
				<td align="center"><input type="text" name="stockInp" value=<%=stock%> readonly></td>
										<td align="center"><input type="text" name="priceInp" value=<%=price%> readonly></td>
									
							<td colspan="4" align="center"><input type="button" name="ADD TO CART" value="ADD TO CART" onclick="showCart()"/></td>
							
							<!-- ********************************************************************* -->
								
			</tr>

		</table>
		</form>
		</fieldset>
		</td>
	</tr>

	<tr>
		<td width="100%" height="20%" bgcolor="#73B05E">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" height="5%" bgcolor="#73B05E">&nbsp;</td>
	</tr>

</table>
<div>
<%-- <%

ArrayList<Item> itemSupplierList= new ArrayList<Item>() ;



if((session.getAttribute("itemSupplierList") != null))
{
			
	itemSupplierList= (ArrayList<Item>)session.getAttribute("itemSupplierList");
			
}
%>
 --%><fieldset>
<legend align="center"><h3>SELETED PRODUCTS IN CART</h3></legend>
<table width="100%" height="100%">
<col width="200">
		<col width="200">
		<col width="200">
		<col width="200">	
	<tr>
		<th>ITEM NAME</th>
		<th>SUPPLIER NAME</th>
		<th>STOCK</th>
		<th>PRICE PER UNIT </th>		
	</tr>
	
		<%

ArrayList<Item> itemSupplierList= new ArrayList<Item>() ;



if((session.getAttribute("itemSupplierList") != null))
{
			
	itemSupplierList= (ArrayList<Item>)session.getAttribute("itemSupplierList");
			
}
%>
	<%
	for (int index = 0; index < itemSupplierList.size(); index++)
	{
		Item itemObj=(Item)itemSupplierList.get(index);
				
		%>
		<tr>
		<td align="center"><%=itemObj.getName() %></td>
		<td align="center"><%=itemObj.getSupplierOrg() %></td>
		<td align="center"><%=itemObj.getSuppStock() %></td>
		<td align="center"><%=itemObj.getPrice()%></td>
	
	</tr>
	<%
	}	
	%>

	
</table>
			

<% if(itemSupplierList.isEmpty()==false){ %>
			<!--<tr>
				--><td><!-- <a href="FetchItemsServlet">Select Supplier</a> --></td>
				<p align="center"><input type="submit" name="cart" name="VIEW CART" value="VIEW CART"  onclick="viewCart()"/></p>
			<!--</tr>
			--><%} %>
</fieldset>
</div>


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