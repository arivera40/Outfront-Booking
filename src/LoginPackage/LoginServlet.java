package LoginPackage;

import java.io.IOException;
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
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionAccount", acc.getUsername());
				response.sendRedirect("userLogged.jsp");	//logged-in page
			}else {
				response.sendRedirect("invalidLogin.jsp"); //error page
			}
		}catch(Throwable theException) {
			System.out.println(theException);
		}
	}

}
