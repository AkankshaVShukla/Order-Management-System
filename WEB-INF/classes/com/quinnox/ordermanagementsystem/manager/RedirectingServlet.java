package com.quinnox.ordermanagementsystem.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RedirectingServlet
 */
public class RedirectingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectingServlet() {
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
		String shop =request.getParameter("shop");
		String orderStatus=request.getParameter("orderStatus");
		String orderForApproval=request.getParameter("orderForApproval");
		String showLog=request.getParameter("showLog");
		String showRejected=request.getParameter("showRejected");
		
		if(shop!=null)
		{
			request.getRequestDispatcher("/GetItems1").forward(request, response);
		}
		if(orderStatus!=null)
		{
			request.getRequestDispatcher("/OrderStatus").forward(request, response);
		}
		if(orderForApproval!=null)
		{
			request.getRequestDispatcher("/ApprovalOrders").forward(request, response);
		}
		if(showLog!=null)
		{
			request.getRequestDispatcher("/AuditLog").forward(request, response);
		}
		if(showRejected!=null)
		{
			request.getRequestDispatcher("/ShowRejected").forward(request, response);
		}
	}
	}

}
