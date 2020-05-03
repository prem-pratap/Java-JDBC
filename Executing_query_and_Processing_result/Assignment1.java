

import java.sql.*;

public class Assignment1 {

	public static void main(String[] args) {
		ResultSet rset;
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String username="scott";
		String password= "tigerr";
		try{
			Class c=Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection established successfully");
			//create statement object
			Statement stmt=conn.createStatement();
			System.out.println("Statement object created");
			//query 
			rset=stmt.executeQuery("SELECT empno,ename FROM emp");
			while(rset.next()) 
				System.out.println(rset.getInt(1)+" "+rset.getString("ename"));
			
			rset.close();
			stmt.close();
			conn.close();
		}
		catch(ClassNotFoundException ce) {
			System.out.println("Driver not loaded");
		}
		 catch (SQLException e) {
			 System.out.println("Connection not established");
		}

	}

}
