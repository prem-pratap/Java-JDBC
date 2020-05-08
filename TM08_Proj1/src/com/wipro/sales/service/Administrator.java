package com.wipro.sales.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.bean.Stock;
import com.wipro.sales.dao.SalesDao;
import com.wipro.sales.dao.StockDao;

public class Administrator {

	private StockDao stockdao_obj=new StockDao();
	private SalesDao salesdao_obj=new SalesDao();
	
	
	/*
	 * This method is used to insert the given stockobj into the TBL_STOCK table 
	 * using StockDao class insertStock method if the below conditions are successful.
	 * 1. Stock obj should not be null
	 * 2. ProductName should be of minimum 2 letters in length
	 * 3. If above 2 are valid generate Product Id using StockDao class generateProductId 
	 * 	method and store the same in the ProductID member of the given Stock Object
	 * 
	 * If any of the above conditions fail return “Data not Valid for insertion”
	 * Else Return the generated ProductId */
	
	
	public String insertStock(Stock stockobj) {
		try {
		if(stockobj!= null && stockobj.getproductName().length() >= 2) {
			String productID=stockdao_obj.generateProductID(stockobj.getproductName());
			stockobj.setproductID(productID);
			if(stockdao_obj.insertStock(stockobj)==1)
				return productID;
			else
				return "Data not valid for insertion";
			}
		else
			return "Data not valid for insertion";
		}catch(SQLException se) {
			System.out.println("Exception occurred while inserting stock");
			se.printStackTrace();
			return null;
		}	
			
		}


	 /*Delete the record of the given Product id using StockDao class deleteStock method, 
	 * if delete is successful return “deleted”
	 * else "record cannot be deleted"*/
	
	
	 public String deleteStock(String productID) throws SQLException {
		 if(stockdao_obj.deleteStock(productID)==1)
			 return "Deleted";
		 else
			 return "record cannot be deleted";
	 }
	 
	 /**
		 * This method is used to insert the given sales obj into the TBL_SALEStable using 
		 * SalesDao class insertSales method if the below conditions are successful.
		 * 1. Sales obj should not be null else return “Object not valid for insertion”
		 * 2. ProductID should be present in the TBL_STOCK table else return “Unknown 
		 * 	Product for sales”
		 * 3. Products current QuatityOnHand value should be more than the QuantitySold value
		 * 	else return “Not enough stock on hand for sales”
		 * 4. SalesDate should be current date or earlier date and not future date, 
		 * else return “Invalid date”
		 * 5. If above 4 are valid generate SalesId using SalesDao class generateSalesId method 
		 * and store the same in the SalesID member of the given Sales Object Call the 
		 * insertSalesmethod of SalesDao and insert the record. 
		 * If insertion is successful call the updateStock method of the StockDao and 
		 * update the sold quantity to the stock.
		 * On successful completion of both the transaction return “Sales Completed”else “Error”.
	 * @throws SQLException 
		 */
	 
	 public String insertSales(Sales salesobj) throws SQLException {
		 if(salesobj ==null)
			 return "Object not valid for insertion";
		 
		 if(stockdao_obj.getStock(salesobj.getproductID())==null)
			 return "Unknown Product for sales";
		 
		 if(stockdao_obj.getStock(salesobj.getproductID()).getquantityOnHand() < salesobj.getquantitySold())
			 return "Not enough stock on hand for sales";
		 
		 Long ld=salesobj.getsalesDate().getTime();
		 Date next = new Date(ld);
		 Date current = new Date();
		 if (next.getTime() >current.getTime())
				return "Invalid date";
						
		 String salesid=salesdao_obj.generateSalesID(salesobj.getsalesDate());
		 salesobj.setsalesID(salesid);
		 
		 if(salesdao_obj.insertSales(salesobj)==1) {
			 if(stockdao_obj.updateStock(salesobj.getproductID(), salesobj.getquantitySold())==1)
				 return "Sales Completed";
			 else
				 return "Error";
		 }
		 else
			 return "Error";
		 
		 
	 }
		 
	 
	 /*This method calls the getSalesReport of the SalesDao and returns the ArrayList*/
	 
	 public ArrayList<SalesReport> getSalesReport() throws SQLException{
		 return salesdao_obj.getSalesReport();
	 }
		 
		 
		 
		 
	  


}
	

