import java.sql.*;

public class Assignment1 {

	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String username="hr";
		String password= "hr";
		try{
			Class c=Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully");
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection established successfully");
			conn.close();
		}
		catch(ClassNotFoundException ce) {
			System.out.println("Driver not loaded");
			ce.printStackTrace();
		}
		 catch (SQLException e) {
			 System.out.println("Connection not established");
			 e.printStackTrace();
		}
		
		

	}

}
