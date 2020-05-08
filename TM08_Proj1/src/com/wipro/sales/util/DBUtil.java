package com.wipro.sales.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection conn;
	public  static Connection getDBConnection() {
		try {
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String username="scott";
			String password="tigerr";
			Class c=Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,username,password);
			
		}catch(ClassNotFoundException ce) {
			System.out.println("Driver not loaded");
		}
		catch(SQLException se) {
			System.out.println("Connection not established");
		}
		
		return conn;
		
	}
	
}
