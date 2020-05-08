package com.wipro.sales.dao;

import java.awt.image.DataBufferUShort;
import java.sql.*;
import java.util.ArrayList;

import com.wipro.sales.util.*;
import com.wipro.sales.bean.*;

public class SalesDao {
	Connection conn=null;
	PreparedStatement pstmt=null;
	String sql=null;
	
	/*This method is used to insert the given sales obj into TBL_SALES table*/
	public int insertSales(Sales sales) throws SQLException {
		//java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(sales.getsalesDate().getTime());
	try {
		sql="INSERT INTO TBL_SALES VALUES(?,?,?,?,?)";
		conn=DBUtil.getDBConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, sales.getsalesID());
		pstmt.setDate(2, sqlDate);
		pstmt.setString(3, sales.getproductID());
		pstmt.setInt(4, sales.getquantitySold());
		pstmt.setDouble(5, sales.getsalesPricePerUnit());
		if(pstmt.executeUpdate()==1)
			return 1;
		else
			return 0;
		}catch(SQLException e) {
			System.out.println("Exception occurred while insertion");
			conn.close();
			return 0;
			
		}
	finally {
		pstmt.close();
		conn.close();
	}
	}
	
	/*this method is used to generate SALESID using last 2 digit of the year part of the given date
	 * concatenated with the SEQ_SALES_ID sequence generated number 
	 */
	public String generateSalesID(java.util.Date date) throws SQLException {
		sql="SELECT  SEQ_SALES_ID.nextval FROM dual";
		
		int SEQ_SALES_ID = 0;
		//getting last two digit of year part from date
		String out = date.toString().substring(date.toString().length()-2, date.toString().length());
		try {
			conn=DBUtil.getDBConnection();
			pstmt=conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			SEQ_SALES_ID = rs.getInt(1);
			out += SEQ_SALES_ID;
			conn.close();
			rs.close();
			return out;
		}catch(SQLException e) {
			System.out.println("Exception while generating sales ID");
			e.printStackTrace();
			conn.close();
			return null;
		}
		
	}
	
	/*This method runs the V_SALES_REPORT view and stores every record in SalesReport Bean adding
	 * them to an arraylist which is return back to the user
	 */
	public ArrayList<SalesReport> getSalesReport() throws SQLException{
		ArrayList<SalesReport>  list=new ArrayList<SalesReport>();
		sql="SELECT * FROM V_SALES_REPORT";
		try {
			conn=DBUtil.getDBConnection();
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				SalesReport sale_obj=new SalesReport();
				sale_obj.setsalesID(rs.getString(1));
				sale_obj.setsalesDate(rs.getDate(2));
				sale_obj.setproductID(rs.getString(3));
				sale_obj.setproductName(rs.getString(4));
				sale_obj.setquantitySold(rs.getInt(5));
				sale_obj.setproductUnitPrice(rs.getDouble(6));
				sale_obj.setsalesPricePerUnit(rs.getDouble(7));
				sale_obj.setprofitAmount(rs.getDouble(8));
				list.add(sale_obj);
				
				
			}
		
		}catch(SQLException se) {
				se.printStackTrace();
				conn.close();
				return null;
				
			}
		finally {
			conn.close();
			
		}
			
		return list;
	}
	
}
	

