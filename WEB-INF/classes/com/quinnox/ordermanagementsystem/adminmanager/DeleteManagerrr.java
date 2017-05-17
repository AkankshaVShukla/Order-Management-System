package com.quinnox.ordermanagementsystem.adminmanager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;

/**
 * Servlet implementation class DeleteManagerrr
 */
public class DeleteManagerrr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteManagerrr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		
		int deleteManager=0;
		int result=0;
		int uIdM=(Integer) session.getAttribute("userId");
		
		String value=request.getParameter("managerName");
		if(value!=null)
		{
		int valuemanager=Integer.parseInt(value);
		
		UserDAO userDAO=new UserDAO();
		try
		{
		result=userDAO.deleteUserIdManager(valuemanager,uIdM);
		if(result!=0)
		{
			
			//out.println("Manager deleted Successfully And "+value+"has been assigned to the employees");
			request.setAttribute("msg","Manager deleted Successfully And "+value+"has been assigned to the employees");
		}
		else
		{
			request.setAttribute("msg","manager could not be deleted" );
			//out.println("manager could not be deleted");
		}
		}
	
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e1) 
		{
			
			e1.printStackTrace();
		}
		request.getRequestDispatcher("/DeleteManagerrr1.jsp").include(request, response);
		
		}
	}
		
}


