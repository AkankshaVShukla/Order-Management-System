package com.quinnox.ordermanagementsystem.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;
import com.quinnox.ordermanagementsystem.daomodel.Item;
import com.quinnox.ordermanagementsystem.daomodel.User;

/**
 * Servlet implementation class TempSessionCart
 */
public class TempSessionCart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	User user1;

	public TempSessionCart() 
	{
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

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
			user1=(User)session.getAttribute("user");


			int itemId=0;
			int supplierId=0;
			int stock=0;
			double price=0.0;

			UserDAO userDao = new UserDAO();
			Item item= new Item();
			Item itemData = new Item();
			User user = new User();

			ArrayList<Item> itemSupplierList = new ArrayList<Item>();

			itemId= Integer.parseInt(request.getParameter("itemName"));
			supplierId=Integer.parseInt(request.getParameter("supplierList"));
			stock=Integer.parseInt(request.getParameter("stockInp"));
			price=Double.parseDouble(request.getParameter("priceInp"));


			System.out.println(itemId);
			try 
			{ 
				if(itemId!=0 && supplierId!=0 && stock!=0 && price!=0)
				{
					System.out.println(itemId);

					if(session.getAttribute("itemSupplierList")==null)//session unset
					{
						item=userDao.getItem(itemId);
						user=userDao.getSupplierOrganization(supplierId);

						itemData.setId(itemId);
						itemData.setName(item.getName());
						itemData.setSupid(supplierId);
						itemData.setSuppStock(stock);
						itemData.setPrice(price);
						//itemData.setStatus("NO");
						itemData.setSupplierOrg(user.getOrganisation());

						itemSupplierList.add(itemData);
						session.setAttribute("itemSupplierList", itemSupplierList);


						System.out.println(item.getName()+"..."+user.getOrganisation()+"..."+stock+".."+price);
					}
					else
					{
						item=userDao.getItem(itemId);
						user=userDao.getSupplierOrganization(supplierId);

						itemData.setId(itemId);
						itemData.setName(item.getName());
						itemData.setSupid(supplierId);
						itemData.setSuppStock(stock);
						itemData.setPrice(price);
						itemData.setSupplierOrg(user.getOrganisation());
						//itemData.setStatus("NO");
						itemSupplierList=(ArrayList<Item>)session.getAttribute("itemSupplierList");
						itemSupplierList.add(itemData);
						session.setAttribute("itemSupplierList", itemSupplierList);
					}


				}


			}
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}


			request.getRequestDispatcher("/GetItems1").forward(request, response);


		}


	}
}
