package LoginPackage;
import java.text.*;
import java.util.*;
import java.sql.*;

public class AccountDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static ResultSet rs2 = null;
	static PreparedStatement ps;
	
	public static int register(Account acc) {
		Statement stmt = null;
		Statement stmt2 = null;
		String username = acc.getUsername();
		String password = acc.getPassword();
		String email = acc.getEmail();
		String insertQuery = "insert into Account values(? , ?, ?, ?)";
		String searchQueryUsername = "select * from Account where username='"
                + username
                + "'";
		String searchQueryEmail = "select * from Account where email='"
                + email
                + "'";
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt2 = currentCon.createStatement();
			rs = stmt.executeQuery(searchQueryUsername);
			rs2 = stmt2.executeQuery(searchQueryEmail);
			
			boolean usernameExists = rs.next();
			boolean emailExists = rs2.next();
			
			if(!usernameExists && !emailExists) {
				ps = currentCon.prepareStatement(insertQuery);
				ps.setString(1, email);
				ps.setString(2, password);
				ps.setString(3, "5");	//fix this
				ps.setString(4, username);
				
				ps.executeUpdate();
				System.out.println("Successfully inserted");
				ps.close();
				currentCon.close();
				return 1;
			}else if(usernameExists && !emailExists) {
				System.out.println("Username already in use!");
				currentCon.close();
				return 2;
			}else if(!usernameExists && emailExists){
				System.out.println("Email already associated to another account!");
				currentCon.close();
				return 3;
			}else {
				System.out.println("Account already exists!");
				currentCon.close();
				return 4;
			}
		}catch (Exception ex) {
			System.out.println("Log in failed: An Exception has occurred!" + ex);
			return 0;
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
