package com.quinnox.ordermanagementsystem.adminmanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;

/**
 * Servlet implementation class PopulateManager
 */
public class PopulateManager extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PopulateManager() 
	{
		super();
		// TODO Auto-generated constructor stub
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
			UserDAO userDao = new UserDAO();
			HashMap<Integer, String> managerNames = null;
			try 
			{
				managerNames = userDao.getManagers();
			} 
			catch (ClassNotFoundException e1)
			{
				e1.printStackTrace();
			} 
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}

			try
			{
				request.setAttribute("managerNames", managerNames);

				String message=(String) request.getAttribute("message");
				if(message!=null)
				{
					request.setAttribute("message1", message);
					System.out.println(message+"............");
				}

				RequestDispatcher rd=request.getRequestDispatcher("/AdminManagement/AddUserForm.jsp");
				rd.include(request, response);
			} 
			catch (NullPointerException e)
			{			
				e.printStackTrace();
			} 

		}
	}

}
