package com.quinnox.ordermanagementsystem.daomodel;

import java.util.ArrayList;

public class ItemSupplierPrice {

	private String supplier;
	private Double price;
	private int stock;
	public ItemSupplierPrice()
	{

		// TODO Auto-generated constructor stub
	}


	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
