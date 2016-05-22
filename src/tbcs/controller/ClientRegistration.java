package tbcs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tbcs.utility.emailer;
import tbcs.model.ClientBean;
import tbcs.model.ContactDetails;
import tbcs.utility.SQLOperations;

@WebServlet("/clientregistration.html")
public class ClientRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String addressNo = request.getParameter("addressNo");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String contactFirstName = request.getParameter("contactFirstName");
		String contactMiddleName = request.getParameter("contactMiddleName");
		String contactLastName = request.getParameter("contactLastName");
		boolean agency = Boolean.parseBoolean(request.getParameter("agency"));
		System.out.println("agency: " + agency);
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		String contactNumber = request.getParameter("contactno");
		int clientID = 0 ;
		
		ClientBean client = new ClientBean(name, addressNo, street, city, zipCode, contactFirstName, contactMiddleName, contactLastName, agency, email, status);
	
		HttpSession userSession = request.getSession();
		emailer clientEmail = new emailer();
		
		//check if client email exists
		if(SQLOperations.checkIfClientExists(client)){ //add client if email not yet registered
			if(SQLOperations.addClient(client)) {
				clientID = SQLOperations.searchClientByEmail(email);
				ContactDetails contactDetails = new ContactDetails(clientID, contactNumber);
				SQLOperations.addContact(contactDetails);
				System.out.println("Added Successfully with Contacts");
				userSession.setAttribute("newclient", client);
				response.sendRedirect("clientstatus.jsp?success=true");
			}else {
				System.out.println("Failed");
				response.sendRedirect("errordisplay.jsp?success=false");
			}
		}else{ // redirect to redirection notifying that email is registered
			System.out.println("Email is already registered: " + client.getEmail());
			response.sendRedirect("registerclient.jsp?error=existing");
		}

	}

}
