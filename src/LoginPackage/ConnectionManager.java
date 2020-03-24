package LoginPackage;
import java.sql.*;
import java.util.*;

public class ConnectionManager {
	static Connection con;
	static String url;
	
	public static Connection getConnection() {
		try {
			String url = "jdbc:mysql://127.0.0.1/Outfront_Booking";	//may have to change
			Class.forName("com.mysql.jdbc.Driver");
			
			try {
				con = DriverManager.getConnection(url, "root", "Ch!31574567");
				
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		return con;
	}
}
