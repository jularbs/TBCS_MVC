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

import tbcs.utility.emailer;
import tbcs.model.ClientBean;
import tbcs.utility.SQLOperations;

@WebServlet("/updateclient.html")
public class UpdateClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clientID = Integer.parseInt(request.getParameter("clientID"));
		
		RequestDispatcher dispatcher = null;
		emailer clientEmail = new emailer();
		if(request.getParameter("action").equals("update")) {
			ResultSet rs;
			ClientBean getEmailRs;
			try {
				SQLOperations.updateClient(clientID);
				rs = SQLOperations.getAllClient();
				request.setAttribute("listclient", rs);
				dispatcher = getServletContext().getRequestDispatcher("/listclient.jsp");
				getEmailRs = SQLOperations.searchClient(clientID);
				clientEmail.sendEmail(getEmailRs.getEmail());
			} catch (SQLException e) {
				response.sendRedirect("errordisplay.jsp?code=1");
			}
			dispatcher.forward(request, response);
		}
	}

}
