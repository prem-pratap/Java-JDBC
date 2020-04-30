import java.sql.*;

public class Assignment2 {

	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String username="hr";
		String password= "hr";
		try{
			//Class c=Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection established successfully");
			conn.close();
		}
		 catch (SQLException e) {
			 System.out.println("Connection not established");
			 e.printStackTrace();
		}
		
		

	}

}
