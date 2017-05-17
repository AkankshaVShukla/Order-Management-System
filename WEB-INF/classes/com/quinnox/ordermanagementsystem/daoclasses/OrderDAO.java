package com.quinnox.ordermanagementsystem.daoclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;



import com.quinnox.ordermanagementsystem.connectionmanager.ConnectionDetail;
import com.quinnox.ordermanagementsystem.daomodel.CartItem;
import com.quinnox.ordermanagementsystem.daomodel.Item;
import com.quinnox.ordermanagementsystem.daomodel.Order;
import com.quinnox.ordermanagementsystem.daomodel.OrderItems;
import com.quinnox.ordermanagementsystem.daomodel.User;


public class OrderDAO
{
	Item item=null;
	Order order1=null;
	ConnectionDetail connect=new ConnectionDetail();
	Connection connection;
	PreparedStatement ps=null;
	CartItem cartItem=null;
	Date date=null;
	//MANAGER METHOD
	public ArrayList<Order> sendOrderStatus(int uId)
	{
		ConnectionDetail connect=new ConnectionDetail();
		Connection con=connect.getConnection();
		Statement stmt;

		String query1;

		ArrayList<Order> arrayList=new ArrayList<Order>();
		try
		{
			stmt=con.createStatement();
			query1="select OID,STATUS,TIME,PLACEDBYID,UNAME from ORDERS inner join USERS on ORDERS.PLACEDBYID=USERS.UID WHERE USERS.MANAGERID="+uId+" OR USERS.UID="+uId;
			ResultSet rs=stmt.executeQuery(query1);
			while(rs.next())
			{
				Order order=new Order();
				order.setOrderId(rs.getInt("OID"));
				order.setPlacedById(rs.getInt("PLACEDBYID"));
				order.setuName(rs.getString("UNAME"));
				order.setStatus(rs.getString("STATUS"));
				order.setTime(rs.getString("TIME"));
				arrayList.add(order);

			}
		}

		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}

