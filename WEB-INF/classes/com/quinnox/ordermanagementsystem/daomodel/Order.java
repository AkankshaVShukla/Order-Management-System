package com.quinnox.ordermanagementsystem.daomodel;

public class Order 
{
//variables for order table
	private int orderId,placedById,userId;
	private String status,time,itemName,placedByName;
	private int rejectById;
	private String uName;
	private String comments;
	
	//variables for ORDERITEM table
	private int itemId,quantity,supplierId;
	private double quotedPrice,totalPrice,supplierPrice;
	
	public Order(int orderId, int rejectById,String comments) {
		super();
		this.orderId = orderId;
		this.comments = comments;
		this.rejectById = rejectById;
	}

public String getuName() {
		return uName;
	}


	public void setuName(String uName) {
		this.uName = uName;
	}


public double getSupplierPrice() {
		return supplierPrice;
	}


	public void setSupplierPrice(double supplierPrice) {
		this.supplierPrice = supplierPrice;
	}


	public void setQuotedPrice(double quotedPrice) {
		this.quotedPrice = quotedPrice;
	}


	//variables for AUDITLOG table
	private int logId;
	private String Role,Comments;
	
public int getRejectById() {
		return rejectById;
	}


	public String getItemName() {
	return itemName;
}


public void setItemName(String itemName) {
	this.itemName = itemName;
}


public String getPlacedByName() {
	return placedByName;
}


public void setPlacedByName(String placedByName) {
	this.placedByName = placedByName;
}


	public void setRejectById(int rejectById) {
		this.rejectById = rejectById;
	}


public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	
	
	public Order() {
		
		// TODO Auto-generated constructor stub
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	
	public int getPlacedById() {
		return placedById;
	}


	public void setPlacedById(int placedById) {
		this.placedById = placedById;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Double getQuotedPrice() {
		return quotedPrice;
	}


	public void setQuotedPrice(Double quotedPrice) {
		this.quotedPrice = quotedPrice;
	}


	public int getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getLogId() {
		return logId;
	}


	public void setLogId(int logId) {
		this.logId = logId;
	}


	public String getRole() {
		return Role;
	}


	public void setRole(String role) {
		Role = role;
	}


	public String getComments() {
		return Comments;
	}


	public void setComments(String comments) {
		Comments = comments;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
	
	
	

}
