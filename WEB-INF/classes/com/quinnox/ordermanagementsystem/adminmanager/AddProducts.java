package com.quinnox.ordermanagementsystem.adminmanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quinnox.ordermanagementsystem.daoclasses.ItemDAO;
import com.quinnox.ordermanagementsystem.daoclasses.UserDAO;
import com.quinnox.ordermanagementsystem.daomodel.Item;
import com.quinnox.ordermanagementsystem.daomodel.User;


/**
 * Servlet implementation class AddProducts
 */
public class AddProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//System.out.println("hii");
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
			ArrayList<User> userList = new ArrayList<User>();
			ArrayList<Integer> suppIdList= new ArrayList<Integer>();
			ArrayList<Integer> stockList= new ArrayList<Integer>();
			ArrayList<Integer> priceList= new ArrayList<Integer>();
		
			UserDAO userDao=new UserDAO();
			ItemDAO itemDao=new ItemDAO();
			String itemName=request.getParameter("itemName");
			//String imagePath=request.getParameter("imagePath");
			
			
			int stock=0;
			int price=0;
			int result=0;
				
			if((itemName==null || itemName.equals("") ))
			{
				RequestDispatcher rd=request.getRequestDispatcher("PopulateSupplier");
				rd.forward(request, response);
			}
			else if(itemName!=null || itemName!="")
			{
			Item item=new Item(itemName);
			 try
			 {
				 	result=itemDao.addProductList(item) ;
				 
					userList=userDao.getSupplier();					
					for (Object object : userList)
					 {
						User user1=(User)object;
				        
						if(request.getParameter(""+user1.getUid())!=null && request.getParameter("stock"+user1.getUid())!="" && request.getParameter("price"+user1.getUid())!="")
						{
							suppIdList.add(user1.getUid());
							stockList.add(Integer.parseInt(request.getParameter("stock"+user1.getUid())));
							priceList.add(Integer.parseInt(request.getParameter("price"+user1.getUid())));								
							//System.out.println(user1.getUid()+","+stock+","+price+"\n");
						}
					 }
					 
					result=itemDao.addSupplierItems(item,suppIdList,stockList,priceList);
					

					System.out.println(result+"......");
					if(result!=0)
					{
						//result=0;
						
						request.setAttribute("message", "Product "+itemName+" added successfully");
						RequestDispatcher rd=request.getRequestDispatcher("PopulateSupplier");
						rd.forward(request, response);
					}			
					else
					{
						RequestDispatcher rd=request.getRequestDispatcher("PopulateSupplier");
						rd.forward(request, response);		
					}

					
				
					
					
			 }
			 catch (NullPointerException e) 
			 {
				 e.printStackTrace();
			 }
			 catch (ClassNotFoundException e) 
			 {
				e.printStackTrace();
			 } 
			 catch (SQLException e) 
			 {
				 e.printStackTrace();
			 }
		
			}
		}
		
	}

}
