

import java.sql.SQLException;

public class JDBCCalls {

	public static void main(String[] args) throws SQLException {
		DAOClass obj=new DAOClass();
		int choice=Integer.parseInt(args[0]);
		int rollno;
		float fees;
		switch(choice) {
		case 1://insert into database
			rollno=Integer.parseInt(args[1]);
			String name=args[2];
			String standard=args[3];
			String dob=args[4];
			fees=Float.parseFloat(args[5]);
			
			if(obj.insertRow(rollno, name, standard, dob, fees))
				System.out.println("Record inserted successfully");
			else
				System.out.println("Insertion error");
			
			break;
		
		case 2:
			//delete a row 
			rollno=Integer.parseInt(args[1]);
			if(obj.deleteRow(rollno))
				System.out.println("Record deleted successfully");
			else
				System.out.println("Deletion error");
			break;
			
		case 3:
			//modify a row
			rollno=Integer.parseInt(args[1]);
			fees=Float.parseFloat(args[2]);
			if(obj.modifyRow(rollno, fees))
				System.out.println("Row modified successfully");
			else
				System.out.println("Modification error");
			break;
		
		case 4:
			try{
				if(args.length==2) {
					rollno=Integer.parseInt(args[1]);
					obj.display(rollno);
					break;
				}
				else {
					obj.display();
					break;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		}

	}

}
