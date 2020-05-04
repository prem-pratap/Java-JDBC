

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAOClass {
	
	
	Connection con;
	public DAOClass() throws SQLException {
		MakeConnection x=new MakeConnection();
		con=x.connectionFactory();
	}
	
	
	boolean tableExist() throws SQLException {
		boolean texist=false;
		DatabaseMetaData dbm=con.getMetaData();
		ResultSet tables=dbm.getTables(null, null, "STUDENT", null);
		//checking whether table exist otherwise create new table
		if(tables.next()) 
				texist=true;						
		else {
		PreparedStatement pstmt =con.prepareStatement("create table student "
				+ "(rollno Number(4) CONSTRAINT roll_id PRIMARY KEY,studentname varchar(20) NOT NULL,standard varchar(2) NOT NULL,Date_of_birth date,fees Number(9,2))");
				pstmt.executeUpdate();
				texist=true;
				pstmt.close();
			}
		
		
		tables.close();
		return texist;
		}	

	
	//method to insert row
	boolean insertRow(int rollno,String name,String standard,String dob,float fee) throws SQLException{
		boolean checkTable=false;
		try{
			checkTable= this.tableExist();
		if(checkTable) {
				String sql="INSERT INTO student VALUES(?,?,?,?,?)";
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, rollno);
				pstmt.setString(2, name);
				pstmt.setString(3, standard);
				pstmt.setString(4, dob);
				pstmt.setFloat(5, fee);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
			}
		
		}catch(SQLException se) {
			System.out.println("Exception occurred while insertion");
			checkTable=false;
		}
		return checkTable;	
	}
	
	
	//method to delete a row
	boolean deleteRow(int rollno) {
		String sql="DELETE FROM student WHERE rollno=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rollno);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			return true;
			
		} catch (SQLException e) {
			System.out.println("Exception occurred while deletion");
			return false;
		}
			
	}
		
	//method to method to modify a row
	
	boolean modifyRow(int rollno,float fee) {
		String sql="UPDATE student SET fees=? WHERE rollno=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rollno);
			pstmt.setFloat(2, fee);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			return true;	
		}catch (SQLException e) {
			System.out.println("Exception occurred while modification");
			return false;
		}	
	}
	
	//method to display all row
	
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
			}catch (SQLException e) {
				System.out.println("Exception occurred while display");
			
			}
	}
	
		
}

	

	

