package com.quinnox.ordermanagementsystem.adminmanager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		PrintWriter out=response.getWriter();
		UserDAO userDao=new UserDAO();
		User user=new User();
		HttpSession session=request.getSession();
		user=(User)session.getAttribute("user");
		//Database Value Verification
		int sessionUId=user.getUid();
		String oldPasswordDatabase;
		if((request.getParameter("oldPassword")==null) && (request.getParameter("newPassword")== null) && (request.getParameter("confirmPassword")==null)) 
		{
			request.setAttribute("errorMsg1", "Enter All details.");
			request.getRequestDispatcher("Users/changePassword.jsp").forward(request, response);
		}
		else
		{
			String oldPassword= request.getParameter("oldPassword");
			String newPassword= request.getParameter("newPassword");
			String confirmPassword= request.getParameter("confirmPassword");
			
			
			int rowsUpdated=0;
			oldPasswordDatabase=userDao.getOldPassword(sessionUId);
			
			if(oldPasswordDatabase.equals(oldPassword))
			{
				if((newPassword.length()>6))
				{
				if(newPassword.equals(confirmPassword))
				{
					rowsUpdated=userDao.updatePassword(sessionUId, newPassword);
					if(rowsUpdated!=0)
					{
						out.println("Password Successfully Updated ");
						request.getRequestDispatcher("Users/changePassword.jsp").include(request, response);
					}
					else
					{
						out.println("Password Update Failed. Try Again");
						request.getRequestDispatcher("Users/changePassword.jsp").include(request, response);
					}
				}
				else
				{
					out.println("Password Size Must be Greater than 6 ");
					request.getRequestDispatcher("Users/changePassword.jsp").include(request, response);
				}
				}
			}
			else
			{
				out.println("Your Passwords Don't Match..Please verify");
				request.getRequestDispatcher("Users/changePassword.jsp").include(request, response);
			}
			
			
			
			
			
		}
			
			
		

	}

}
