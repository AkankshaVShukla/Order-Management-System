package com.quinnox.ordermanagementsystem.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;
import com.quinnox.ordermanagementsystem.daomodel.Order;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class SupplierOrderFunctionServlet
 */
public class SupplierOrderFunctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierOrderFunctionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		Boolean logger=(Boolean)session.getAttribute("Logged");
		User user=(User)session.getAttribute("user");
		if(logger==null||logger==false)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			//System.out.println("COMING IN");
			ServletContext context=getServletContext();
			ArrayList orders=(ArrayList) context.getAttribute("orders");
			HashSet orderId=new HashSet();
			OrderDAO orderDAO=new OrderDAO();
			int orderIdclicked=0;
			for(Object obj:orders)
			{
				Order order=(Order)obj;
				orderId.add(order.getOrderId());
				
			}
			String orderIdclick=null;
			for (Object object : orderId)
			{
				orderIdclick=request.getParameter(""+object);
				if(orderIdclick!=null)
				{
					orderIdclicked=(Integer)object;
				}
				
			}
			if(orderIdclicked>0)
			{//System.out.println(orderIdclicked);
				ArrayList order=orderDAO.getOrder(orderIdclicked,user.getUid());
				request.setAttribute("orderId",orderIdclicked);
				request.setAttribute("order",order);
				//request.setAttribute("orders",orders);
				//Order order=new Order();
		/*		for (Object obj:order) {
					System.out.println(((Order)obj).getItemId());
				}
*/				request.getRequestDispatcher("/OrdersLog/ApproveOrderSupplier.jsp").forward(request,response);
			}
			
		}
	}
	}
	


