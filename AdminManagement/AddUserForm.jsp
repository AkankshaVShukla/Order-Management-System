
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

<title>Add User Form</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />



<!-- <script type="text/javascript"
	src='../javascript/jquery-ui/js/jquery-ui-1.10.2.custom.js'></script> -->


 <link rel="stylesheet" href="javascript/jquery-ui/css/smoothness/images/jquery-ui-1.10.0.custom.css" />
 
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
<script src="javascript/js/jquery-1.9.1.js"></script>
<script src="javascript/js/jquery-ui-1.10.2.custom.js"></script>
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
<div id="outer">
	<%@include file="/loggerDetails.jsp"%>
<div id="wrapper">

<div id="mainbody">
<div id="page">

<%
	HashMap<Integer, String> managerNames = ((HashMap<Integer, String>) request.getAttribute("managerNames"));
	Set<Integer> keys = managerNames.keySet();//return set of keys
	Collection<String> values = managerNames.values();
	Iterator<Integer> it = keys.iterator();
	int uid = 0;
	String managerName = null;
%>
<script type="text/javascript">
       $(function() {
               $("#datepicker").datepicker({ dateFormat: "yy/mm/dd",
            	      changeMonth: true,
            	      changeYear: true
            	    }).val();
       });
   </script>
<script type="text/javascript">

function showSelected(role) 
{
	
        if (role=="Supplier") 
            {
        	//alert("hii");
          	    document.getElementById("supplier").style.display = 'block';
          	    document.getElementById("supplier1").style.display = 'block';
            } else {
            	document.getElementById("supplier").style.display = 'none';
            	document.getElementById("supplier1").style.display = 'none';
            }
      
    return false;
}


function validateData()
{
		var flag=1;
	    var userName = document.addUserForm.userName.value;
		var regex = /(\\)|(\/)|(\?)/;
		if(userName==null || userName=="" || regex.test(userName))
		{
			//document.getElementById("unameError").innerHTML="Enter Proper Username ";
			alert("enter username");
			flag=0;
			return false;
		}
		if(userName.length>20)
		{
		
			alert("User Name Can't Exceed 20 Characters");
		}
		
		 var mobile=document.addUserForm.phoneNumber.value;
		 var re = /^[0-9]{10}/;
		 if(mobile==null || mobile=="" )
		 {
			// document.getElementById("phoneError").innerHTML="Enter Proper Username ";
			 alert("enter mobile");
			 flag=0;
			 return false;
		 }
		 else if(!(mobile.length == 10))
		 {
				// document.getElementById("phoneError").innerHTML="Enter Proper Username ";
					 alert("enter 10 digit mobile");
					 flag=0;
					 return false;
		
		 }
		 else if(isNaN(mobile))
		{
			 alert("enter correct digit mobile");
			 flag=0;
			 return false; 
		}
		 else
			{}
		
		
		var email1=document.addUserForm.email.value; 
		var atpos=email1.indexOf("@");
		var dotpos=email1.lastIndexOf("."); 
		 if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email1.length || email1==null || email1=="") 
			 {
			 alert("email wrong");
			 //document.getElementById("emailError").innerHTML="Not a valid e-mail address"; 
			 flag=0;
			return false; 
			}
	
		 
		  if( flag==1 ) 
		    {
			 
		    	document.addUserForm.action="AddUser";
				document.addUserForm.method="POST"; 
				document.addUserForm.submit();  
		    	return true;
		    }
		  else
			{
			  
			   return false;
			}
		 
	
}


function back()
{
	document.addUserForm.action="Users/administrator.jsp";
	document.addUserForm.method="POST"; 
	document.addUserForm.submit(); 
}

</script>
	<form action="" name="addUserForm" method="POST" >
		<table align="center">

			<tr>
				<td>NAME</td>
				<td><input type="text" name="userName" value=""/></td>
				<td><div id="unameError"></div></td>
			</tr>
			<tr>
				<td>ROLE</td>
				<td>
					<select name="role" id="role"  onchange="showSelected(this.value)">
						<option value="Purchase Employee" selected="selected">Purchase Employee</option>
						<option value="Purchase Manager">Purchase Manager</option>
						<option value="Supplier">Supplier</option>
						<option value="Quality Control Engineer">Quality Control Engineer</option>
					</select>
				</td>
				<td><div id="roleError"></div></td>
			</tr>
		
			<tr>
				<td>
				<div id="supplier" style="display: none;">SUPPLIER ORGANIZATION</div>
				</td>
				<td>
				<div id="supplier1" style="display: none;"><input type="text" name="organization" value=""/></div>
				</td>
				<td><div id="supplierError"></div></td>
			</tr>
			<tr>
				<td>MANAGER NAME</td>
				<td>
					<select name="managerName">
					    <option value=0 selected>Select Manager</option>

			<%
				while (it.hasNext()) {
					Integer key = (Integer) it.next();
					uid = key;
					managerName = (String) managerNames.get(key);
			%>
			<option value=<%=uid%>><%=managerName%></option>
						<%
							}
						%>
					</select>
				</td>
				<td><div id="ManagerError"></div></td>
			</tr>
			<tr>
				<td>DATE OF BIRTH</td>
				<td><input type="text" name="dob" id="datepicker" value=""/></td>
				<td><div id="dobError"></div></td>
			</tr>
			<tr>
				<td>MOBILE NUMBER</td>
				<td><input type="text" name="phoneNumber" value=""/></td>
				<td><div id="phoneError"></div></td>
			</tr>	
			<tr>
				<td>EMAIL</td>
				<td><input type="text" name="email" value=""/></td>
				<td><div id="emailError"></div></td>
			</tr>			
			<tr>
				<td><input type="button" name="ADD USER" value="ADD USER" onclick="return validateData()"/></td>
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
	
	  <% } %>
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