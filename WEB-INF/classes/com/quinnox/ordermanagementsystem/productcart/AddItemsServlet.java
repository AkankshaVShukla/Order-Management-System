package com.quinnox.ordermanagementsystem.productcart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.Cart;
import com.quinnox.ordermanagementsystem.daomodel.CartItem;
import com.quinnox.ordermanagementsystem.daomodel.Item;
import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;
import com.quinnox.ordermanagementsystem.daomodel.User;
import com.quinnox.ordermanagementsystem.daoclasses.ItemDAO;
import com.quinnox.ordermanagementsystem.daomodel.ItemSupplierPrice;


/**
 * Servlet implementation class AddItemsServlet
 */
public class AddItemsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	User user=null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddItemsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession(false);
		Boolean logged=(Boolean)session.getAttribute("Logged");

		System.out.println(logged);
		if(logged!=null && !logged)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else if(logged!=null && logged==true)
		{
			// TODO Auto-generated method stub
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();

			session=request.getSession();
			//ArrayList suppliersPrices;
			ArrayList<Item> itemSupplierList=(ArrayList<Item>) session.getAttribute("itemSupplierList");
			session.removeAttribute("itemSupplierList");
			if(itemSupplierList!=null)
			{
				//System.out.println("cumin in");
				Cart cart =(Cart)session.getAttribute("cart");
				if(cart==null)
				{
					cart=new Cart();
					session.setAttribute("cart",cart);
				}
				try{
					for (Item item2 : itemSupplierList) 
					{	//System.out.println(item2.getId());

					//System.out.println(item2.getSupplierOrg());
					//item=dao.getItem(item2.getId(),item2.getSupplierOrg());
					CartItem cartItem=new CartItem("cartItem"+item2.getId()+item2.getSupplierOrg(),item2,1);
					cart.addCartItem(cartItem);

					}

					ArrayList<Item> itemSupplierList1 =new ArrayList<Item>();
					session.setAttribute("itemSupplierList",itemSupplierList1);
					request.getRequestDispatcher("ProductCart/DisplayCart.jsp").forward(request, response);	
				}

				catch(Exception e)
				{
					pw.println(e);
				}

			}

		}
		else
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}


}

