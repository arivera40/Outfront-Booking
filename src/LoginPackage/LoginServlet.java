package LoginPackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Account acc = new Account();
			acc.setUsername(request.getParameter("username"));
			acc.setPassword(request.getParameter("password"));
			
			acc = AccountDAO.login(acc);
			
			if(acc.isValid()) {
				int accountStatus = AccountDAO.checkStatus(acc);
				if(accountStatus == 1) {
					System.out.println("account status = " + accountStatus);
					//HttpSession session = request.getSession(true);
					request.setAttribute("acc_number", acc.getAccountNum());
					request.setAttribute("email", acc.getEmail());
					request.getRequestDispatcher("customerForm.jsp").forward(request, response);
					
					//response.sendRedirect("customerForm.jsp");
				}else if(accountStatus == 2) {
					System.out.println("account status = " + accountStatus);
					response.sendRedirect("customerIndex.jsp");
				}else {
					System.out.println("account status = " + accountStatus);
					response.sendRedirect("managerIndex.jsp");
				}
				
			/*	HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionAccount", acc.getUsername());
				response.sendRedirect("userLogged.jsp");	//logged-in page	*/
			}else {
				response.sendRedirect("invalidLogin.jsp"); //error page
			}
		}catch(Throwable theException) {
			System.out.println(theException);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		if(username.isEmpty() || password.isEmpty() || email.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			//out.println("<font color=red>Please fill all the fields</font>");
			rd.include(request, response);
		}else {
			try {
				Account acc = new Account();
				acc.setUsername(username);
				acc.setPassword(password);
				acc.setEmail(email);
				
				int status = AccountDAO.register(acc);
				if(status == 1) {
					response.sendRedirect("registerSuccess.jsp");	//logged-in page
				}else if(status == 2){
					response.sendRedirect("registerUsernameExists.jsp");	//this needs to be changed, 
				}else if(status == 3){
					response.sendRedirect("registerEmailExists.jsp");
				}else {
					response.sendRedirect("registerExists.jsp");
				}
			}catch(Throwable theException) {
				System.out.println(theException);
			}
		}
	}

}
