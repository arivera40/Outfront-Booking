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
				ps.setString(3, "0");
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
				acc.setAccountNum(rs.getInt("acc_number"));
				acc.setEmail(rs.getString("email"));
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
	
	public static int checkStatus(Account acc) {
		Statement stmt = null;
		System.out.println("Does it enter checkStatus method?");
		int accountNum = acc.getAccountNum();
		
		String managerQuery ="select * from Manager where acc_number='"
                + accountNum
                + "'";
		String customerQuery = "select * from Customer where acc_number='"
                + accountNum
                + "'";
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery(managerQuery);
			boolean more = rs.next();
			
			if(!more) {
				rs2 = stmt.executeQuery(customerQuery);
				boolean more2 = rs2.next();
				currentCon.close();
				if(!more2) {	//return 1 - customer has not filled out customer information yet
					return 1;
					//foward to customerForm page
				}else {		//return 2 - customer has already filled customer information
					return 2;
					//foward to customerBooking page
				}
			}else{	//return 3 - account is a manager
				currentCon.close();
				return 3;
				//Manager account 
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
		return 0;
	}
	
	public static void insertCustomerData(Customer customer) {
		Statement stmt = null;
		System.out.println("Does it enter insertCustomerData method");
		String createCustomerQuery = "insert into Customer values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			
				ps = currentCon.prepareStatement(createCustomerQuery);
				ps.setInt(1, customer.getAccountNum());
				ps.setString(2, customer.getFirstName());
				ps.setString(3, customer.getLastName());
				ps.setString(4, customer.getAddress());
				ps.setString(5, customer.getState());
				ps.setString(6, customer.getCity());
				ps.setString(7, customer.getZipCode());
				ps.setString(8, customer.getPhone());
				ps.setString(9, customer.getEmail());
				ps.setString(10, customer.getCardNum());
				
				
				ps.executeUpdate();
				System.out.println("Successfully inserted");
				ps.close();
				currentCon.close();
				return;
		}catch (Exception ex) {
			System.out.println("Log in failed: An Exception has occurred!" + ex);
			return;
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
}
