

import java.sql.Connection;
import java.sql.DriverManager;

public class MakeConnection {
	
	Connection con;
	Connection connectionFactory() { 
	try {
		Class c=Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return con;
	}

}
