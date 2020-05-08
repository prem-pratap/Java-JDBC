package com.wipro.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.wipro.sales.bean.Stock;
import com.wipro.sales.util.DBUtil;

public class StockDao {

	Connection conn=null;
	PreparedStatement pstmt=null;
	String sql=null;
	
	/*This method is used to insert the given sales obj into TBL_STOCK table*/
	public int insertStock(Stock stock) throws SQLException {
		
		
	try {
		sql="INSERT INTO TBL_STOCK VALUES(?,?,?,?,?)";
		conn=DBUtil.getDBConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, stock.getproductID());
		pstmt.setString(2, stock.getproductName());
		pstmt.setInt(3, stock.getquantityOnHand());
		pstmt.setDouble(4, stock.getproductUnitPrice());
		pstmt.setInt(5, stock.getreorderLevel());		
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
		conn.close();
		pstmt.close();
		}
	}
	 
	/*This method is used to generate StockID using the First 2 letters of the given
	 * product name concatenated with the SEQ_PRODUCT_ID sequence generated number.*/
	
	public String generateProductID(String productName) throws SQLException {
		sql="SELECT SEQ_PRODUCT_ID.NEXTVAL FROM DUAL";
		
		int SEQ_PRODUCT_ID=0;
		
		try {
			conn=DBUtil.getDBConnection();
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			SEQ_PRODUCT_ID = rs.getInt(1);
			String out = productName.substring(0, 2);
			out += SEQ_PRODUCT_ID;
			conn.close();
			rs.close();
			return out;
		}catch(SQLException se) {
			System.out.println("Exception occurred while generating product id");
			se.printStackTrace();
			conn.close();
			return null;
		}
		
	}
	
	/* This method is used to update the Stock table by subtracting the current 
	 * Quantity_On_Hand by the given soldQty of the given productID.*/
	
	public int updateStock(String productID,int soldQty) throws SQLException {
		sql="UPDATE tbl_stock SET quantity_on_hand=quantity_on_hand-? WHERE product_id=?";
		try {
			conn=DBUtil.getDBConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, soldQty);
			pstmt.setString(2, productID);
			if(pstmt.executeUpdate()==1)
				return 1;
			else
				return 0;
		}catch(SQLException se) {
			System.out.println("Exception while updating stock");
			conn.close();
			return 0;
			
		}
		finally {
			conn.close();
			pstmt.close();
		}
		
	}
	
	
	/** This method is used to fetch a specific record details from the Stock table 
	 * for the given productID, store the information to a Stock bean object the 
	 * return the same
	 * @throws SQLException */
	
	public Stock getStock(String productID) throws SQLException {
		sql="SELECT * FROM tbl_stock WHERE product_id=?";
		try {
			conn=DBUtil.getDBConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productID);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			Stock stock_obj=new Stock();
			stock_obj.setproductID(rs.getString(1));
			stock_obj.setproductName(rs.getString(2));
			stock_obj.setquantityOnHand(rs.getInt(3));
			stock_obj.setproductUnitPrice(rs.getDouble(4));
			stock_obj.setreorderLevel(rs.getInt(5));
			rs.close();
			return stock_obj;
		}catch(SQLException se) {
			System.out.println("Exception occurred while getting stock");
			return null;
		}
		finally {
			conn.close();
			pstmt.close();
		}
	}
	
	/* This method is used to delete the stock record of the given ProductID*/
	
	public int deleteStock(String productID) throws SQLException {
		sql="DELETE FROM tbl_stock WHERE product_id=?";
		
		try {
			conn=DBUtil.getDBConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productID);
			if (pstmt.executeUpdate() == 1) return 1;
			else return 0;
		}catch(SQLException se) {
			System.out.println("Exception occurred while deleting record in table stock");
			return 0; 
		}
		finally {
			conn.close();
			pstmt.close();
		}
		
	}
	
}
