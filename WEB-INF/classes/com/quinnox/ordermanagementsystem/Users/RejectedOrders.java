package com.quinnox.ordermanagementsystem.Users;

import java.io.IOException;

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
public class RejectedOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RejectedOrders() {
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
			Order order;
			OrderDAO orderDAO;

			User user=(User) session.getAttribute("user");

			String rejectOrder=request.getParameter("Reject");
			String accept=request.getParameter("AcceptSend");

			if(rejectOrder!=null)
			{
				int reject=0;
				String comment;
				OrderItemsDAO orderItemsDao=new OrderItemsDAO();


				comment=request.getParameter("comment");
				RequestDispatcher rd=null;
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
						order=new Order();
						int orderId=(Integer) getServletContext().getAttribute("orderId");
						int uId=user.getUid();
						order.setUserId(uId);
						order.setOrderId(orderId);
						order.setComments(comment);
						order.setStatus("REJECTED");
						order.setRole(user.getRole());
						//System.out.println(order.getOrderId());
						reject=orderDAO.updateOrder(order);
						System.out.println(reject);
						if(reject>0)
						{
							request.setAttribute("orderId",orderId);
							request.setAttribute("Status",order.getStatus());
							request.getRequestDispatcher("/OrdersLog/ApprovedRejected.jsp").forward(request,response);
							//System.out.println("Order Rejected of id :"+orderId);
						}
						else if(reject==0)
						{
							request.setAttribute("orderId",orderId);
							request.setAttribute("Status","APPROVED");
							request.getRequestDispatcher("/OrdersLog/ApprovedRejected.jsp").forward(request,response);
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
			int orderId=(Integer) getServletContext().getAttribute("orderId");
			order.setStatus("APPROVED");
			order.setRole(user.getRole());
			order.setOrderId(orderId);
			order.setRejectById(user.getUid());
			orderDAO=new OrderDAO();
			accepted=orderDAO.updateOrder(order);
			System.out.println(accepted);

			//request.setAttribute("accepted",accepted);
			request.setAttribute("orderId",accepted);

			if(accepted>0)
			{
				request.setAttribute("Status",order.getStatus());
			}
			else
			{
				request.setAttribute("Status","ITEMS APPROVED");
			}
			request.getRequestDispatcher("/OrdersLog/ApprovedRejected.jsp").forward(request,response);

			}
		}
	}
}


