package com.quinnox.ordermanagementsystem.Users;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;

/**
 * Servlet implementation class SupplierOrderManagementServlet
 */
public class SupplierOrderManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupplierOrderManagementServlet() {
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
			OrderDAO dao=new OrderDAO();
			ServletContext context=getServletContext();
			int orderid=0;
			String orderID=null;	
			HashSet orderId=(HashSet) context.getAttribute("orderId");
			for (Object object : orderId) {
				System.out.println(object);
				orderID=(String)request.getParameter(""+object);

				System.out.println(orderID);
			}
			if(orderID!=null)
			{	int order=Integer.parseInt(orderID);
			request.setAttribute("orderId",order);
			request.getRequestDispatcher("OrdersLog/ApproveReject.jsp").forward(request, response);
			}
		}
	}

}
