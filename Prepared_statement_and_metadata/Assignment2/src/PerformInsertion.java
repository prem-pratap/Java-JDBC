
import java.sql.SQLException;
import java.util.*;

public class PerformInsertion {

	public static void main(String[] args) throws SQLException {
		insertStudent obj=new insertStudent();
		Scanner sc=new Scanner(System.in);
		String[] standards = {"I", "II", "III", "IV", "V", "Vi", "VII", "VIII", "IX", "X"};
		List<String> list = new ArrayList<>(Arrays.asList(standards));
		int roll_no;
		String name;
		String standard;
		String dob;
		double fees=0.0;
		
		try {
			//enter rollno and it must be of length 4 for a valid rollno
			boolean valid_roll_no=false;
			do {
				System.out.println("Enter roll no:");
				roll_no=sc.nextInt();
				if(String.valueOf(roll_no).length()==4)  
					valid_roll_no=true;
				else {
					System.out.println("Enter valid roll number");
					roll_no=0;
					valid_roll_no=false;}
			}while(valid_roll_no==false);
			
			//enter name and it should be in uppercase
			boolean valid_name=false;
			do {
			System.out.println("Enter name:");
			name=sc.next();
			boolean lowercasefound = false;
			for (int i = 0; i < name.length(); i++) {
				if (Character.isLowerCase(name.charAt(i))) {
					lowercasefound = true;
					break;
				}
			}
			
			if(name.length() <21 && !lowercasefound ) 
				valid_name=true;
				
			else { 
				System.out.println("Enter valid name");
				valid_name=false;
				}
			}while(valid_name==false);
			//enter standard which must lie in standards
			boolean valid_standard=false; 
			do{
				System.out.println("Enter standard");
				standard=sc.next();
				if(list.contains(standard))
					valid_standard=true;
				else {
					System.out.println("Enter valid standard");
					valid_standard=false;
				}
				
			}while(valid_standard==false);
			
			//enter date of birth
			System.out.print("Enter D.O.B.: ");
			dob = sc.next();
			
			//enter fees
			
			System.out.print("Enter fees: ");
			fees = sc.nextDouble();
			
			
			
			//inserting into table
			if (obj.insertRow(roll_no, name, standard, dob, fees));
				System.out.println("Details  inserted successfully");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
