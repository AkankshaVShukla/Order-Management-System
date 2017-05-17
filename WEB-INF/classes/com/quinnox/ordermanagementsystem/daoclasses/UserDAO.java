package com.quinnox.ordermanagementsystem.daoclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.quinnox.ordermanagementsystem.connectionmanager.ConnectionDetail;
import com.quinnox.ordermanagementsystem.daomodel.Item;
import com.quinnox.ordermanagementsystem.daomodel.ItemSupplierPrice;
import com.quinnox.ordermanagementsystem.daomodel.User;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDAO 
{	
	Connection con;
	ConnectionDetail connect;

	public UserDAO()
	{}

	public void setConnection() throws ClassNotFoundException,SQLException
	{
		connect=new ConnectionDetail();
		con=connect.getConnection();

	}

	public ArrayList getItemSupplierDetail(int suppId,int itemIdValue) throws ClassNotFoundException,SQLException
	{
		ArrayList itemSuppList=new ArrayList();
		try
		{
			setConnection();
			con.setAutoCommit(true);

			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from suppitems s INNER JOIN SUPPLIER ON s.SUPPID=supplier.suppid " +
					"where s.suppid="+suppId+"  and s.itemid="+itemIdValue);
			while(rs.next())
			{
				ItemSupplierPrice itemSP = new ItemSupplierPrice();
				itemSP.setSupplier(rs.getString("ORGANIZATION"));
				itemSP.setPrice(rs.getDouble("sprice"));
				itemSP.setStock(rs.getInt("stock"));

				itemSuppList.add(itemSP);

			}
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
		return itemSuppList;
	}

	public ArrayList<User> getItemSupplierName(int itemId) throws ClassNotFoundException,SQLException
	{
		ArrayList<User> users=new ArrayList<User>();
		try
		{

			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select sup.suppid, sup.organization from supplier sup where sup.suppid in (select s.suppid from suppitems s where s.itemid="+itemId+")");
			while(rs.next())
			{
				User user=new User();
				user.setUid(rs.getInt("suppid"));
				user.setOrganisation(rs.getString("organization"));			
				users.add(user);
			}
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
		return users;
	}


	public ArrayList<User> getSupplier() throws ClassNotFoundException,SQLException
	{
		ArrayList<User> users=new ArrayList<User>();
		try
		{
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM SUPPLIER");
			while(rs.next())
			{
				User user=new User();
				user.setUid(rs.getInt("SUPPID"));
				user.setOrganisation(rs.getString("ORGANIZATION"));
				users.add(user);
			}
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
		return users;
	}

	public User getSupplierOrganization(final int supId) throws ClassNotFoundException,SQLException
	{
		User user=new User();
		try
		{
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from supplier where suppid="+supId);
			if(rs.next())
			{
				user.setUid(rs.getInt(1));
				user.setOrganisation(rs.getString(2));

			}
			rs.close();
			stmt.close();
			//.commit();
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
		return user;
	}


	public Item getItem(final int itemId) throws ClassNotFoundException,SQLException
	{
		Item item=new Item();
		try
		{
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from items where itemid="+itemId);
			if(rs.next())
			{
				item.setId(rs.getInt(1));
				item.setName(rs.getString(2));

			}
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
		return item;
	}

	public int getItemCount() throws ClassNotFoundException,SQLException
	{
		int itemCount=0;
		try
		{
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT COUNT(*) FROM products");
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


	public HashMap<Integer,String> getsupplierIds() throws ClassNotFoundException,SQLException
	{
		setConnection();
		Statement ps=null;
		HashMap<Integer,String> supplierNames= new HashMap<Integer, String>();

		String command1="select * from supplier";
		//String command2="select organization from supplier where role='Supplier'";

		try
		{
			con.setAutoCommit(true);
			ps=con.createStatement();
			ResultSet rs=ps.executeQuery(command1);
			while (rs.next()) 
			{ 
				User user=new User();
				user.setUid(rs.getInt("suppid"));
				user.setOrganisation(rs.getString("organization"));
				supplierNames.put(user.getUid(),user.getOrganisation());
			}			

			rs.close();
			ps.close();
			//con.commit();

		} 
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			con.rollback();
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}
		return supplierNames;

	}

	public HashMap<Integer,String> getManagers() throws ClassNotFoundException,SQLException
	{
		setConnection();
		Statement ps=null;
		HashMap<Integer,String> managerNames= new HashMap<Integer, String>();

		String command1="select * from users where role='Purchase Manager'";	

		try
		{
			con.setAutoCommit(true);
			ps=con.createStatement();
			ResultSet rs=ps.executeQuery(command1);
			while (rs.next()) 
			{ 
				User user=new User();
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("uname"));
				managerNames.put(user.getUid(),user.getName());
			}			

			rs.close();
			ps.close();
			//con.commit();				
		} 
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			con.rollback();
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}
		return managerNames;

	}

	public ArrayList<User>  viewUsers() throws ClassNotFoundException,SQLException
	{
		setConnection();
		Statement ps=null;
		ArrayList<User> users=new ArrayList<User>();

		String command1="select * from users where USERSTATUS='ACTIVE' ";	

		try
		{
			con.setAutoCommit(true);
			ps=con.createStatement();
			ResultSet rs=ps.executeQuery(command1);
			while (rs.next()) 
			{ 
				User user=new User();

				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("uname"));
				user.setRole(rs.getString("role"));
				user.setManagerId(rs.getInt("managerid"));
				user.setDob(rs.getString("dob"));
				user.setPhoneNumber(rs.getString("phonenumber"));
				user.setEmail(rs.getString("email"));

				users.add(user);
			}			

			rs.close();
			ps.close();
			// con.commit();	
		} 
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			//con.rollback();
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}
		return users;

	}

	public int deleteUser(ArrayList uidList) throws ClassNotFoundException,SQLException
	{
		setConnection();
		Statement ps1=null;
		Statement ps2=null;
		Statement ps3=null;
		//ArrayList uidList1= uidList;

		String command1=null;
		String command2=null;
		String command3=null;
		int result=0;

		try
		{
			for (Object object : uidList) 
			{
				int uid=(Integer)object;

				command1="delete from login where uid="+uid+";";	
				command2="delete from supplier where suppid="+uid+";";
				command3="delete from users where uid="+uid+";";

				con.setAutoCommit(false);
				ps1=con.createStatement();
				result=ps1.executeUpdate(command1);
				ps1.close();	

				ps2=con.createStatement();
				result=ps2.executeUpdate(command2);
				ps2.close();	

				ps3=con.createStatement();
				result=ps3.executeUpdate(command3);
				ps3.close();	

				con.commit();
			}


		} 
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			con.rollback();
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}
		return result;

	}

	public User getUser(final String username) throws ClassNotFoundException,SQLException
	{
		User user=new User();
		try
		{
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select UID from LOGIN where USERNAME='"+username+"'");
			while(rs.next())
			{	System.out.println(rs.getInt("UID"));
			int uid=rs.getInt("UID");
			user=getUserByUid(uid);
			}
			rs.close();
			stmt.close();
			//	con.commit();
		}
		catch(SQLException exception)
		{
			//  con.rollback();
			exception.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}
		return user;
	}


	public User getUserByUid(int uid) throws ClassNotFoundException, SQLException
	{
		User user=new User();
		try
		{
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from USERS where UID="+uid);
			while(rs.next())
			{
				user.setUid(rs.getInt("UID"));
				user.setName(rs.getString("UNAME"));
				user.setRole(rs.getString("ROLE"));
			}

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
		return user;
	}


	public String generateLoginUserName(String uname,int uid)
	{

		uname.trim();
		String strUname[]=uname.split(" ");

		String loginUname="";
		if(strUname.length<2)
		{
			loginUname=strUname[0]+uid;
			return loginUname;
			//System.out.println(loginUname);
		}	
		else if(strUname.length==2)
		{
			loginUname=strUname[0]+(strUname[1].charAt(0))+uid;
			return loginUname;
			//System.out.println(loginUname);

		}
		else
		{
			System.out.println("invalid");
		}		

		return loginUname;
	}

	public int addSupplier(User user) throws ClassNotFoundException,SQLException
	{
		setConnection();
		PreparedStatement ps3=null;
		String command3=null;
		int result3=0;

		if(ps3==null)
		{
			try
			{
				command3="insert into supplier (suppid,organization)values(?,?)";
				//	con.setAutoCommit(true);
				ps3=con.prepareStatement(command3);
				//User user1=getUser(user.getName());		    


				System.out.println(user.getName()+"...."+user.getUid()+"**********"+user.getOrganisation());
				ps3.setInt(1,user.getUid());
				ps3.setString(2,user.getOrganisation());
				result3=ps3.executeUpdate();

				//ps3=null;
				ps3.close();
				//con.commit();

			} 
			catch (NullPointerException e)
			{			
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				//con.rollback();
				e.printStackTrace();
			}
			finally
			{
				connect.closeConnection();
			}
		}
		return result3;


	}

	public int addUsers(User user) throws ClassNotFoundException,SQLException
	{

		setConnection();
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		//PreparedStatement ps3=null;
		String command1=null;	
		String command2=null;
		//String command3=null;
		int result1=0;
		int result2=0;
		//int result3=0;

		if(ps1==null || ps2==null)
		{
			try
			{
				command1= "insert into users (uname,role,managerid,dob,phonenumber,email,userstatus)values(?,?,?,?,?,?,?)";
				command2="insert into login (uid,username,password,chgpassword)values(?,?,?,?)";
				//command3="insert into supplier (suppid,organization)values(?,?)";

				//System.out.println(userName +",,,"+role+"..."+managerId+".."+dob+"..."+phoneNumber+".");			
				//con.setAutoCommit(false);

				ps1=con.prepareStatement(command1);


				ps1.setString(1, user.getName());
				ps1.setString(2, user.getRole());
				ps1.setInt(3, user.getManagerId());
				ps1.setString(4, user.getDob());
				ps1.setString(5, user.getPhoneNumber());
				ps1.setString(6, user.getEmail());
				ps1.setString(7,"ACTIVE");

				result1=ps1.executeUpdate();

				//  ps1=null;
				ps1.close();

				if(result1!=0)
				{
					ps2=con.prepareStatement(command2);

					Statement stmt=con.createStatement();

					ResultSet rs=stmt.executeQuery("Select uid from USERS where uname='"+user.getName()+"'");
					while(rs.next())
					{
						user.setUid(rs.getInt("UID"));
					}		    

					System.out.println( user.getUid()+"****login*****");
					String loginName= generateLoginUserName(user.getName(),user.getUid());

					ps2.setInt(1, user.getUid());
					ps2.setString(2, loginName);
					ps2.setString(3,"welcome123");
					ps2.setString(4,"welcome123");
					result2=ps2.executeUpdate();
					//ps2=null;
					ps2.close();
					//connect.closeConnection();

				}
				System.out.println(user.getRole());
				String role=user.getRole();
				if( role.trim().equalsIgnoreCase("Supplier"))
				{
					System.out.println("supp comin");
					int result3=addSupplier(user);
					//setConnection();
					/*ps3=con.prepareStatement(command3);
						//User user1=getUser(user.getName());		    


						System.out.println(user.getName()+"...."+user.getUid()+"**********"+user.getOrganisation());
						ps3.setInt(1, user.getUid());
						ps3.setString(2, user.getOrganisation());
						result3=ps3.executeUpdate();

						//ps3=null;
						ps3.close();*/
				}

				//con.commit();

			} 
			catch (NullPointerException e)
			{			
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				//	con.rollback();
				e.printStackTrace();
			}
			finally
			{
				connect.closeConnection();
			}
		}

		return result1;

	}

	public int getSupplierId(String supplier) throws SQLException
	{
		int supplierId=0;
		try
		{
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select SUPPID from SUPPLIER where ORGANIZATION='"+supplier+"'");
			if(rs.next())
			{
				supplierId=rs.getInt("SUPPID");
			}
			//con.commit();
		} 
		catch(SQLException exception)
		{
			con.rollback();
			exception.printStackTrace();
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}

		return supplierId;

	}



	public ArrayList<User> populateUserID(int uId) throws SQLException, ClassNotFoundException
	{	
		ArrayList<User> populateUsers=new ArrayList<User>();
		User user=new User();

		try
		{
			setConnection();

			con.setAutoCommit(true);

			Statement stmt=con.createStatement();
			String query="select UID,UNAME,ROLE,ManagerId from USERS where UID="+uId+" and USERSTATUS='ACTIVE'";
			ResultSet rs=stmt.executeQuery(query);

			while(rs.next())
			{
				user.setUid(rs.getInt("UID"));
				user.setName(rs.getString("UNAME"));
				user.setRole(rs.getString("ROLE"));
				user.setManagerId(rs.getInt("MANAGERID"));

				populateUsers.add(user);
			}
			//con.commit();
		}
		catch (SQLException e) 
		{
			con.rollback();
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}
		return populateUsers;
	}


	public ArrayList getManagerEmployees(int uId) throws SQLException
	{
		ArrayList employeeInfo=new ArrayList();
		User user=new User();


		try
		{
			setConnection();
			con.setAutoCommit(true);
			Statement stmt=con.createStatement();
			String query="select uid,uname,phonenumber,email from users where managerid="+uId;
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				user.setUid(rs.getInt("UID"));
				user.setName(rs.getString("UNAME"));
				user.setPhoneNumber(rs.getString("PHONENUMBER"));
				user.setEmail(rs.getString("EMAIL"));
				employeeInfo.add(user);

			}
			//con.commit();
		}
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			//con.rollback();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}
		return employeeInfo;
	}

	public HashMap getUsersUnderManagers(int uId)throws ClassNotFoundException,SQLException 
	{
		ResultSet rs1=null;
		ArrayList arrayList=new ArrayList();
		setConnection();
		int userId=0;
		String uName=null;
		HashMap userDetails= new HashMap<Integer, String>();
		try
		{	
			con.setAutoCommit(true);
			Statement stmt=(Statement) con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT UID,UNAME FROM USERS where MANAGERID="+uId);
			while(rs.next())
			{
				arrayList.add(rs.getInt("UID"));

			}


			for(Object obj:arrayList)
			{
				int userId1=(Integer) obj;
				rs1=stmt.executeQuery(" select u.UID,u.UNAME,o.OID from USERS u inner join ORDERS o on u.uid=o.placedbyid where o.status='placed' and o.PLACEDBYID="+userId1);			
				if(rs1!=null)
				{
					while(rs1.next())
					{			
						User user=new User();
						int orderId=rs1.getInt("OID");
						user.setUid(rs1.getInt("UID"));
						user.setName(rs1.getString("UNAME"));
						userDetails.put(orderId,user);

					}

				}
			}
			//con.commit();
		}
		catch (NullPointerException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e) 	
		{
			con.rollback();
			e.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}
		return userDetails;	
	}

	public int deleteUserId(int uId) throws SQLException, ClassNotFoundException
	{
		int inacAffected=0;


		setConnection();
		System.out.println(uId);
		//con.setAutoCommit(false);
		PreparedStatement stmt=con.prepareStatement("update USERS set USERSTATUS='INACTIVE' where UID="+uId);
		System.out.println("comin in");
		//String query="update USERS set USERSTATUS='INACTIVE' where UID="+uId;
		inacAffected=stmt.executeUpdate();
		System.out.println(inacAffected);
		connect.closeConnection();

		return inacAffected;
	}

	public ArrayList employeeOrdersPending(int empId) throws SQLException, ClassNotFoundException
	{
		ArrayList employeePendingOrders=new ArrayList();
		setConnection();
		int empAffected=0;
		con.setAutoCommit(false);
		Statement stmt=con.createStatement();
		String query="select a.oid,a.status,a.role from auditlog a inner join orders o on a.oid=o.oid where a.status='PLACED' and ROLE='EMPLOYEE' and o.placedbyid="+empId;
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next())
		{
			User user=new User();

			employeePendingOrders.add(rs.getInt("OID"));
		}

		return employeePendingOrders;
	}

	public int deleteUserIdManager(int newManagerId,int uIdM) throws
	SQLException, ClassNotFoundException
	{
		int inacAffected=0;
		int idaffected=0;
		int finalaff=0;
		try
		{
			setConnection();

			//con.setAutoCommit(false);
			Statement stmt=con.createStatement();
			String query="update USERS set USERSTATUS='INACTIVE' where UID="+uIdM;
			inacAffected=stmt.executeUpdate(query);

			String query1="update users set managerid="+newManagerId+" where managerid="+uIdM;
				idaffected=stmt.executeUpdate(query1);

			if(inacAffected!=0 || idaffected!=0)
			{
				finalaff=1;
			}
			//con.commit();
		}

		catch (SQLException e)
		{
			//con.rollback();
			e.printStackTrace();
		}
		catch(ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			connect.closeConnection();
		}
		return finalaff;
	}



	public ArrayList getNewManagerList(int uId) throws ClassNotFoundException, SQLException
	{
		ArrayList managerList=new ArrayList();
		setConnection();
		User user=new User();
		try
		{
			//con.setAutoCommit(false);
			Statement stmt=(Statement) con.createStatement();
			ResultSet rs=stmt.executeQuery("select uid,uname from users where uid!="+uId+"and userstatus='ACTIVE' and role='Purchase Manager' ");
						while(rs.next())
						{       user=new User();
						user.setUid(rs.getInt("uid"));
						user.setName(rs.getString("uname"));
						managerList.add(user);
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


		return managerList;

	}

	public ArrayList getSupplierOrdersDelete(int suppId) throws SQLException, ClassNotFoundException
	{

		ArrayList deleteSupplier=new ArrayList();
		setConnection();

		try
		{
			con.setAutoCommit(false);
			Statement stmt=(Statement) con.createStatement();
			ResultSet rs=stmt.executeQuery("select a.oid,a.status,a.role from auditlog a inner join orderitem o on a.oid=o.oid where a.status='APPROVED' and ROLE='Purchase Manager' and o.SUPPID="+suppId);
						while(rs.next())
						{
							deleteSupplier.add(rs.getInt("OID"));

						}
		}
		catch(Exception e)
		{e.printStackTrace();}

		con.commit();
		return deleteSupplier;
	}

	public ArrayList getQualityOrdersDelete(int qualId) throws
	SQLException, ClassNotFoundException
	{

		ArrayList deleteQuality=new ArrayList();
		setConnection();

		try
		{
			con.setAutoCommit(false);
			Statement stmt=(Statement) con.createStatement();
			ResultSet rs=stmt.executeQuery("select a.oid,a.status,a.role from auditlog a  where a.status='APPROVED' and ROLE='SUPPLIER' ");
						while(rs.next())
						{
							deleteQuality.add(rs.getInt("OID"));

						}
		}
		catch(Exception e)
		{e.printStackTrace();}

		con.commit();
		return deleteQuality;
	}

    
    
    
	public String getOldPassword(int userId)
			{
				String oldPassword=null;
				try{
					setConnection();
					con.setAutoCommit(true);
					
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select password from login where uid="+userId);
					while (rs.next())
					{
						oldPassword=rs.getString("password");
					}
				
				}
				catch(Exception e)
				{e.printStackTrace();}
			return oldPassword;
			}
			public int updatePassword(int userId,String newPassword)
			{int rowsUpdated=0;
				try{
					setConnection();
					con.setAutoCommit(true);
					
					Statement stmt=con.createStatement();
					rowsUpdated=stmt.executeUpdate("update login set password= '"+newPassword+"' where uid="+userId);
					
				}
				catch(Exception e)
				{e.printStackTrace();}
			return rowsUpdated;
			}


			
			

}
