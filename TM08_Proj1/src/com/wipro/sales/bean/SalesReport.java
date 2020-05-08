package com.wipro.sales.bean;

import java.util.Date;

public class SalesReport {

	private String salesID;
	private Date salesDate;
	private String productID;
	private String productName;
	private int quantitySold;
	private double productUnitPrice;
	private double salesPricePerUnit;
	private double profitAmount;
	
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
	
	public String getproductName() {
		return productName;
	}
	
	public int getquantitySold() {
		return quantitySold;
	}
	
	public double getproductUnitPrice() {
		return productUnitPrice;
	}
	
	public double getsalesPricePerUnit() {
		return salesPricePerUnit;
	}
	
	public double getprofitAmount() {
		return profitAmount;
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
	
	public void setproductName(String name) {
		this.productName=name;
	}
	
	public void setquantitySold(int quantity) {
		this.quantitySold=quantity;
	}
	
	public void setproductUnitPrice(double price) {
		this.productUnitPrice=price;
	}
	
	public void setsalesPricePerUnit(double price) {
		this.salesPricePerUnit=price;
	}
	
	public void setprofitAmount(double amount) {
		this.profitAmount=amount;
	}
}
