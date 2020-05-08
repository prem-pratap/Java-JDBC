package com.wipro.sales.main;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.bean.Stock;
import com.wipro.sales.service.Administrator;

public class SalesApplication {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);

		Administrator admin=new Administrator();
		int option=0;
		do {
			System.out.println("1. Insert Stock");
			System.out.println("2. Delete Stock");
			System.out.println("3. Insert Sales");
			System.out.println("4. View Sales Report");
			System.out.print("Enter your Choice: ");
			option = sc.nextInt();
			
			switch(option){
			
			case 1:
				Stock stockobj=new Stock();
				System.out.println("Enter product id:");
				stockobj.setproductID(sc.next());
				System.out.println("Enter product name:");
				String pname=sc.next();
				pname=pname+sc.nextLine();
				stockobj.setproductName(pname);
				System.out.println("Enter quantity on hand:");
				stockobj.setquantityOnHand(sc.nextInt());
				System.out.println("Enter product unit price:");
				stockobj.setproductUnitPrice(sc.nextDouble());
				System.out.println("Enter product reorder level:");
				stockobj.setreorderLevel(sc.nextInt());
				
				admin.insertStock(stockobj);
				break;
				
			case 2:
				System.out.println("Enter product id to delete from stock:");
				String msg=admin.deleteStock(sc.next());
				if(msg.equals("Deleted"))
					System.out.println(msg+" successfully");
				else
					System.out.println(msg);
				
				break;
			case 3:
				Sales saleobj=new Sales();
				System.out.println("Enter sales id:");
				saleobj.setsalesID(sc.next());
				System.out.print("Enter date (dd/MM/yyyy):");
				String sDate = sc.next();
				Date date = new SimpleDateFormat("dd/MM/yy").parse(sDate);
				
				saleobj.setsalesDate(date);
				System.out.println("Enter product id:");
				saleobj.setproductID(sc.next());
				System.out.println("Enter quantity sold:");
				saleobj.setquantitySold(sc.nextInt());
				System.out.println("Enter sales price per unit:");
				saleobj.setsalesPricePerUnit(sc.nextDouble());
				
				String result=admin.insertSales(saleobj);
				System.out.println(result);
				break;
				
			case 4:
				System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n","Product ID","Product Name"," Sales ID","Sales Date","Product unit price"," Product sales price"," Quantity sold"," Profit Amount");
				for(SalesReport obj:admin.getSalesReport()) {
					System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n",obj.getproductID(),obj.getproductName(),
							obj.getsalesID(),obj.getsalesDate(),obj.getproductUnitPrice(),
							obj.getsalesPricePerUnit(),obj.getquantitySold(),obj.getprofitAmount());
				}
				break;
				
			default:
				System.out.println("Exiting...");
				option = 0;
				break;	
			}
			
		}while(option >=1 && option <=4);
		
	}

}
