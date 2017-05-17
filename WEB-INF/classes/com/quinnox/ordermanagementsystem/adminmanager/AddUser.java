package com.quinnox.ordermanagementsystem.adminmanager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() 
	{
		super();
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

		HttpSession session=request.getSession(false);
		Boolean logged=false;
		logged=(Boolean)session.getAttribute("Logged");
		System.out.println(logged);
		if(logged!=null && !logged)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			String userName=null;
			String role=null;
			String organization=null;
			int managerId=0;
			String dob=null;
			String phoneNumber=null;
			String email=null;

			User user=null;


			try
			{			
				userName=request.getParameter("userName");
				role=request.getParameter("role");
				organization=request.getParameter("organization");
				managerId=Integer.parseInt(request.getParameter("managerName"));
				dob=request.getParameter("dob");
				phoneNumber=request.getParameter("phoneNumber");
				email=request.getParameter("email");

				userName.trim();
				role.trim();
				organization.trim();

				if((userName==null || role==null || dob==null || phoneNumber == null || email==null )||
						(userName.equals("") || role.equals("")|| dob.equals("") || phoneNumber.equals("") || email.equals("") ))
				{
					RequestDispatcher rd=request.getRequestDispatcher("PopulateManager");
					rd.forward(request, response);
				}
				else if(userName!=null || role!=null || dob!=null || phoneNumber!= null || email!=null )
				{
					UserDAO userdao =new UserDAO();
					user=new User(userName, organization, role, managerId, dob, phoneNumber, email);
					//HttpSession session = request.getSession();
					ServletContext context =getServletContext();
					int result = 0;
					try 
					{
						System.out.println("add user servlet");
						result = userdao.addUsers(user);
						user=new User();
						//System.out.println(result+"......");
					} 
					catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}

					System.out.println(result+"......");
					if(result!=0)
					{
						//result=0;
						user=null;
						request.setAttribute("message", "User "+userName+" added successfully");
						RequestDispatcher rd=request.getRequestDispatcher("PopulateManager");
						rd.forward(request, response);
					}			
					else
					{

						RequestDispatcher rd=request.getRequestDispatcher("PopulateManager");
						rd.forward(request, response);				
					}

				}

			}	

			catch (NullPointerException e)
			{			
				e.printStackTrace();
			} 

		}




	}

}
