<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<script type="text/javascript">
function validate()
{
	var username=document.loginform.username.value;
	var password=document.loginform.password.value;
	
	if(username==null || username=="")
	{
		alert("Username cannot be empty.");
		return false;
	}
	 if(username.match(/\ /))
	 {
		   alert("Please Enter Username without Spaces");
		   username.focus();
		   username.value="";
		   return false;
	 }

	 if(password==null || password=="")
		{
			alert("Password cannot be empty.");
			return false;
		}
		 if(password.match(/\ /))
		 {
			   alert("Please Enter password without Spaces");
			   username.focus();
			   username.value="";
			   return false;
		 }
		 if(username.length>15)
		 {
			   alert("Username Cannot exceed 15 characters");
			   username.focus();
			   username.value="";
			   return false;
		 }
		 if(password.length>22)
		 {
			   alert("Password Cannot exceed 22 characters");
			   username.focus();
			   username.value="";
			   return false;
		 }
		 
return true;
}

function disableCntrls(e)
{   
    if(e.keyCode==17 ||e.keyCode==93)
	{ 
    	alert('Enter Correct Password');    
		 return false;  
	}
}
function disContextMenu(e)
{    
	$(e).bind("contextmenu",function(e)
			{return false; }); 
} 
</script>
	<div id="outer">
		
		<div id="header">
				<div id="logo">
					<!--<h1>Niche Car Makers</h1>
				-->
				<img src="images/Europcar-logo.jpg" width="93%" height="100%">
				</div>
				
		 </div>
		<div id="wrapper">	
			<div id="mainbody">
		<div id="page">
			<div align="center">
			
				<fieldset style="width: 250pt; height: 150pt" align="center">
					<legend align="center"><b>LOGIN </b></legend>
					
					<form name="loginform" method="POST" action="LoginProcessor"
						onsubmit="return validate()">
						<table align="center" border="0" bgcolor="white" style="width: 250pt; height: 120pt">
							<tr bgcolor="#73B05E">
								<th>UserName:</th>
								<td colspan="2"><input type="text" id="username"
									name="username" maxlength="10"></td>
							</tr>

							<tr bgcolor="#73B05E">
								<th>Password:</th>
								<td colspan="2"><input type="password" id="password"
									name="password" onkeydown="return disableCntrls(event);"
onmousedown="disContextMenu(this);"></td>
							</tr>
							<tr bgcolor="#73B05E">
								<td colspan="3">
									<center>
										<input type="submit" value="  login  ">
									</center></td>
							</tr>

						</table>
					</form>

				</fieldset>
				<% if(request.getAttribute("missing")!=null){
						String missing=""+request.getAttribute("missing");%>
				<h3 align="center" style="color: red;"><%=missing %></h3>
				<%} %>
			</div>
			
				<br class="clearfix" /><br class="clearfix" /><br class="clearfix" />
			</div>
				<br class="clearfix" />
				
			</div>
		</div>
			
	
			<%@ include file="footer.jsp"%>
		</div>
	
</body>
</html>