package tbcs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ParseConversionEvent;

import tbcs.model.EmployeeBean;
import tbcs.model.accountBean;
import tbcs.utility.emailer;
import tbcs.utility.SQLOperations;
import tbcs.utility.functions;

/**
 * Servlet implementation class createAccount
 */
@WebServlet("/createaccount.html")
public class createAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("ID"));
		String email = request.getParameter("email");
		int type = Integer.parseInt(request.getParameter("type"));
		
		accountBean account = new accountBean();
		System.out.println("id: " + id + "request: " + request.getParameter("ID"));
		account.setUsername(email);
		account.setPassword(functions.createPassword(6));
		
		if(SQLOperations.createAccount(account, id, type)) {
			System.out.println("Added Successfully");
			emailer.sendEmail(email, account);
			response.sendRedirect("accountcreation.jsp?success=true");
		}else {
			System.out.println("Failed");
			response.sendRedirect("errordisplay.jsp?success=false");
		}
	}

}
