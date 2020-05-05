import java.sql.*;

public class Assignment {

	public static void main(String[] args) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		CallableStatement cstmt=null;
		try {
			Class c=Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tigerr");
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		
		String sql="SELECT empno,ename FROM emp";
		try {
			stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery(sql);
			while(rset.next()) {
				int empid=rset.getInt(1);
				String ename=rset.getString(2);
				double netsalary=0;
				cstmt=conn.prepareCall("{call calculateSalary(?,?)}");
				cstmt.setInt(1, empid);
				cstmt.registerOutParameter(2, java.sql.Types.DOUBLE);
				cstmt.executeQuery();
				netsalary=cstmt.getDouble(2);
				System.out.println("EmpID: " + empid + ", Ename: " + ename + ", Net Salary: " + netsalary);	
			}	
			}catch(SQLException e) {
				System.out.println("Error occurred");
			}
		finally{
			conn.close();
			stmt.close();
			cstmt.close();
		}
			
			
		}

	}