		return arrayList;
	}
	
	public ArrayList<Order> getAuditLog()
	{

		int uid=0;
		ConnectionDetail connect=new ConnectionDetail();
		Connection con=connect.getConnection();
		Statement stmt;

		String query1;


		ArrayList<Order> auditLog=new ArrayList<Order>();
		try
		{
			stmt=con.createStatement();
			query1="select OID,UNAME,STATUS,TIME,PLACEDBYUID,AUDITLOG.ROLE,COMMENTS from AUDITLOG inner join USERS on AUDITLOG.PLACEDBYUID=USERS.UID";
			ResultSet rs=stmt.executeQuery(query1);

			while(rs.next())
			{
				Order order=new Order();
				order.setOrderId(rs.getInt("OID"));
				order.setuName(rs.getString("UNAME"));
				order.setStatus(rs.getString("STATUS"));
				order.setTime(rs.getString("TIME"));
				order.setPlacedById(rs.getInt("PLACEDBYUID"));
				order.setRole(rs.getString("ROLE"));
				order.setComments(rs.getString("COMMENTS"));
				auditLog.add(order);

			}

		}
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}

		return auditLog;
	}

	public ArrayList<Order> getAuditLog(int uId)
	{

		int uid=0;
		ConnectionDetail connect=new ConnectionDetail();
		Connection con=connect.getConnection();
		Statement stmt;

		String query1;


		ArrayList<Order> auditLog=new ArrayList<Order>();
		try
		{
			stmt=con.createStatement();
			query1="select OID,UNAME,STATUS,TIME,PLACEDBYUID,AUDITLOG.ROLE,COMMENTS from AUDITLOG inner join USERS on AUDITLOG.PLACEDBYUID=USERS.UID WHERE USERS.MANAGERID="+uId+" OR USERS.UID="+uId;
			ResultSet rs=stmt.executeQuery(query1);

			while(rs.next())
			{
				Order order=new Order();
				order.setOrderId(rs.getInt("OID"));
				order.setuName(rs.getString("UNAME"));
				order.setStatus(rs.getString("STATUS"));
				order.setTime(rs.getString("TIME"));
				order.setPlacedById(rs.getInt("PLACEDBYUID"));
				order.setRole(rs.getString("ROLE"));
				order.setComments(rs.getString("COMMENTS"));
				auditLog.add(order);

			}

		}
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}

		return auditLog;
	}


	public ArrayList<Order> rejectedOrdersListManager(int managerId)
	{	Order orderRejected;
	ArrayList<Order> rejectedOrders=new ArrayList<Order>();
	try {

		connection=connect.getConnection();
		Statement stmt = connection.createStatement();

		ResultSet rs=stmt.executeQuery("select distinct orders.oid,auditlog.status,orderitem.suppid,users.uname,orders.time,AUDITLOG.COMMENTS from ORDERITEM,auditlog " +
				"inner join orders on AUDITLOG.TIME=ORDERS.time " +
				"inner join USERS on placedbyuid=uid " +
				"where auditlog.status='REJECTED' AND auditlog.ROLE='SUPPLIER' " +
				"and managerid="+managerId+" AND orderitem.status='REJECTED'");
		while(rs.next())
		{
			orderRejected=new Order();
			orderRejected.setOrderId(rs.getInt("OID"));
			orderRejected.setPlacedByName(rs.getString("UNAME"));
			orderRejected.setuName(getSupplierName(rs.getInt("SUPPID")));
			orderRejected.setTime(rs.getString("TIME"));
			orderRejected.setComments(rs.getString("COMMENTS"));
			rejectedOrders.add(orderRejected);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	connect.closeConnection();
	return rejectedOrders;
	}
	public String getSupplierName(int suppid)
	{
		String supplierName="";
		try {

			connection=connect.getConnection();
			Statement stmt = connection.createStatement();

			ResultSet rs=stmt.executeQuery("select ORGANIZATION FROM SUPPLIER WHERE SUPPID="+suppid);
			while(rs.next())
			{
				supplierName=rs.getString("ORGANIZATION");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		connect.closeConnection();
		return supplierName;
	}


	//PLACING ORDER DAO METHOD....
	public int getNewOrderId()
	{
		int orderId=0;
		Statement stmt;
		try 
		{
			connection=connect.getConnection();
			stmt = connection.createStatement();

			ResultSet rs=stmt.executeQuery("SELECT COUNT(OID) as 'total' FROM ORDERS");
			if(rs.next())
			{
				orderId=rs.getInt("total");
			}
			rs.close();
			stmt.close();
			connect.closeConnection();
			return orderId+1;

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderId;
	}
//add order....start..	
	Connection connection2=connect.getConnection();
	
	public int addOrder(Cart cart,User user)
	{	
		int result=0;
		int orderId=getNewOrderId();
		String status=null;
		if(user.getRole().equalsIgnoreCase("Purchase Manager"))
		{
			status="APPROVED";
		}
		else
		{
			status="PLACED";
		}

		
		String time=getTime();
		//System.out.println(time);
		String order="INSERT INTO ORDERS(OID,STATUS,TIME,PLACEDBYID)VALUES(?,?,CONVERT(datetime,'"+time+"',121),?)";
		try {
			
			//connection=connect.getConnection();
			ps=connection2.prepareStatement(order);
			   connection2.setAutoCommit(false);
			order1=new Order();
			order1.setStatus(status);
			ps.setInt(1,orderId);
			ps.setString(2,order1.getStatus());
			//ps.setString(3,);
			ps.setInt(3,user.getUid());

			result=ps.executeUpdate();
			ps.close();
			//connect.closeConnection();
			boolean logUpdate=false;
			if(result!=0)
			{
				addOrderItems(cart,orderId);
				logUpdate=updateLog(user,orderId,order1,time);


			}
			if(logUpdate==true)
			{
			connection2.commit(); 
			}} catch (SQLException e) {
			// TODO Auto-generated catch block
			try { 
				connection2.rollback();   
				}
			catch (SQLException sqx) { 
				sqx.printStackTrace();
				System.out.println("rollback failed");}
			e.printStackTrace();
		}

		return orderId;

	}
	public boolean addOrderItems(Cart cart,int orderId) throws SQLException
	{	
		boolean itemsadded=false;
		//connection=connect.getConnection();
		Collection cartItems=cart.getCartItems();
		Iterator it=cartItems.iterator();
		Item item=null;
		int itemId=1;
		int result=0;
		String orderItem="INSERT INTO ORDERITEM(OID,ITEMID,QUANTITY,PRICE,SUPPID,TOTAL_PRICE,STATUS)VALUES(?,?,?,?,?,?,?)";

		while(it.hasNext())
		{   
			cartItem=(CartItem) it.next();

			item=cartItem.getItem();
			String supplier=item.getSupplierOrg();
			System.out.println(supplier);
			int supplierId=new UserDAO().getSupplierId(supplier);
			try
			{
				//ps=connection.prepareStatement(orderItem);
				ps=connection2.prepareStatement(orderItem);
				ps.setInt(1,orderId);
				ps.setInt(2,item.getId());
				ps.setInt(3,cartItem.getQuantity());
				ps.setDouble(4,item.getQuotedPrice());
				ps.setInt(5,supplierId);
				ps.setDouble(6,cartItem.getQuantity()*item.getQuotedPrice());
				ps.setString(7,"WAITING");
				result=ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			itemId=itemId+1;
		}

		//connect.closeConnection();
		if(result>0)
		{
			itemsadded=true;	
		}
		return itemsadded;
	}


	public boolean updateLog(User user,int orderId,Order order,String time)
	{

		boolean logUpdated=false;
		//connection=connect.getConnection();

		String orderItem="INSERT INTO AUDITLOG(PLACEDBYUID,OID,STATUS,ROLE,TIME,COMMENTS)VALUES(?,?,?,?,CONVERT(datetime,'"+time+"',121),?)";
		int result=0;

		try
		{
			//ps=connection.prepareStatement(orderItem);
			ps=connection2.prepareStatement(orderItem);
			ps.setInt(1,user.getUid());
			ps.setInt(2,orderId);
			ps.setString(3,order.getStatus());
			ps.setString(4,user.getRole());
			//ps.setString(5,time);
			ps.setString(5,"NO");

			result=ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result>0)
		{
			logUpdated=true;
		}
		return logUpdated;

	}
//placing order finished above...
	
	public String getTime()
	{	GregorianCalendar g=new GregorianCalendar();
	g.setTime(new Date());
	String time=g.get(Calendar.YEAR)+"-"+(g.get(Calendar.MONTH)+1)+"-"+g.get(Calendar.DATE)+" "+
	g.get(Calendar.HOUR)+":"+g.get(Calendar.MINUTE)+":"+g.get(Calendar.SECOND)+"."+g.get(Calendar.MILLISECOND);
	return time;	

	}
	public ArrayList<Order> getOrders(int userId)
	{
		ArrayList<Order> orders=new ArrayList<Order>();
		Order order;
		try {
			connection=connect.getConnection();
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT OID,STATUS,TIME FROM ORDERS WHERE PLACEDBYID="+userId);
			while(rs.next())
			{
				order=new Order();
				order.setOrderId(rs.getInt("OID"));
				order.setStatus(rs.getString("STATUS"));
				order.setTime(""+rs.getTimestamp("TIME"));
				orders.add(order);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	public ArrayList<Order> ordersToApproveSuppliers(int supplierId)
	{	ArrayList<Order> ordersToApprove=new ArrayList<Order>();
	Order order;
	try {
		connection=connect.getConnection();
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("select ORDERITEM.ITEMID,ORDERITEM.OID,ITEMS.ITEMNAME,"+
				"ORDERITEM.QUANTITY,ORDERITEM.PRICE,UNAME,AUDITLOG.STATUS," +
				"AUDITLOG.PLACEDBYUID,AUDITLOG.TIME "+
				"from ITEMS,AUDITLOG inner join ORDERITEM on AUDITLOG.OID=ORDERITEM.OID " +
				"inner join USERS on AUDITLOG.PLACEDBYUID=USERS.UID " +
				" inner join ORDERS ON AUDITLOG.OID=ORDERS.OID"+
				" WHERE AUDITLOG.STATUS='APPROVED' AND AUDITLOG.ROLE='Purchase Manager' " +
				" AND ORDERITEM.SUPPID="+supplierId+" AND ITEMS.ITEMID=ORDERITEM.ITEMID " +
		" AND orders.status='APPROVED'");
		while(rs.next())
		{
			order=new Order();
			order.setOrderId(rs.getInt("OID"));
			//itemId.add(rs.getInt("ITEMID"));
			order.setSupplierPrice(getSupplierPrice(rs.getInt("ITEMID"), supplierId));
			order.setItemName(rs.getString("ITEMNAME"));
			order.setQuantity(rs.getInt("QUANTITY"));
			order.setSupplierPrice(getSupplierPrice(rs.getInt("ITEMID"), supplierId));
			order.setQuotedPrice(rs.getDouble("PRICE"));
			//order.setTotalPrice(rs.getDouble("TOTAL_PRICE"));
			//order.setStatus(rs.getString("STATUS"));
			order.setTime(""+rs.getTimestamp("TIME"));
			order.setPlacedById(rs.getInt("PLACEDBYUID"));
			order.setRejectById(supplierId);

			order.setPlacedByName(rs.getString("UNAME"));
			ordersToApprove.add(order);
		}
		connect.closeConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ArrayList<Order> ordersForSupplier=areAllItemApprovedSupplier(ordersToApprove,supplierId);

	return ordersToApprove;

	}

	public ArrayList<Order> areAllItemApprovedSupplier(ArrayList<Order> orders,int supplierId)
	{//System.out.println("coming");
		ArrayList ordersForSupplier=new ArrayList();
		connection=connect.getConnection();
		try {
			connection=connect.getConnection();
			Statement stmt=connection.createStatement();
			for (Object object :orders ) {
				Order order3=(Order)object;
				//System.out.println(order3.getOrderId());
				ResultSet rs=stmt.executeQuery("SELECT OID FROM ORDERITEM WHERE OID="+order3.getOrderId() +" AND SUPPID="+supplierId+" AND STATUS='WAITING'");	
				while(rs.next())
				{
					//System.out.println(rs.getInt("OID"));
					ordersForSupplier.add(order3);
				}
			}
			connect.closeConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return ordersForSupplier;
	}
	public Double getSupplierPrice(int itemId,int supplierId)
	{	
		Double supplierPrice=0.0;
		try {
			connection=connect.getConnection();
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT SPRICE FROM SUPPITEMS " +
					"WHERE SUPPID="+supplierId+" AND ITEMID="+itemId);
			while(rs.next())
			{
				supplierPrice=rs.getDouble("SPRICE");
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return supplierPrice;
	}
	//ACCEPT/REJECTED ORDER METHOD

	public int updateOrder(Order order)
	{	
		String time=getTime();
		int orderIdUpdated=0;
		int updated=0;
		int rejected=0;
		int logUpdated=0;
		int onlyItemsUpdated=0;
		int orderItemsApproved=0;
		String status=null;
		boolean allItemsApproved=false;
		if(order.getRole().trim().equalsIgnoreCase("Purchase Manager"))
		{
			status="PLACED";
		}
		else if(order.getRole().trim().equalsIgnoreCase("Quality Control Engineer"))
		{
			status="APPROVED";

		}

		else if(order.getRole().trim().equalsIgnoreCase("Supplier")&&order.getStatus().equalsIgnoreCase("APPROVED"))
		{	orderItemsApproved=orderItemsApprove(order);
		if(orderItemsApproved>0)
		{	
			allItemsApproved=areAllItemsApproved(order.getOrderId());
			if(allItemsApproved==true)
			{
				status="APPROVED";
				onlyItemsUpdated=0;
			}
			else
			{	onlyItemsUpdated=1;
			//System.out.println("only items approved");
			}
		}
		}
		else if(order.getRole().trim().equalsIgnoreCase("Supplier")&&order.getStatus().trim().equalsIgnoreCase("REJECTED"))
		{	orderItemsApproved=orderItemsApprove(order);
		//System.out.println(orderItemsApproved);
		status="APPROVED";
		}
		try {
			String query="UPDATE ORDERS SET STATUS='"+order.getStatus()+"',TIME=CONVERT(datetime,'"+time+"',121) WHERE STATUS='"+status+"' AND OID="+order.getOrderId();
			connection=connect.getConnection();
			PreparedStatement ps=connection.prepareStatement(query);
			updated=ps.executeUpdate();

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int placedById=getPlacedById(order.getOrderId());

		if(updated>0)
		{	String stat=order.getStatus();
		if(stat.equalsIgnoreCase("REJECTED"))
		{	
			//System.out.println(order.getStatus().trim());
			rejected=addRejection(order);
		}
		logUpdated=updateLog(order,time,placedById);
		}

		if(logUpdated>0||rejected>0)
		{
			orderIdUpdated=order.getOrderId();
		}
		if(onlyItemsUpdated==1)
		{
			orderIdUpdated=0;
		}
		else
		{
			orderIdUpdated=order.getOrderId();
		}
		return orderIdUpdated;

	}
	public int addRejection(Order order)
	{		
		OrderItems orderItems=null;
		HashMap itemsUnderOrder=new HashMap();
		ConnectionDetail connect=new ConnectionDetail();
		Connection con=connect.getConnection();
		int reject=0;
		PreparedStatement ps=null;
		String query=null;
		try
		{
			query="insert into REJECTION(OID,COMMENT,REJECTEDBY)values(?,?,?)";
			ps=con.prepareStatement(query);
			ps.setInt(1,order.getOrderId());
			ps.setString(2,order.getComments());
			ps.setInt(3,order.getUserId());


			reject = ps.executeUpdate();		

			ps.close();
		}
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}
		return reject;
	}
	public int getPlacedById(int orderId)
	{
		int placedById=0;
		connection=connect.getConnection();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs=stmt.executeQuery("select PLACEDBYID from orders where OID="+orderId);
			while(rs.next())
			{
				placedById=rs.getInt("PLACEDBYID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connect.closeConnection();
		return placedById;	
	}
	public int updateLog(Order order,String time,int placedById)
	{
		int updatedlog=0;
		connection=connect.getConnection();

		String orderItem="INSERT INTO AUDITLOG(PLACEDBYUID,OID,STATUS,ROLE,TIME,COMMENTS)VALUES(?,?,?,?,CONVERT(datetime,'"+time+"',121),?)";
		//String orderItems="DELETE FROM DBO.ORDERITEM WHERE OID="+order.getOrderId();
		//	int result=0;

		try
		{
			ps=connection.prepareStatement(orderItem);
			//ps1=connection.prepareStatement(orderItems);
			ps.setInt(1,placedById);
			ps.setInt(2,order.getOrderId());
			ps.setString(3,order.getStatus());
			ps.setString(4,order.getRole());
			//ps.setString(5,time);
			ps.setString(5,order.getComments());

			updatedlog=ps.executeUpdate();
			//ps1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return updatedlog;
	}
	public ArrayList getAuditQualitySupplier(String role)
	{
		int uid=0;
		ConnectionDetail connect=new ConnectionDetail();
		Connection con=connect.getConnection();
		Statement stmt;

		String query1="";


		ArrayList auditLog=new ArrayList();
		try
		{
			stmt=con.createStatement();
			if(role.equalsIgnoreCase("Supplier"))
			{
			query1="select * from AUDITLOG where role='Supplier'";
			}
			if(role.equalsIgnoreCase("quality"))
			{
				query1="select * from AUDITLOG inner join USERS on PLACEDBYUID=UID where role='quality'";
			}
			ResultSet rs=stmt.executeQuery(query1);

			while(rs.next())
			{
				Order order=new Order();
				order.setOrderId(rs.getInt("OID"));
				order.setStatus(rs.getString("STATUS"));
				order.setTime(rs.getString("TIME"));
				order.setPlacedByName(rs.getString("UNAME"));
				order.setRole(rs.getString("ROLE"));
				order.setComments(rs.getString("COMMENTS"));
				auditLog.add(order);

			}

		}	
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}

		return auditLog;

	}

	public boolean areAllItemsApproved(int orderId)
	{	
		boolean allItemsApproved=false;
		ArrayList allstatus=new ArrayList();
		try {
			connection=connect.getConnection();
			Statement stmt=connection.createStatement();
			ResultSet rs= stmt.executeQuery("SELECT STATUS FROM ORDERITEM WHERE OID="+orderId);
			while(rs.next())
			{
				allstatus.add(rs.getString("STATUS"));
			}
			for (Object object : allstatus)
			{
				if(object!=null&&((String)object).equalsIgnoreCase("APPROVED"))
				{
					allItemsApproved=true;
				}
				else{
					allItemsApproved=false;
					break;}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connect.closeConnection();
		return allItemsApproved;

	}
	public int orderItemsApprove(Order order)
	{
		String status="";
		int orderItemsApproved=0;
		if(order.getStatus().trim()=="APPROVED")
		{
			status="APPROVED";
		}

		else if(order.getStatus().trim().equalsIgnoreCase("REJECTED"))
		{
			status="REJECTED";
		}
		String orderItemsApprove="UPDATE ORDERITEM SET STATUS='"+status+"' WHERE OID="+order.getOrderId()+" AND SUPPID="+order.getRejectById();
		//String orderItems="DELETE FROM DBO.ORDERITEM WHERE OID="+order.getOrderId();


		try
		{
			connection=connect.getConnection();
			ps=connection.prepareStatement(orderItemsApprove);
			orderItemsApproved=ps.executeUpdate();
			//ps1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderItemsApproved;
	}
	public ArrayList getOrder(int orderId,int userId)
	{	Order order2;
	ArrayList orderSupplier=new ArrayList();
	connection=connect.getConnection();

	try {

		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("select ORDERITEM.ITEMID,ORDERITEM.OID,ITEMS.ITEMNAME," +
				"ORDERITEM.QUANTITY,ORDERITEM.PRICE,UNAME,AUDITLOG.STATUS," +
				"AUDITLOG.PLACEDBYUID,AUDITLOG.TIME" +
				" from ITEMS,AUDITLOG " +
				" INNER JOIN ORDERITEM ON AUDITLOG.OID=ORDERITEM.OID " +
				" INNER JOIN USERS on AUDITLOG.PLACEDBYUID=USERS.UID " +
				"WHERE AUDITLOG.STATUS='APPROVED' AND AUDITLOG.ROLE='Purchase Manager' " +
				"AND ORDERITEM.SUPPID="+userId+" AND ITEMS.ITEMID=ORDERITEM.ITEMID and AUDITLOG.OID="+orderId);
		while(rs.next())
		{ order2=new Order();
		order2.setOrderId(rs.getInt("OID"));
		order2.setItemId(rs.getInt("ITEMID"));
		//System.out.println(rs.getInt("ITEMID"));
		order2.setOrderId(rs.getInt("OID"));
		order2.setItemName(rs.getString("ITEMNAME"));
		order2.setQuantity(rs.getInt("QUANTITY"));
		order2.setQuotedPrice(rs.getDouble("PRICE"));
		order2.setSupplierPrice(getSupplierPrice(rs.getInt("ITEMID"),userId));
		order2.setPlacedByName(rs.getString("UNAME"));
		order2.setTime(rs.getString("TIME"));
		orderSupplier.add(order2);
		}

	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return orderSupplier;
	}
	public ArrayList getOrderQuality()
	{	Order orderQuality;
	ArrayList ordersQuality=new ArrayList();
	try {
		connection=connect.getConnection();
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT AUDITLOG.STATUS,AUDITLOG.OID,ORDERS.TIME,UNAME FROM AUDITLOG " +
				"INNER JOIN USERS ON PLACEDBYUID=USERS.UID " +
				"INNER JOIN ORDERS ON AUDITLOG.TIME=ORDERS.TIME " +
		"WHERE AUDITLOG.STATUS='APPROVED' AND AUDITLOG.ROLE='Supplier'");

		while(rs.next())
		{	orderQuality=new Order();
		orderQuality.setOrderId(rs.getInt("OID"));
		orderQuality.setPlacedByName(rs.getString("UNAME"));
		orderQuality.setTime(rs.getString("TIME"));
		ordersQuality.add(orderQuality);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(ordersQuality.isEmpty());
	return ordersQuality;

	}
	public ArrayList getOrderApproveQuality(int orderid)
	{
		Order order4;
		ArrayList orderQuality=new ArrayList();
		connection=connect.getConnection();

		try {

			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery("select ORDERITEM.ITEMID,ORDERITEM.OID,ITEMS.ITEMNAME,ORDERITEM.QUANTITY,ORDERITEM.PRICE,ORDERITEM.SUPPID,UNAME," +
					"AUDITLOG.STATUS,AUDITLOG.PLACEDBYUID,AUDITLOG.TIME from ITEMS,AUDITLOG " +
					"INNER JOIN ORDERITEM ON AUDITLOG.OID=ORDERITEM.OID " +
					"INNER JOIN USERS on AUDITLOG.PLACEDBYUID=USERS.UID " +
					"WHERE AUDITLOG.STATUS='APPROVED' AND AUDITLOG.ROLE='Supplier' " +
					"AND ITEMS.ITEMID=ORDERITEM.ITEMID and AUDITLOG.OID="+orderid);
			while(rs.next())
			{ order4=new Order();
			order4.setOrderId(rs.getInt("OID"));
			order4.setItemId(rs.getInt("ITEMID"));
			//System.out.println(rs.getInt("ITEMID"));
			order4.setOrderId(rs.getInt("OID"));
			order4.setItemName(rs.getString("ITEMNAME"));
			order4.setQuantity(rs.getInt("QUANTITY"));
			order4.setQuotedPrice(rs.getDouble("PRICE"));
			order4.setSupplierPrice(getSupplierPrice(rs.getInt("ITEMID"),rs.getInt("SUPPID")));
			order4.setPlacedByName(rs.getString("UNAME"));
			order4.setTime(rs.getString("TIME"));
			orderQuality.add(order4);
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderQuality;

	}
	
//AUDIT LOG METHODS NEW...
public ArrayList getAuditLogOrder(int orderId)
{
	ArrayList orderAudit=new ArrayList();
	Order order5;
	//ArrayList orderQuality=new ArrayList();
	connection=connect.getConnection();

	try {

		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT UNAME,OID,AUDITLOG.STATUS,AUDITLOG.ROLE,TIME,AUDITLOG.COMMENTS  FROM AUDITLOG inner join users on PLACEDBYUID=UID WHERE OID="+orderId);
	while(rs.next())
	{
		order5=new Order();
		order5.setPlacedByName(rs.getString("UNAME"));
		order5.setOrderId(rs.getInt("OID"));
		order5.setStatus(rs.getString("STATUS"));
		order5.setTime(rs.getString("TIME"));
		order5.setComments(rs.getString("COMMENTS"));
		order5.setRole(rs.getString("ROLE"));
		orderAudit.add(order5);
	}
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	return orderAudit;
}
public ArrayList getOrderDetail(int orderId)
{
	ArrayList orderDetails=new ArrayList();
	Order order5;
	//ArrayList orderQuality=new ArrayList();
	connection=connect.getConnection();

	try {

		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("select orderitem.oid,price,organization,itemname from orderitem " +
				"inner join items on orderitem.itemid=items.itemid " +
				"inner join supplier on orderitem.suppid=supplier.suppid " +
				"where orderitem.oid="+orderId);
	while(rs.next())
	{
		order5=new Order();
		//order5.setPlacedByName(rs.getString("UNAME"));
		order5.setOrderId(rs.getInt("OID"));
		order5.setQuotedPrice(rs.getDouble("PRICE"));
	order5.setItemName(rs.getString("itemname"));	
		order5.setStatus(rs.getString("ORGANIZATION"));
		orderDetails.add(order5);
	}
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	return orderDetails;
}
public ArrayList getHistoryQuality()
{

	ArrayList orderAudit=new ArrayList();
	Order order5;
	//ArrayList orderQuality=new ArrayList();
	connection=connect.getConnection();

	try {

		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT UNAME,OID,AUDITLOG.STATUS,AUDITLOG.ROLE,TIME,AUDITLOG.COMMENTS  FROM AUDITLOG inner join users on PLACEDBYUID=UID WHERE AUDITLOG.ROLE='Quality Control Engineer'");
	while(rs.next())
	{
		order5=new Order();
		order5.setPlacedByName(rs.getString("UNAME"));
		order5.setOrderId(rs.getInt("OID"));
		order5.setStatus(rs.getString("STATUS"));
		order5.setTime(rs.getString("TIME"));
		order5.setComments("COMMENTS");
		order5.setRole(rs.getString("ROLE"));
		orderAudit.add(order5);
	}
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	return orderAudit;

	
}
	
}
