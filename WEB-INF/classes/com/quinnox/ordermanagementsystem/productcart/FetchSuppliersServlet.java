package com.quinnox.ordermanagementsystem.productcart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daomodel.User;
import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;

//import com.microsoft.sqlserver.jdbc.*;
/**
 * Servlet implementation class FetchItemsServlet
 */
public class FetchSuppliersServlet extends HttpServlet
{	
	User user=null;
	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated method stub
		response.setContentType("text/html");
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
			try 
			{
				UserDAO dao=new UserDAO();
				ArrayList supplier=dao.getSupplier();
				request.setAttribute("supplier",supplier);
				request.getRequestDispatcher("ProductCart/Catalog.jsp").forward(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}



}
