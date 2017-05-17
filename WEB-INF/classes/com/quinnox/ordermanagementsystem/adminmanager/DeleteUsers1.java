package com.quinnox.ordermanagementsystem.adminmanager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class DeleteUsers1
 */
public class DeleteUsers1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUsers1() {
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
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		String submit=request.getParameter("submit");
		String confirmDelete=request.getParameter("confirmdelete");
		String role=null;
		int uIdM=0;
		UserDAO userDao=new UserDAO();
		ArrayList populatedUsers=new ArrayList();
		ArrayList managerEmployees=new ArrayList();
		ArrayList supplierOrders=new ArrayList();
		ArrayList qualityOrders=new ArrayList();
		ArrayList managerNames=new ArrayList();
		
		try
		{
	//	Submit Button
			
		if(submit!=null)
			{
				int uId=Integer.parseInt(request.getParameter("userId"));
				populatedUsers=userDao.populateUserID(uId);
				if(populatedUsers.isEmpty())
				{
					//out.println("This Particular User is Unavailable");
					request.setAttribute("msg1","This Particular User is Unavailable");
					request.getRequestDispatcher("/DeleteManagerrr1.jsp").include(request, response);
				}
				else
				{
				request.setAttribute("populatedUsers",populatedUsers);
				request.getRequestDispatcher("/AdminManagement/DeleteUsers1.jsp").forward(request, response);
				}
			}
		//Confirm Delete button
		if(confirmDelete!=null)
			{
				role=(String)session.getAttribute("role");
				uIdM=(Integer) session.getAttribute("userId");
				//System.out.println(role+"......"+uIdM);
				if(role.equalsIgnoreCase("Purchase Employee"))
				{
					
					ArrayList employeePendingOrders=userDao.employeeOrdersPending(uIdM);
					if(employeePendingOrders.isEmpty())
					{
					int delete=userDao.deleteUserId(uIdM);
					
				
					if(delete!=0)
					{
						//out.println("Record Deleted Successfully");
						request.setAttribute("msg1", "Record Deleted Successfully");
						request.getRequestDispatcher("/DeleteManagerrr1.jsp").include(request, response);
					}
					else
					{
						//out.println("Record Couldn't be Deleted");
						request.setAttribute("msg1", "Record Couldn't be Deleted");
						request.getRequestDispatcher("/DeleteManagerrr1.jsp").include(request, response);
					}
					}
					else
					{
					request.setAttribute("employeePendingOrders",employeePendingOrders);
					out.println("The Employee Has  Orders Pending Against His Name!--- Cannot be Deleted");
					request.getRequestDispatcher("/PendingOrdersEmployee.jsp").include(request, response);
				}}
				
				

				else if(role.equalsIgnoreCase("Purchase Manager"))
				{
					managerEmployees=userDao.getManagerEmployees(uIdM);
					
			
					if(managerEmployees.isEmpty())
					
						{
							int delete=userDao.deleteUserId(uIdM);
						if(delete!=0)
						{
							//out.println("Record Deleted Successfully");
							request.setAttribute("msg1", "Record Deleted Successfully");
							request.getRequestDispatcher("/DeleteManagerrr1.jsp").include(request, response);
							}
						else
						{
							//out.println("Record Couldn't be Deleted");
							request.setAttribute("msg1","Record Couldn't be Deleted");
							request.getRequestDispatcher("/DeleteManagerrr1.jsp").include(request, response);
						}
						}
						
					
					
					else
					{
						System.out.println(managerNames.isEmpty());
						request.setAttribute("managerEmployees",managerEmployees);
						managerNames=userDao.getNewManagerList(uIdM);
						
						request.setAttribute("managerNames", managerNames);
						request.getRequestDispatcher("/PendingOrderManager.jsp").forward(request, response);
						
					
					}
					

				}
				else if(role.equalsIgnoreCase("Supplier"))
				{

					supplierOrders=userDao.getSupplierOrdersDelete(uIdM);
					
					if(supplierOrders.isEmpty())
					{
						int delete=userDao.deleteUserId(uIdM);
						
						if(delete!=0)
						{
							request.setAttribute("msg1", "Record Deleted Successfully");
							request.getRequestDispatcher("/DeleteManagerrr1.jsp").include(request, response);
						}
						else
						{request.setAttribute("msg1","Record Couldn't be Deleted");
						request.getRequestDispatcher("/DeleteManagerrr1.jsp").include(request, response);
						}
						
					}

					else
					{
						request.setAttribute("supplierOrders",supplierOrders);
						//out.println("The Supplier has Orders Pending at his Disposal!--- Cannot be Deleted");
						request.setAttribute("msg2", "The Supplier has Orders Pending at his Disposal!--- Cannot be Deleted");
						request.getRequestDispatcher("/PendingOrderSupplier.jsp").include(request, response);

					}
				}
				else if(role.equalsIgnoreCase("Quality Control Engineer"))
				{
					int qualityDelete=0;
					qualityOrders=userDao.getQualityOrdersDelete(uIdM);
					if(qualityOrders.isEmpty())
					{
						qualityDelete=userDao.deleteUserId(uIdM);
						
					}

					else
					{
						request.setAttribute("qualityOrders",qualityOrders);
						request.setAttribute("msg2","The Quality Control Officer has Orders Pending at his Disposal!--- Cannot be Deleted");
						request.getRequestDispatcher("/PendingOrderSupplier.jsp").include(request, response);
						
					}
					if(qualityDelete!=0)
					{
						request.setAttribute("msg1", "Record Deleted Successfully");
						request.getRequestDispatcher("/DeleteManagerrr1.jsp").include(request, response);
					}
					else
					{
						request.setAttribute("msg1","Record Couldn't be Deleted");
						request.getRequestDispatcher("/DeleteManagerrr1.jsp").include(request, response);
					}
					
				}
			}
		

		}




		catch(Exception e)
		{e.printStackTrace();}

	}

}
