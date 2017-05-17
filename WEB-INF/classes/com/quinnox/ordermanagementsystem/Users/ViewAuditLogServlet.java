package com.quinnox.ordermanagementsystem.Users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;
import com.sun.jmx.remote.util.OrderClassLoaders;

/**
 * Servlet implementation class ViewAuditLogServlet
 */
public class ViewAuditLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAuditLogServlet() {
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
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		Boolean logg=(Boolean) session.getAttribute("Logged");
		//logg=false;
		int orderIdAudit=0,orderIdorder=0;
		int orderid=0;
		if(logg==null||logg==false)
		{
			System.out.println("gufi");
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
		else
		{
			ArrayList orderId=(ArrayList) session.getAttribute("orderIds");
			if(orderId!=null&&orderId.isEmpty())
			{
				
			}
			else{
				for (Object object : orderId) {
					System.out.println(object);
					
				}
				String orderIdValueAuditLog="";
				String orderIdValueOrder="";
			for (Object object2:orderId) 
			{
				
			System.out.println(object2+"cuminiafo");
					//int i=(Integer)object2;
			orderIdValueAuditLog=request.getParameter("a"+(Integer)object2);
			orderIdValueOrder=request.getParameter("o"+(Integer)object2);
			if(orderIdValueAuditLog!=null||orderIdValueOrder!=null)
			{
				orderid=(Integer)object2;
				break;
			}	//orderIdorder=(Integer)object2;
			}
			OrderDAO dao=new OrderDAO();
			ArrayList orderAudit,orderDetail;
			if(orderIdValueAuditLog!=null)
			{
				if(orderIdValueAuditLog.equalsIgnoreCase("VIEW AUDITLOG"))
				{
					orderIdAudit=orderid;
					orderAudit=dao.getAuditLogOrder(orderIdAudit);
					request.setAttribute("orders",orderAudit);
					request.getRequestDispatcher("/OrdersLog/AuditLog.jsp").forward(request, response);
			
				
				}
			}
			
			 if(orderIdValueOrder!=null)
				{
				 if(orderIdValueOrder.equalsIgnoreCase("VIEW ORDER"))
					{
					orderIdorder=orderid;
					orderDetail=dao.getOrderDetail(orderIdorder);
					request.setAttribute("orders",orderDetail);
					request.getRequestDispatcher("/OrdersLog/OrderDetail.jsp").forward(request, response);
				
					}
				}
			 /*
			OrderDAO dao=new OrderDAO();
			ArrayList orderAudit,orderDetail;
			if(orderIdAudit>0)
			{
				orderAudit=dao.getAuditLogOrder(orderIdAudit);
				request.setAttribute("orders",orderAudit);
				request.getRequestDispatcher("/OrdersLog/AuditLog.jsp").forward(request, response);
		
			}
			if(orderIdorder>0)
			{
				orderDetail=dao.getOrderDetail(orderIdorder);
				request.setAttribute("orders",orderDetail);
				request.getRequestDispatcher("/OrdersLog/OrderDetail.jsp").forward(request, response);
			}*/
			ArrayList auditLogs;
		String auditLog=(String)request.getAttribute("auditlog");
		if(auditLog!=null)
		{	if(auditLog.equalsIgnoreCase("Supplier"))
				{
				auditLogs=dao.getAuditQualitySupplier(auditLog);
				}
		//	request.setAttribute("a", arg1)
		}
		}
		}
	}
}
