package com.quinnox.ordermanagementsystem.connectionmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDetail
{
	    Connection con=null;
	   
	  /* private static final String Drivername="com.mysql.jdbc.Driver";
	   private static final String USERNAME="root";
	   private static final String PASSWORD="";
	   private static final String URL="jdbc:mysql://localhost:3306/ordermanagementsystem";
	  */ 
	 private static final String Drivername="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	 private static final String URL="jdbc:sqlserver://localhost;databaseName=ordermanagementsystem;integratedSecurity=true;";
	
	public Connection getConnection()
	{		
		try
		{
		 Class.forName(Drivername);
		// con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		  con=DriverManager.getConnection(URL);
		 
		}
		catch (ClassNotFoundException classNotFoundException) 
		{
			classNotFoundException.printStackTrace();
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public void closeConnection() 
	{
		try
		{
			con.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
