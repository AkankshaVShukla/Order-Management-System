package com.quinnox.ordermanagementsystem.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;
import com.quinnox.ordermanagementsystem.daoclasses.OrderItemsDAO;
import com.quinnox.ordermanagementsystem.daomodel.Order;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class RejectedOrders
 */
public class RejectedOrders1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RejectedOrders1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order;
		OrderDAO orderDAO;
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		User user=(User) session.getAttribute("user");
		RequestDispatcher rd=null;
		String rejectOrder=request.getParameter("Reject");
		String accept=request.getParameter("AcceptSend");
		int orderId=(Integer) getServletContext().getAttribute("orderId");
		OrderItemsDAO orderItemsDao=new OrderItemsDAO();
		if(rejectOrder!=null)
		{
			int reject;
			String comment;
			
			comment=request.getParameter("comment");
			if(comment==null||comment.equals(""))
			{
				rd=request.getRequestDispatcher("");
				rd.forward(request, response);
			}
			if(comment!=null|| comment!="")
			{

				orderDAO=new OrderDAO();


				try
				{
					
					int uId=user.getUid();
					order=new Order(orderId,uId,comment);
					order.setStatus("REJECTED");
					order.setRole(user.getRole());
					//System.out.println(order.getOrderId());
					reject=orderDAO.updateOrder(order);
					
					

					if(reject!=0)
					{
						try
						{
						HashMap itemsUnderOrder=new HashMap();
						itemsUnderOrder=(HashMap)orderItemsDao.getOrderItems(orderId);
						request.setAttribute("itemsUnderOrder", itemsUnderOrder);
						
						session.setAttribute("decision","reject");
						//System.out.println(orderId);
						rd=request.getRequestDispatcher("/Manager/ShowOrderDecision.jsp");
						rd.forward(request,response);
						}
						catch (SQLException e1)
						{
							e1.printStackTrace();
						}
						catch (NullPointerException e3)
						{			
							e3.printStackTrace();
						} 
						catch (ClassNotFoundException e)
						{
							e.printStackTrace();
						} 
					

					}

					else
					{
						
						out.println("Rejection is failed");
						out.println("<html>");
						out.println("<head><body>");
						out.println("<form action=\"\\Users\\Manager.jsp\" method=\"post\">");
						out.println("<input type=\"submit\" value=\"RETURN TO MAIN MENU\">");
						out.println("</form>");
						out.println("</body>");
						out.println("</html>");
					}
				}
				
				catch (NullPointerException e)
				{			
					e.printStackTrace();
				}
				
			
		}
		}
		if(accept!=null)
		{	int accepted=0;
		order =new Order();
		order.setStatus("APPROVED");
		order.setRole(user.getRole());
		order.setOrderId(orderId);
		orderDAO=new OrderDAO();
		accepted=orderDAO.updateOrder(order);
		if(accepted>0)
		{
			try
			{
			HashMap itemsUnderOrder=new HashMap();
			itemsUnderOrder=(HashMap)orderItemsDao.getOrderItems(orderId);
			request.setAttribute("itemsUnderOrder", itemsUnderOrder);
			session.setAttribute("decision","accepted");
			//System.out.println(orderId);
			rd=request.getRequestDispatcher("/Manager/ShowOrderDecision.jsp");
			rd.forward(request,response);
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			catch (NullPointerException e3)
			{			
				e3.printStackTrace();
			} 
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} 
			

		}
		else
		{
			out.println("Approval is failed");
			out.println("<html>");
			out.println("<head><body>");
			out.println("<form action=\"\\Users\\Manager.jsp\"  method=\"post\">");
			out.println("<input type=\"submit\" value=\"RETURN TO MAIN MENU\">");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}

		}
		
	}
}



