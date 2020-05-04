import java.sql.SQLException;

public class PerformDeletion {

	public static void main(String[] args) throws SQLException {
		deleteRecord obj=new deleteRecord();
		int roll_no=Integer.parseInt(args[0]);
		try {
			if(obj.deleteRow(roll_no))
				System.out.println("Record deleted successfully and updated into student log");
			else
				System.out.println("Error while deletion");
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		

	}

}
