package com.quinnox.ordermanagementsystem.order;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

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

/**
 * Servlet implementation class PlacingOrderServlet
 */
public class PlacingOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CartItem cartItem=null;
	Item item=null;
	User user=null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlacingOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
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
		logged=(Boolean)session.getAttribute("Logged");
		System.out.println(logged);
		if(logged!=null && !logged)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			int  orderPlaced=0;
			response.setContentType("text/html");
			user =(User)session.getAttribute("user");
			if(user!=null)
			{
				if(logged==true)
				{ if(session.getAttribute("cart")!=null)
				 {
					Cart cart=(Cart)session.getAttribute("cart");
					Collection cartItems=cart.getCartItems();
					Iterator it=cartItems.iterator();
					if(cartItems.isEmpty()==false)
					{
						OrderDAO order=new OrderDAO();
						orderPlaced=order.addOrder(cart,user);
						if(orderPlaced>0)
						{	session.removeAttribute("cart");
						session.removeAttribute("itemSupplierList");
						request.setAttribute("cart",cart);
						request.setAttribute("orderId",orderPlaced);
						request.getRequestDispatcher("ProductCart/OrderPlaced.jsp").forward(request, response);
						}
					}
				}
				}
			}
		}
	}

}
