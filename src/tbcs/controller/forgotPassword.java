package tbcs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tbcs.model.accountBean;
import tbcs.utility.SQLOperations;
import tbcs.utility.emailer;
import tbcs.utility.functions;

/**
 * Servlet implementation class forgotPassword
 */
@WebServlet("/forgotpassword.jsp")
public class forgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//check if email exists first
		String email = request.getParameter("email");
		
		int accountID = SQLOperations.getAccountID(email);
		System.out.println("forgot password for email: " + email +"with account ID: " + accountID);
		
		if(accountID < 0){
			response.sendRedirect("forgotmypassword.jsp?exist=false");
		}else{
			//update account
			String newpass = SQLOperations.forgotPassword(accountID);
			
			//email user
			String emailMessage = "Your new Password has been created for your account. New password: " + newpass; // fix;
			emailer.sendEmail(email, "Forgot Password: Traffic Billing Collection System", emailMessage);
			response.sendRedirect("forgotmypassword.jsp?success=true");
		}

	}

}
