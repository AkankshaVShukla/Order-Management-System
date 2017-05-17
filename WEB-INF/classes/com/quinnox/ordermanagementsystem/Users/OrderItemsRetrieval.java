package com.quinnox.ordermanagementsystem.Users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;
import com.quinnox.ordermanagementsystem.daomodel.Order;

/**
 * Servlet implementation class OrderItemsRetrieval
 */
public class OrderItemsRetrieval extends HttpServlet {
	int orderId;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderItemsRetrieval() {
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
		ServletContext context=getServletContext();
		HttpSession session=request.getSession();
		Boolean logged=(Boolean)session.getAttribute("Logged");
		
		if(logged==null||logged==false)
		{
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else
		{	int orderId=0;
			ArrayList orders=(ArrayList)context.getAttribute("orders");
			for (Object object : orders) 
			{
				Order order=(Order)object;
				String orderid=request.getParameter(""+order.getOrderId());
				if(orderid!=null)
				{
					orderId=order.getOrderId();
					break;
				}
			}
			
			
		OrderDAO dao=new OrderDAO();
		ArrayList ordersQuality=dao.getOrderApproveQuality(orderId);
		request.setAttribute("order",ordersQuality);
		request.setAttribute("orderId",orderId);
		request.getRequestDispatcher("OrdersLog/ItemsApproveManagerQuality.jsp").forward(request, response);
		
		}
	}
}
