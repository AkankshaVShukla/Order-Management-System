package com.quinnox.ordermanagementsystem.Users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class SuppliersServlet
 */
public class SuppliersServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       OrderDAO orderDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppliersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession(false);
		Boolean logged=(Boolean) session.getAttribute("Logged");
		User user=(User)session.getAttribute("user");
		if(logged==null||logged==false)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
		String work=(String)request.getAttribute("work");
			if(work.equals("orderStatus"))
			{
				OrderDAO order=new OrderDAO();
				//ArrayList orders=orderDao.getOrders(user.getUid());
				
			}
		if(work.equalsIgnoreCase("approve"))
			{	
				OrderDAO orderDao= new OrderDAO();
				ArrayList orders=orderDao.ordersToApproveSuppliers(user.getUid());
				//System.out.println(user.getUid());
				for (Object object : orders) {
					//System.out.println(((Order)object).getOrderId());
				}
				request.setAttribute("orders",orders);
				request.getRequestDispatcher("OrdersLog/ApproveOrderSupplier.jsp").forward(request, response);
			}
		}
		
	}

}
