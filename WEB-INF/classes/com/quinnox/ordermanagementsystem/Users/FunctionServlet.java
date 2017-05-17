package com.quinnox.ordermanagementsystem.Users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class FunctionServlet
 */
public class FunctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FunctionServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		Boolean logg=(Boolean) session.getAttribute("Logged");
		if(logg==null||logg==false)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
//common for all users...
			String home=request.getParameter("home");
			User user=(User) session.getAttribute("user");
//employee
			String shopping=request.getParameter("shopping");
			String history=request.getParameter("history");
			String userDetails=request.getParameter("Details");
			String log=request.getParameter("log");
			String logout=request.getParameter("logout");
//suppliers		
			String orderStatus=request.getParameter("orderStatus");
			String approveOrders=request.getParameter("approveOrders");
			String auditLogSupplier=request.getParameter("AuditLogSupplier");
			
			
			if(userDetails!=null)
			{
				request.getRequestDispatcher("Details.jsp").forward(request, response);
			}
			if(logout!=null)
			{
				session.removeAttribute("user");
				session.removeAttribute("Logged");
				session.removeAttribute("itemSupplierList");
				session.invalidate();
				//request.getRequestDispatcher("/index.jsp").forward(request, response);
				response.sendRedirect("index.jsp");
			}

			if(shopping!=null)
			{
				request.getRequestDispatcher("/GetItems1").forward(request, response);
			}
			if(history!=null)
			{	request.setAttribute("history","history");
				request.getRequestDispatcher("/EmployeeOrdersHistory").forward(request,response);
			}
			if(log!=null)
			{	request.setAttribute("log","log");
				request.getRequestDispatcher("/EmployeeOrdersHistory").forward(request, response);	
			}
			if(auditLogSupplier!=null)
			{
				request.setAttribute("auditlog","Supplier");
				request.getRequestDispatcher("/ViewAuditLogServlet").forward(request,response);
			}
			if(approveOrders!=null)
			{	request.setAttribute("work","approve");
			request.getRequestDispatcher("/SupplierViewPendingOrderServlet").forward(request, response);
			}
			if(orderStatus!=null)
			{
				request.setAttribute("work","orders");
				request.getRequestDispatcher("/SupplierViewPendingOrderServlet").forward(request, response);			
			}
			if(home!=null&&user.getRole().equalsIgnoreCase("Administrator"))
			{
				request.getRequestDispatcher("/Users/administrator.jsp").forward(request, response);
			}
			if(home!=null&&user.getRole().equalsIgnoreCase("Purchase Employee"))
			{
				request.getRequestDispatcher("/Users/Employee.jsp").forward(request, response);
			}
			if(home!=null&&user.getRole().equalsIgnoreCase("Purchase Manager"))
			{
				request.getRequestDispatcher("/Users/Manager.jsp").forward(request, response);
			}if(home!=null&&user.getRole().equalsIgnoreCase("Supplier"))
			{
				request.getRequestDispatcher("/Users/Supplier.jsp").forward(request, response);
			}
			if(home!=null&&user.getRole().equalsIgnoreCase("Quality Control Engineer"))
			{
				request.getRequestDispatcher("/Users/Quality.jsp").forward(request, response);
			}


		}
	}

}
