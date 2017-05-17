package com.quinnox.ordermanagementsystem.daomodel;

public class User 
{
	private int uid=0;
	private String name=null;
	private String organisation=null;
	private String role=null;
	private int managerId=0;
	private String dob=null;
	private String phoneNumber=null;
	private String email=null;
	
	
	public User()
	{
		
	}
		
	public User(String name, String role, int managerId, String dob,
			String phoneNumber, String email) 
	{
		super();
		this.name = name;
		this.role = role;
		this.managerId = managerId;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public User(int uid, String name, String organisation, String role,
			int managerId, String dob, String phoneNumber, String email) 
	{
		super();
		this.uid = uid;
		this.name = name;
		this.organisation = organisation;
		this.role = role;
		this.managerId = managerId;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}



	public User(String name, String organisation, String role, int managerId,
			String dob, String phoneNumber, String email) {
		super();
		this.name = name;
		this.organisation = organisation;
		this.role = role;
		this.managerId = managerId;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrganisation() {
		return organisation;
	}
	
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public int getManagerId() {
		return managerId;
	}
	
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getDob() {
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

