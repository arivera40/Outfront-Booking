package LoginPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Customer customer = new Customer();
			customer.setFirstName(request.getParameter("first_name"));
			customer.setLastName(request.getParameter("last_name"));
			customer.setAddress(request.getParameter("address"));
			customer.setState(request.getParameter("state"));
			customer.setCity(request.getParameter("city"));
			customer.setZipCode(request.getParameter("zipcode"));
			customer.setPhone(request.getParameter("phone"));
			customer.setCardNum(request.getParameter("card_number"));
			System.out.println("does it execute this?");
			customer.setAccountNum(Integer.parseInt(request.getParameter("acc_number")));
			customer.setEmail(request.getParameter("email"));
			System.out.println("how about these");
			System.out.println("customer account number is" + customer.getAccountNum());
			
			System.out.println("acc_number = " + customer.getAccountNum());
			AccountDAO.insertCustomerData(customer);
			response.sendRedirect("customerIndex.jsp");
		}catch(Throwable theException) {
			System.out.println(theException);
		}
	}

}
