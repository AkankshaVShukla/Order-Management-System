package com.quinnox.ordermanagementsystem.productcart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.ItemDAO;
import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;
import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class EmployeeOrdersHistory
 */
public class EmployeeOrdersHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeOrdersHistory() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		Boolean logged=false;
		
		if(session.getAttribute("Logged")!=null)
			logged=(Boolean) session.getAttribute("Logged");
		//System.out.println(logged);
		if(logged!=null && !logged)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			User user=(User)session.getAttribute("user");
			String history=(String) request.getAttribute("history");
			String log=(String) request.getAttribute("log");
			ItemDAO idao;
			OrderDAO orderDao;
			ArrayList orders;
			UserDAO udao;
		if(history!=null)
		{
			orderDao =new OrderDAO();
			orders=new ArrayList();
			orders=orderDao.getOrders(user.getUid());
			request.setAttribute("orders",orders);
			request.getRequestDispatcher("/OrdersLog/PlacedOrders.jsp").forward(request, response);
		}
		if(log!=null)
		{
		orderDao=new OrderDAO();
		orders =orderDao.getAuditLog();
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/OrdersLog/AuditLog.jsp").forward(request, response);
		
		}
		}
	}

}
