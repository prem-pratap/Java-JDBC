import java.sql.*;

public class deleteRecord {
	Connection conn;
	public deleteRecord() throws SQLException{
		MakeConnection x=new MakeConnection();
		conn=x.connectionFactory();
	}
	
	
	boolean tableExist() throws SQLException {
		boolean texist=false;
		DatabaseMetaData dbm=conn.getMetaData();
		ResultSet tables=dbm.getTables(null, null, "STUDENTLOG", null);
		//checking whether table exist otherwise create new table
		if(tables.next()) 
				texist=true;						
		else {
		PreparedStatement pstmt =conn.prepareStatement("create table studentlog "
				+ "(rollno Number(4) CONSTRAINT roll_id_pk PRIMARY KEY,studentname varchar(20) NOT NULL,standard varchar(2) NOT NULL,Date_of_birth date,fees Number(9,2))");
				pstmt.executeUpdate();
				texist=true;
				pstmt.close();
			}
		tables.close();
		return texist;
		}
	
	
	boolean deleteRow(int rollno) throws SQLException {
		boolean checkLogTable=false;
		PreparedStatement pstmt;
		int logged=0;
		int deleted=0;
		try {
			checkLogTable=this.tableExist();
			if(checkLogTable) {
				String sqlInsert="INSERT INTO studentlog (SELECT * FROM  student WHERE rollno=?)";
				pstmt=conn.prepareStatement(sqlInsert);
				pstmt.setInt(1,rollno);
				logged=pstmt.executeUpdate();
				pstmt.close();
				String sqldelete="DELETE FROM student WHERE rollno=?";
				pstmt=conn.prepareStatement(sqldelete);
				pstmt.setInt(1, rollno);
				deleted=pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error while deletion");
			conn.close();
			
		}
		if(logged==1 && deleted==1)
			return true;
		else
			return false;
	}
	
}
