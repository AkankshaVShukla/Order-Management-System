package com.quinnox.ordermanagementsystem.daomodel;

public class OrderItems {
	private int orderId,itemId,quantity,suppId;
	private double price;
	private String itemName;
	
	public OrderItems() {
		
	}
	
	public OrderItems(int quantity, int suppId, double price, String itemName) {
		super();
		this.quantity = quantity;
		this.suppId = suppId;
		this.price = price;
		this.itemName = itemName;
	}

	public OrderItems(int orderId, int itemId, int quantity, int suppId,
			double price, String itemName) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.suppId = suppId;
		this.price = price;
		this.itemName = itemName;
	}

	

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public OrderItems(int quantity, int suppId, double price) {
		super();
		this.quantity = quantity;
		this.suppId = suppId;
		this.price = price;
	}
	public OrderItems(int orderId, int itemId, int quantity, int suppId,
			double price) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.suppId = suppId;
		this.price = price;
	}
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	public int getSuppId() {
		return suppId;
	}
	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

}
