<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.quinnox.ordermanagementsystem.daomodel.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<script type="text/javascript">
function validate()
{
	var flag=1;
	oldPassword=document.chgPwd.oldPassword.value;
	if(oldPassword==null || oldPassword=="")
	{
		flag=0;
		alert("Old Password can not be null.");
		return false;
	}

	 if( flag==1 ) 
	    {
		 
	    	document.chgPwd.action='../ChangePassword';
			document.chgPwd.method="POST"; 
			document.chgPwd.submit();  
	    	return true;
	    }
	  else
		{
		   return false;
		}
	 

	return true;
}



</script>
<form name="chgPwd" action="" method="post">
 <% User user=(User)session.getAttribute("user");
    if(user!=null){
     %>
<P ALIGN="center">
     <table>
     	<tr><td align="left">User Id:</td><td align="LEFT"><input type="text" name="uidTxt" value= <%=user.getUid() %> readonly/></td></tr>
     	<tr><td align="left">Name: </td><td align="left"><input type="text" name="unameTxt" value= <%=user.getName() %> readonly/></td></tr>
     	<tr><td align="left">Old Password:</td><td align="LEFT"><input type="password" name="oldPassword" value=""/></td></tr> 
     	<tr><td align="left">New Password:</td><td align="LEFT"><input type="password" name="newPassword" value=""/></td></tr> 
     	<tr><td align="left">Confirm Password:</td><td align="LEFT"><input type="password" name="confirmPassword" value=""/></td></tr> 
     	<tr><td ><input type="button" value="OK" onclick="return  validate()"></td>
        <td ><input type="button" value="CANCEL" onclick="window.close()"></td></tr>
     </table>
   </P>
 <% }
    else{
   	 request.getRequestDispatcher("index.jsp").forward(request,response);
    }%>
</form>

</body>
</html>