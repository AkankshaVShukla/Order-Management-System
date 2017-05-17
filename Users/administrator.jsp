<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
<script language="JavaScript">
  javascript:window.history.forward(1);
</script>
<script type="text/javascript">
function userAction(action_name)
{
    if( action_name=="ADD USER" ) 
    {
    	document.admin.action="PopulateManager";
		document.admin.method="POST"; 
		document.admin.submit(); 
    	 return true;
    }
    
    if( action_name=="VIEW USER" ) 
    {
    	document.admin.action="ViewUser";
		document.admin.method="POST"; 
		document.admin.submit(); 
    	 return true;
    }
    
    if( action_name=="ADD PRODUCTS" ) 
    {
    	document.admin.action="PopulateSupplier";
		document.admin.method="POST"; 
		document.admin.submit(); 
    	 return true;
    }
    
    if( action_name=="DELETE USER" )
    {
        document.admin.action="AdminManagement/DeleteUsers1.jsp";
        document.admin.method="POST";
        document.admin.submit();
         return true;
    }



    return false;
}
 

</script>
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
<div id="outer"><%@include file="/loggerDetails.jsp"%>
<div id="wrapper">
<div id="mainbody">
<div id="page">

<fieldset><legend></legend>
<form action="" method="" name="admin">
<div id="left" align="center"><input type="submit"
	value="VIEW USER" onclick="return userAction(this.value)" /> <input
	type="submit" value="ADD USER" onclick="return userAction(this.value)" />

<input type="submit" value="DELETE USER"
	onclick="return userAction(this.value)" /> <input type="submit"
	value="ADD PRODUCTS" onclick="return userAction(this.value)" /></div>
</form>
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