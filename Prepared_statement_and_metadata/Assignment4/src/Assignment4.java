
import java.sql.*;
public class Assignment4 {

	public static void main(String[] args) {
		Connection con=null;
		try {
			Class c=Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int roll_no=Integer.parseInt(args[0]);
		double fees=Double.parseDouble(args[1]);
		
		try {
			String sql="UPDATE student SET fees=? WHERE rollno=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setDouble(1,fees);
			pstmt.setInt(2, roll_no);
			pstmt.executeUpdate();
			if (pstmt.executeUpdate() == 1)
				System.out.println("Fees of student having rollno = " + roll_no + " updated successfully");
			else
				System.out.println("Error");
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
