package com.quinnox.ordermanagementsystem.Users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;

/**
 * Servlet implementation class ApprovalOrders
 */
public class ViewOrdersManagerQuality extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewOrdersManagerQuality() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		Boolean logged=false;
		logged=(Boolean)session.getAttribute("Logged");
		
		if(logged==null||logged==false)
		{
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
		else
		{
			String pendingOrders=(String)request.getParameter("pendingOrders");
			String auditLog=(String)request.getParameter("auditlog");
			if(pendingOrders!=null)
			{
				/*RequestDispatcher rd=null;

		UserDAO dao=new UserDAO();
		HashMap userDetails = new HashMap();		
		try
		{
		//User user=(User) session.getAttribute("user");
		int uId=user.getUid();
		userDetails=dao.getUsersUnderManagers(uId);

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
			request.setAttribute("UserDetails",userDetails);
			rd=request.getRequestDispatcher("/OrdersLog/OrdersToApprove.jsp");
			rd.forward(request, response);
		}
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		}*/ 
				OrderDAO dao=new OrderDAO();
				ArrayList list;

				list=dao.getOrderQuality();
				//System.out.println(list.isEmpty());
				/*for(Object obj:list)
			{
				System.out.println(((Order)obj).getOrderId());
			}*/
				request.setAttribute("orders",list);
				request.getRequestDispatcher("/OrdersLog/OrdersToApprove.jsp").forward(request, response);
			} 
			if(auditLog!=null)
			{String quality="quality";
				OrderDAO orderDAO=new OrderDAO();
				ArrayList orders=orderDAO.getHistoryQuality();
				request.setAttribute("orders",orders);
				//System.out.println(orders.isEmpty());
				request.getRequestDispatcher("/OrdersLog/PlacedOrders.jsp").forward(request,response);

			}

		}

	}
}
