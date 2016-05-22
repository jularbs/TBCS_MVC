package tbcs.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tbcs.utility.SQLOperations;

@WebServlet("/clientmaintenance.html")
public class ClientMaintenance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clientID = Integer.parseInt("clientID");
		
		RequestDispatcher dispatcher = null;
		if(request.getParameter("action").equals("delete")) {
			ResultSet rs;
			
			try {
				SQLOperations.deleteClient(clientID);
				rs = SQLOperations.getAllClient();
				dispatcher = getServletContext().getRequestDispatcher("/listclient.jsp");
			}catch (SQLException e) {
				response.sendRedirect("errordisplay.jsp?code=1");
			}
			dispatcher.forward(request, response);		
		}
	}
}
