package com.quinnox.ordermanagementsystem.daomodel;

import java.util.ArrayList;

public class Item {
	private int id;
	private String name;
	private int supid;
	
	private String imagePath;
	private String supplier;
	private String supplierOrg;
	
	private double price;
	private double quotedPrice;
	private int suppStock;
	
	private ArrayList itemSupplierPrice;
	
	
	public Item()
	{}
	
	public Item(String name) 
	{
		super();
		this.name = name;
		
	}


	public Item(final int id,final String name,final String imagePath,final double price)
	{
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.price = price;
	}

	public Item(final int id,final String name,final String imagePath,final String description,final double price)
	{
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.supplier = description;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String description) {
		this.supplier = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuotedPrice() {
		return quotedPrice;
	}
	public void setQuotedPrice(double quotedPrice) {
		this.quotedPrice = quotedPrice;
	}
	
	public ArrayList getItemSupplierPrice() {
		return itemSupplierPrice;
	}
	public void setItemSupplierPrice(ArrayList itemSupplierPrice) {
		this.itemSupplierPrice = itemSupplierPrice;
	}
	public int getSuppStock() {
		return suppStock;
	}

	public void setSuppStock(int suppStock) {
		this.suppStock = suppStock;
	}
	
	
	public int getSupid() {
		return supid;
	}

	public void setSupid(int supid) {
		this.supid = supid;
	}
	
	public String getSupplierOrg() {
		return supplierOrg;
	}

	public void setSupplierOrg(String supplierOrg) {
		this.supplierOrg = supplierOrg;
	}
	@Override
	public boolean equals(Object obj)
	{
		return (((Item)obj).id==id);
	}
}
