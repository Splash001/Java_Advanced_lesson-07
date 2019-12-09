  
package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.lviv.lgs.domain.Customer;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.service.CustomerService;
import ua.lviv.lgs.service.impl.CustomerServiceImpl;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerService customerService = CustomerServiceImpl.getCustomerService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String customerPassword = request.getParameter("password");

		if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !customerPassword.isEmpty()) {
			customerService
					.create(new Customer(firstName, lastName, email, UserRole.USER.toString(), customerPassword));
			HttpSession session = request.getSession(true);
			session.setAttribute("userEmail", email);
			session.setAttribute("userFirstName", firstName);
		}

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");

	}

}