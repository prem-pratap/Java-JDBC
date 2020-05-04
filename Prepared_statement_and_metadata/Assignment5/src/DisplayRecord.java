import java.sql.*;

public class DisplayRecord{
	
	Connection con;
	{
	try {
		Class c=Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
	void display() {
		String sql="SELECT * FROM student";
		try {
			PreparedStatement pstmt =con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			System.out.format("%-15s%-20s%-15s%-15s%-15s\n","Roll No","Name","Standard","Date of Birth","Fees");
			while(rs.next()) 
				System.out.format("%-15s%-20s%-15s%-15s%-15s\n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getFloat(5));
			pstmt.close();
			rs.close();
			con.close();
			}catch (SQLException e) {
				System.out.println("Exception occurred while display");
			
			}
		
		}
	
		
	//display method to display particular row
	
	void display(int rollno) {
		String sql="SELECT * FROM student WHERE rollno="+rollno;
		try {
			PreparedStatement pstmt =con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			System.out.format("%-15s%-20s%-15s%-15s%-15s\n","Roll No","Name","Standard","Date of Birth","Fees");
			while(rs.next()) 
				System.out.format("%-15s%-20s%-15s%-15s%-15s\n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getFloat(5));
			pstmt.close();
			rs.close();
			con.close();
			}catch (SQLException e) {
				System.out.println("Exception occurred while display");
			
			}
	}
	
	
}
