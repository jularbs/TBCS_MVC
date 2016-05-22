package tbcs.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tbcs.model.ClientBean;
import tbcs.utility.SQLOperations;

@WebServlet("/updateclientprofile.html")
public class UpdateClientProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ClientBean client = new ClientBean();
			
			client.setName(request.getParameter("name"));
			client.setAddressNo(request.getParameter("addressNo"));
			client.setStreet(request.getParameter("street"));
			client.setCity(request.getParameter("city"));
			client.setZipCode(request.getParameter("zipCode"));
			client.setContactFirstName(request.getParameter("contactFirstName"));
			client.setContactMiddleName(request.getParameter("contactMiddleName"));
			client.setContactLastName(request.getParameter("contactLastName"));
			client.setAgency(Boolean.parseBoolean(request.getParameter("agency")));
			client.setEmail(request.getParameter("email"));
			
			int recordsAffected = SQLOperations.updateClientProfile(client, Integer.parseInt(request.getParameter("client_ID")));
			request.setAttribute("updateClientProfile", client);
			RequestDispatcher dispatcher;
			
			if(recordsAffected > 0) {	
				dispatcher = getServletContext().getRequestDispatcher("/updateProfileStatus.jsp?success=true");
			} else {
				dispatcher = getServletContext().getRequestDispatcher("/updateProfileStatus.jsp?success=false");
			}
		dispatcher.forward(request, response);		
		} catch (SQLException e) {
			response.sendRedirect("errordisplay.jsp?code=1");
		}
		
	}
}
