package com.quinnox.ordermanagementsystem.adminmanager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.connectionmanager.ConnectionDetail;
import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;
import com.quinnox.ordermanagementsystem.daomodel.User;


/**
 * Servlet implementation class LoginProcessor
 */
public class LoginProcessor extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginProcessor() 
    {
        super();
       
    }
    
    public boolean isLoginValid(String username,String password) throws ClassNotFoundException,SQLException
	{
    	ConnectionDetail connectionDetail=new ConnectionDetail();
    	Connection con=connectionDetail.getConnection();
    	Statement ps=null;
    	String username1=null;
    	String password1=null;

    	//String SQL="Select * from LOGIN where username='"+username+"' and password='"+password+"'";
    	String SQL="select * from login l inner join users u on l.uid=u.uid where l.username='"+username+"' and l.password='"+password+"' and u.userstatus='ACTIVE' ";

    	
    	//System.out.println("function");
		try
		{
		    ps=con.createStatement();
		    ResultSet rs=ps.executeQuery(SQL);
		    if(rs.next())
		    {
			    username1=rs.getString("username");
			    password1=rs.getString("password");
			    
			  //  System.out.println(username1+"..."+password1+",,form :"+username+"..."+password);
			    
			   if((username1.trim().equalsIgnoreCase(username)) && (password1.trim().equalsIgnoreCase(password)))
			    {
					return true;
				}
		    }			
			
			
			ps.close();
			
		} 
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		}
		finally
		{
			connectionDetail.closeConnection();
		}
		
		
		return false;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(true);
		out.println("hiii");

		String username= request.getParameter("username");
		String password = request.getParameter("password");
	
		
		// validation
		if((username==null|| username=="" || username==" ")&&(password==null||password=="" || password==" "))
		{
			request.setAttribute("missing", "PLEASE ENTER USERNAME AND PASSWORD");
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		else if((username==null|| username=="" || username==" "))
		{
			request.setAttribute("missing", "PLEASE ENTER USERNAME");
			
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		else if((password==null||password=="" || password==" "))
		{
			request.setAttribute("missing","PLEASE ENTER PASSWORD");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else {	
				boolean valid=false;
			try 
			{
				valid = isLoginValid(username,password);
				out.print(valid+".....");
				if(valid)
				{
					session.setAttribute("Logged", true);
					UserDAO dao=new UserDAO();
					User user=dao.getUser(username);						
					session.setAttribute("user",user);
				//	System.out.println(user.getRole());
					
					if(user.getRole().equalsIgnoreCase("Administrator"))
			    	{
			    	request.getRequestDispatcher("Users/administrator.jsp").forward(request,response);
			    	}
			    	if(user.getRole().equalsIgnoreCase("Purchase Employee"))
			    	{
			    	request.getRequestDispatcher("Users/Employee.jsp").forward(request,response);
			    	}
			    	if(user.getRole().equalsIgnoreCase("Purchase Manager"))
			    	{
			    		request.getRequestDispatcher("Users/Manager.jsp").forward(request,response);
			    	}
			    	if(user.getRole().equalsIgnoreCase("Supplier"))
			    	{
			    		request.getRequestDispatcher("Users/Supplier.jsp").forward(request,response);
			    	}
			    	if(user.getRole().equalsIgnoreCase("Quality Control Engineer"))
			    	{
			    		request.getRequestDispatcher("Users/Quality.jsp").forward(request,response);
			    	}
					//redirect user depending on role...
					
					/*RequestDispatcher rd=request.getRequestDispatcher("/Servlet2");
					rd.forward(request, response);*/
				}
				else{
					request.setAttribute("missing","INVALID CREDENTIALS");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
					}
			}
			catch (ClassNotFoundException e) 
			{

				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
						
		}
	
	
	}
	

}
