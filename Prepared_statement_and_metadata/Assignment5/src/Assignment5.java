

public class Assignment5 {
	public static void main(String[] args) {
	DisplayRecord obj=new DisplayRecord();
	
	if(args.length==0) 
		obj.display();
	else if(args.length==1) {
		int rollno=Integer.parseInt(args[0]);
		obj.display(rollno);
		}
	}
}
