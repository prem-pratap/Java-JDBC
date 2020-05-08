package com.wipro.sales.bean;

import java.util.Date;

public class Sales {
	private String salesID;
	private Date salesDate;
	private String productID;
	private int quantitySold;
	private double salesPricePerUnit;
	
	//getters
	public String getsalesID() {
		return salesID;
	}
	
	public Date getsalesDate() {
		return salesDate;
	}
	
	public String getproductID() {
		return productID;
	}
	
	public int getquantitySold() {
		return quantitySold;
	}
	
	public double getsalesPricePerUnit() {
		return salesPricePerUnit;
	}
	
	//setters
	
	public void setsalesID(String id) {
		this.salesID=id;
	}
	
	public void setsalesDate(Date salesDate) {
		this.salesDate=salesDate;
	}
	
	public void setproductID(String pid) {
		this.productID=pid;
	}
	
	public void setquantitySold(int quantity) {
		this.quantitySold=quantity;
	}
	
	public void setsalesPricePerUnit(double price) {
		this.salesPricePerUnit=price;
	}
}
