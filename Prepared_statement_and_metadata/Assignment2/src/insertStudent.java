import java.sql.*;

public class insertStudent {

	Connection conn;
	public insertStudent() throws SQLException{
		MakeConnection x=new MakeConnection();
		conn=x.connectionFactory();
	
	}
	
	boolean tableExist() throws SQLException {
		boolean texist=false;
		DatabaseMetaData dbm=conn.getMetaData();
		ResultSet tables=dbm.getTables(null, null, "STUDENT", null);
		//checking whether table exist otherwise create new table
		if(tables.next()) 
				texist=true;						
		else {
		PreparedStatement pstmt =conn.prepareStatement("create table student "
				+ "(rollno Number(4) CONSTRAINT roll_id PRIMARY KEY,studentname varchar(20) NOT NULL,standard varchar(2) NOT NULL,Date_of_birth date,fees Number(9,2))");
				pstmt.executeUpdate();
				texist=true;
				pstmt.close();
			}
		tables.close();
		return texist;
		}
	
	//method to insert row
	boolean insertRow(int rollno,String name,String standard,String dob,double fee) throws SQLException{
		boolean checkTable=false;
		try{
			checkTable= this.tableExist();
		if(checkTable) {
				String sql="INSERT INTO student VALUES(?,?,?,?,?)";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, rollno);
				pstmt.setString(2, name);
				pstmt.setString(3, standard);
				pstmt.setString(4, dob);
				pstmt.setDouble(5, fee);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
		
		}catch(SQLException se) {
			System.out.println("Exception occurred while insertion");
			checkTable=false;
			conn.close();
		}
		return checkTable;	
	}
	
	
	
}
