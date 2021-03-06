package com.quinnox.ordermanagementsystem.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class OrderStatus
 */
public class OrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderStatus() {
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
			RequestDispatcher rd=null;

			User user=(User) session.getAttribute("user");
			OrderDAO orderDAO=new OrderDAO();
			int uId=user.getUid();


			ArrayList statusList=new ArrayList();
			statusList=orderDAO.sendOrderStatus(uId);

			try
			{

				request.setAttribute("statusList", statusList);
				rd=request.getRequestDispatcher("/Manager/ViewOrderStatus.jsp");
				rd.include(request,response);

			}

			catch (NullPointerException e5)
			{			
				e5.printStackTrace();
			} 
			catch (NumberFormatException e6)
			{			
				e6.printStackTrace();
			} 

		}


	}

}
