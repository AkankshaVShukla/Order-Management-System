package com.quinnox.ordermanagementsystem.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class ApprovalOrders
 */
public class ApprovalOrders extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApprovalOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
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
			PrintWriter out=response.getWriter();
			RequestDispatcher rd=null;
			UserDAO udao=new UserDAO();

			HashMap userDetails = null;		
			try
			{
				User user=(User) session.getAttribute("user");
				int uId=user.getUid();
				userDetails = new HashMap();
				userDetails=udao.getUsersUnderManagers(uId);

				if(userDetails!=null)
				{

					request.setAttribute("UserDetails",userDetails);
					rd=request.getRequestDispatcher("/Manager/OrdersToApprove.jsp");
					rd.include(request, response);
				}


			}
			catch (ClassNotFoundException e1)
			{
				e1.printStackTrace();
			} 
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			catch (NullPointerException e)
			{			
				e.printStackTrace();
			} 
		}
	}
}


