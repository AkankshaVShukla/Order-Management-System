package com.quinnox.ordermanagementsystem.productcart;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.Cart;
import com.quinnox.ordermanagementsystem.daoclasses.ItemDAO;
import com.quinnox.ordermanagementsystem.daoclasses.OrderDAO;
import com.quinnox.ordermanagementsystem.daomodel.CartItem;
import com.quinnox.ordermanagementsystem.daomodel.Item;
import com.quinnox.ordermanagementsystem.daomodel.User;



/**
 * Servlet implementation class DecisionServlet
 */
public class DecisionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	CartItem cartItem=null;
	User user=null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DecisionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
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
			// TODO Auto-generated method stub
			response.setContentType("text/html");
			//PrintWriter pw=response.getWriter();
			String update=request.getParameter("update");
			double quotePrice=0.0;
			if(update!=null)
			{	 
				session =request.getSession();
				Cart cart=(Cart)session.getAttribute("cart");
				Collection cartItems=cart.getCartItems();
				Iterator it=cartItems.iterator();
				Vector v=new Vector();
				Item item=null;
				while(it.hasNext())
				{
					cartItem=(CartItem)it.next();

					quotePrice=Double.parseDouble(request.getParameter("q"+cartItem.getCartItemId()));
					item=cartItem.getItem();
					item.setQuotedPrice(quotePrice);
					cartItem.setQuantity(Integer.parseInt(request.getParameter("T"+cartItem.getCartItemId())));

					if(request.getParameter(cartItem.getCartItemId())!=null)
					{
						v.addElement(cartItem.getCartItemId());
					}
				}
				Enumeration en=v.elements();

				while(en.hasMoreElements())
				{
					String cartItemId=(String)en.nextElement();
					cartItem=new CartItem(cartItemId,null,0);
					cart.removeCartItem(cartItem);
				}

				request.getRequestDispatcher("ProductCart/DisplayCart.jsp").forward(request, response);

			}

			String shop=request.getParameter("shop");
			if(shop!=null)
			{
				request.getRequestDispatcher("GetItems1").forward(request,response);
			}

			String order=request.getParameter("order");
			if(order!=null)
			{
				request.getRequestDispatcher("PlacingOrderServlet").forward(request,response);
			}
		}

	}
}


