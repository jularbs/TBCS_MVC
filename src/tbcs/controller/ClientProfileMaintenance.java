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

/**
 * Servlet implementation class ClientProfileMaintenance
 */
@WebServlet("/clientprofilemaintenance.html")
public class ClientProfileMaintenance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clientID = Integer.parseInt(request.getParameter("clientID"));
		
		RequestDispatcher dispatcher = null;
		
		if (request.getParameter("action").equals("update")) {
			ClientBean client = new ClientBean();
			
			try {
				client = SQLOperations.searchClientProfile(clientID);
				
				request.setAttribute("client", client);
				request.setAttribute("clientID", clientID);
				
				dispatcher = getServletContext().getRequestDispatcher("/updateclientprofile.jsp?action=update");
			} catch (SQLException e){
				response.sendRedirect("errordisplay.jsp?code=1");
			}
			
			dispatcher.forward(request, response);
		}
	
	}

}
