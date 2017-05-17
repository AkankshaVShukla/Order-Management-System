package com.quinnox.ordermanagementsystem.daoclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.quinnox.ordermanagementsystem.connectionmanager.ConnectionDetail;
import com.quinnox.ordermanagementsystem.daomodel.OrderItems;

public class OrderItemsDAO {
	Connection con;
	ConnectionDetail connect;
	Statement stmt;
	
	
	public HashMap getOrderItems(int orderId) throws ClassNotFoundException,SQLException
	{
		OrderItems orderItems=null;
		HashMap itemsUnderOrder=new HashMap();
		connect=new ConnectionDetail();
		con=connect.getConnection();
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select o.itemid,o.quantity,o.price,o.suppid,i.itemname from dbo.ORDERITEM o inner join dbo.ITEMS i on o.itemid=i.itemid where OID="+orderId);
		while(rs.next())
		{
			orderItems=new OrderItems();
			int itemId=rs.getInt("itemid");
			orderItems.setQuantity(rs.getInt("quantity"));
			orderItems.setSuppId(rs.getInt("suppid"));
			orderItems.setPrice(rs.getDouble("price"));
			orderItems.setItemName(rs.getString("itemname"));
			itemsUnderOrder.put(itemId,orderItems);
		}
		connect.closeConnection();
		return itemsUnderOrder;
	}
	public ArrayList getOrderId()
	{
		ArrayList orderId=new ArrayList();
		connect=new ConnectionDetail();
		con=connect.getConnection();
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select OID from dbo.ORDERS");
			while(rs.next())
			{
				orderId.add(rs.getInt("OID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderId;
	}
	

	
	
public static void main(String[] args) {
	ArrayList al=new OrderItemsDAO().getOrderId();
	
	for(Object obj:al)
	{
	System.out.println(obj);
	}
	}
}
