package com.quinnox.ordermanagementsystem.daoclasses;

import java.sql.*;
import java.util.ArrayList;
import com.quinnox.ordermanagementsystem.daomodel.ItemSupplierPrice;
import com.quinnox.ordermanagementsystem.connectionmanager.ConnectionDetail;
import com.quinnox.ordermanagementsystem.daomodel.Item;
public class ItemDAO 
{	

	Connection con;
	ConnectionDetail connect;
	ArrayList items;

	public ItemDAO()
	{}

	public void setConnection() throws ClassNotFoundException,SQLException
	{
		connect=new ConnectionDetail();
		con=connect.getConnection();

	}

	public int addSupplierItems(Item item,ArrayList<Integer> suppIdList,ArrayList<Integer> stockList,ArrayList<Integer> priceList)throws ClassNotFoundException,SQLException
	{
		PreparedStatement ps1=null;		
		String command1=null;	       	       	
		int result=0; 

		int suppId=0;
		int stock=0;
		int price=0;
		int index=0;
		try
		{
			setConnection();
		    con.setAutoCommit(false);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select itemid from items where itemname='"+item.getName()+"'");
			while(rs.next())
			{
				item.setId(rs.getInt("itemid"));
			}		    

			System.out.println( item.getId()+"*********");

			command1= "insert into suppitems (suppid,itemid,stock,sprice)values(?,?,?,?)";

			for (Integer integer : suppIdList) 
			{
				ps1=con.prepareStatement(command1);	
				suppId=(int)integer;
				stock=stockList.get(index);
				price=priceList.get(index);
				index++;

				System.out.println(suppId+".."+stock+".."+price);

				ps1.setInt(1, suppId);
				ps1.setInt(2, item.getId());
				ps1.setInt(3, stock);
				ps1.setInt(4, price);

				result=ps1.executeUpdate();	
				ps1.close();
				con.commit();
			}

		} 
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch(SQLException exception)
		{
			   con.rollback();
			   exception.printStackTrace();
		}
		finally
		{
		 connect.closeConnection();
		}
		return result;
	}

	public int addProductList(Item item) throws ClassNotFoundException,SQLException
	{
		PreparedStatement ps1=null;		
		String command1=null;	       	       	
		int result1=0;       

		UserDAO userDAO=new UserDAO();

		try
		{
			setConnection();
			con.setAutoCommit(false);
			command1= "insert into items (itemname)values(?)";
			ps1=con.prepareStatement(command1);			

			ps1.setString(1,item.getName());			
			result1=ps1.executeUpdate();		
			ps1.close();
			con.commit();

		} 
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch(SQLException exception)
		{
			   con.rollback();
			   exception.printStackTrace();
		}
		finally
		{
		 connect.closeConnection();
		}
		return result1;

	}


	public ArrayList getAllItems() throws SQLException
	{
		ArrayList suppliersPrices;
		items=new ArrayList();
		try {
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM ITEMS");
			while(rs.next())
			{
				suppliersPrices=new ArrayList();

				Item item=new Item();
				item.setId(rs.getInt("ITEMID"));
				item.setName(rs.getString("ITEMNAME"));
				suppliersPrices=getAllSuppliers(rs.getInt("ITEMID"));
				item.setItemSupplierPrice(suppliersPrices);
				items.add(item);

			}
			//con.commit();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException exception)
		{
			   con.rollback();
			   exception.printStackTrace();
		}
		finally
		{
		 connect.closeConnection();
		}
		return items;
	}

	public ArrayList getAllSuppliers(int itemId) throws SQLException
	{	
		ItemSupplierPrice itemSupplierPrice;
		ArrayList suppliers=new ArrayList();
		try {
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT SPRICE,ORGANIZATION FROM SUPPITEMS INNER JOIN SUPPLIER"+
					" ON SUPPITEMS.SUPPID=SUPPLIER.SUPPID WHERE ITEMID="+itemId);
			while(rs.next())
			{
				itemSupplierPrice=new ItemSupplierPrice();
				itemSupplierPrice.setPrice(rs.getDouble("SPRICE"));
				itemSupplierPrice.setSupplier(rs.getString("ORGANIZATION"));
				suppliers.add(itemSupplierPrice);
			}
			//con.commit();
			
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch(SQLException exception)
		{
			   con.rollback();
			   exception.printStackTrace();
		}
		finally
		{
		 connect.closeConnection();
		}
		return suppliers;
	}
	
	/*public Item getItem(final int itemId,String supplier) throws ClassNotFoundException,SQLException
	{
		//System.out.println(supplier);
		Item item=new Item();
		try
		{
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT ITEMS.ITEMID,ITEMNAME,ORGANIZATION,SPRICE FROM SUPPITEMS "+ 
					"INNER JOIN ITEMS ON (ITEMS.ITEMID=SUPPITEMS.ITEMID) "+
					"INNER JOIN SUPPLIER ON(SUPPITEMS.SUPPID=SUPPLIER.SUPPID) "+
					"WHERE SUPPITEMS.ITEMID="+itemId+" AND ORGANIZATION='"+supplier+"'");
			if(rs.next())
			{
				item.setId(rs.getInt("ITEMID"));
				item.setName(rs.getString("ITEMNAME"));
				item.setSupplier(rs.getString("ORGANIZATION"));
				item.setPrice(rs.getDouble("SPRICE"));

			}
			rs.close();
			stmt.close();
			//con.commit();
		}catch(SQLException exception)
		{
			   con.rollback();
			   exception.printStackTrace();
		}
		finally
		{
		 connect.closeConnection();
		}
		return item;
	}*/
	
	public int getItemCount() throws ClassNotFoundException,SQLException
	{
		int itemCount=0;
		try
		{
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT COUNT(*) FROM ITEMS");
			rs.next();
		    itemCount=rs.getInt(1);
			rs.close();
			stmt.close();
			//con.commit();
		}
		catch(SQLException exception)
		{
			   con.rollback();
			   exception.printStackTrace();
		}
		finally
		{
		 connect.closeConnection();
		}
			
		return itemCount;
	}
	
}
