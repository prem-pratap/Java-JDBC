package com.wipro.sales.bean;

public class Stock {
	private String productID;
	private String productName;
	private int quantityOnHand;
	private double productUnitPrice;
	private int reorderLevel;
	
	//getters
	public String  getproductID() {
		return productID;
	}
	
	public String getproductName() {
		return productName;
	}
	
	public int getquantityOnHand() {
		return quantityOnHand;
	}
	
	public double getproductUnitPrice() {
		return productUnitPrice;
	}
	
	public int getreorderLevel() {
		return reorderLevel;
	}
	
	//setters
	public void setproductID(String id) {
		this.productID=id;
	}
	
	public void setproductName(String name) {
		this.productName=name;
	}
	
	public void setquantityOnHand(int quantity) {
		this.quantityOnHand=quantity;
	}
	
	public void setproductUnitPrice(double price) {
		this.productUnitPrice=price;
	}
	
	public void setreorderLevel(int level) {
		this.reorderLevel=level;
	}
	
}
