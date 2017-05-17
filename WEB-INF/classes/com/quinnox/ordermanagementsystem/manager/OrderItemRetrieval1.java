package com.quinnox.ordermanagementsystem.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.OrderItemsDAO;
import com.quinnox.ordermanagementsystem.daomodel.OrderItems;

/**
 * Servlet implementation class OrderItemsRetrieval
 */
public class OrderItemRetrieval1 extends HttpServlet 
{
	int orderId=0;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderItemRetrieval1() {
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
			HashMap itemsUnderOrder=new HashMap();

			try
			{
				ServletContext context=getServletContext();
				OrderItemsDAO orderItemsDao=new OrderItemsDAO();
				PrintWriter out=response.getWriter();


				String orderid="";
				ArrayList OrderIdList=orderItemsDao.getOrderId();

				for(Object obj:OrderIdList)
				{	orderid=request.getParameter(""+obj);
				if(orderid!=null)
				{
					orderId=(Integer)obj;
					break;
				}
				}

				itemsUnderOrder=(HashMap)orderItemsDao.getOrderItems(orderId);
				request.setAttribute("OrderId",orderId);
				session.setAttribute("OrderId", orderId);

			}	
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} 
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			catch (NullPointerException e3)
			{			
				e3.printStackTrace();
			} 
			catch (NumberFormatException e4)
			{			
				e4.printStackTrace();
			} 
			try
			{

				request.setAttribute("itemsUnderOrder", itemsUnderOrder);

				//System.out.println(orderId);
				rd=request.getRequestDispatcher("/Manager/ItemsApproveManager.jsp");
				rd.forward(request,response);

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
