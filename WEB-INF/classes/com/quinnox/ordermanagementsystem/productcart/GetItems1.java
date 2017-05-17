package com.quinnox.ordermanagementsystem.productcart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.ItemDAO;
import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;
import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;
import com.quinnox.ordermanagementsystem.daomodel.Item;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class GetItems
 */
public class GetItems1 extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	User user=null;
	ArrayList items;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetItems1() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		Boolean logged=false;

		if(session.getAttribute("Logged")!=null)
			logged=(Boolean) session.getAttribute("Logged");
		System.out.println(logged);
		if(logged!=null && !logged)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{

			response.setContentType("text/html");
			user=(User)session.getAttribute("user");

			ItemDAO idao;
			OrderDAO orderDao;
			ArrayList orders;
			UserDAO udao;
			ArrayList supplierList = null;
			//ServletContext context=getServletContext();
			//PrintWriter pw=response.getWriter();	
			if(user!=null)
			{
			//String shopping=request.getParameter("shopping");
			//String history=request.getParameter("history");
			//String log=request.getParameter("log");


			
				idao=new ItemDAO();
				items=new ArrayList();
				try {
					items=idao.getAllItems();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int itemIdValue=0;
				int suppId=0;
				request.setAttribute("items",items);

				try
				{
					if(request.getParameter("dimeti")!=null ){
						itemIdValue = Integer.parseInt(request.getParameter("dimeti"));
					}
					if(itemIdValue!=0)
					{						
						System.out.println(".....itemIdValue...."+itemIdValue);	
						session.setAttribute("itemIdValue",itemIdValue); 


						Iterator it = items.iterator();
						Item item = null;
						int itemId;
						String itemName;
						while (it.hasNext()) 
						{
							item = (Item) it.next();
							itemId = item.getId();
							if(itemId==itemIdValue)
							{
								itemName = item.getName();
								session.setAttribute("itemName",itemName); //session item name
							}

						}   		
						udao=new UserDAO();
						supplierList=udao.getItemSupplierName(itemIdValue);
						request.setAttribute("supplierList",supplierList);
					}





					//get stock and price
					if(request.getParameter("dis")!=null )
					{
						suppId = Integer.parseInt(request.getParameter("dis"));
						
					}
					if(suppId!=0)
					{						
						System.out.println("...suppId......"+suppId);	
						session.setAttribute("suppId",suppId); 

						udao=new UserDAO();
						int itemidvalue=Integer.parseInt(request.getParameter("itemIdValue"));
						supplierList=udao.getItemSupplierName(itemidvalue);
						request.setAttribute("supplierList",supplierList);
						if( request.getAttribute("supplierList")!=null)
						{
							ArrayList sl=(ArrayList) request.getAttribute("supplierList");
							Iterator iterator = sl.iterator();
							User user1 = null;
							int uid;
							String userOrg;
System.out.println("cumin"+supplierList.isEmpty());
while (iterator.hasNext()) 
							{
								user1 = (User) iterator.next();
								uid = user1.getUid();
System.out.println("supplier "+uid);

								if(uid==suppId)
								{
									userOrg = user1.getOrganisation();
									System.out.println("comin in"+userOrg);
									session.setAttribute("userOrg",userOrg); //session item name
								}
							}
						}

						udao=new UserDAO();
						int itemId=(Integer) session.getAttribute("itemIdValue");
						ArrayList itemSuppList=udao.getItemSupplierDetail(suppId,itemId);
						request.setAttribute("itemSuppList",itemSuppList);

					}

				}catch(NullPointerException e)
				{
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//request.setAttribute("", arg1)
				request.getRequestDispatcher("ProductCart/Product1.jsp").forward(request, response);
			
			/*	if(history!=null)
				{
					orderDao =new OrderDAO();
					orders=new ArrayList();
					orders=orderDao.getOrders(user.getUid());
					request.setAttribute("orders",orders);
					request.getRequestDispatcher("OrdersLog/PlacedOrders.jsp").forward(request, response);
				}
			*/	
			}
		}	


	}



}
