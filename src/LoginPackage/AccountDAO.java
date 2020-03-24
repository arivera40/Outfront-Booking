package LoginPackage;
import java.text.*;
import java.util.*;
import java.sql.*;

public class AccountDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps;
	
	public int register(Account acc) {

	}
	
	public static Account login(Account acc) {
		Statement stmt = null;
		
		String username = acc.getUsername();
		String password = acc.getPassword();
		
		String searchQuery=
	               "select * from Account where username='"
	                        + username
	                        + "' AND password='"
	                        + password
	                        + "'";
		System.out.println("Your username is " + username);
		System.out.println("Your password is " + password);
		System.out.println("Query: "+searchQuery);
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();
			
			if(!more) {
				System.out.println("Sorry, you are not a registered user! Please sign up first");
				acc.setValid(false);
			}else if(more){
				username = rs.getString("username");
				System.out.println("Welcome "+ username);
				acc.setUsername(username);
				acc.setValid(true);
			}
		}catch (Exception ex) {
			System.out.println("Log in failed: An Exception has occurred!" + ex);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch (Exception e) {}
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch (Exception e) {}
				stmt = null;
			}
			if(currentCon != null) {
				try {
					currentCon.close();
				}catch (Exception e) {}
				currentCon = null;
			}
		}
		return acc;
	}
}
