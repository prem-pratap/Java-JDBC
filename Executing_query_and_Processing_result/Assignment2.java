

import java.sql.*;

public class Assignment2 {

	public static void main(String[] args) {
		ResultSet rset=null;
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String username="scott";
		String password= "tigerr";
		try{
			Class c=Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url,username,password);
			//create statement object
			Statement stmt=conn.createStatement();
			//query 
			rset=stmt.executeQuery("SELECT ename,job,sal,comm FROM emp WHERE sal >1000  AND sal < 2000");
			System.out.format("%-15s%-15s%-20s%-20s\n","Ename","Job","Salary","Commission");
			while(rset.next()) {
				 String name = rset.getString(1);
				 String job=rset.getString(2);
				 double salary=rset.getDouble("sal");
				 double commission=rset.getDouble("comm");
				 boolean b=rset.wasNull();
				 if(b)
					 commission=0;
				 System.out.format("%-15s%-15s%-20s%-20s\n",name,job,salary,commission);
			
			}
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
