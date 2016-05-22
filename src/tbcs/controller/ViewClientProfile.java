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

/**
 * Servlet implementation class ViewClientProfile
 */
@WebServlet("/viewclientprofile.html")
public class ViewClientProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clientID = Integer.parseInt(request.getParameter("clientID"));
		RequestDispatcher dispatcher = null;
		
		//add session
		try{
			ResultSet rs = SQLOperations.viewClientProfile(clientID);

			request.setAttribute("viewClientProfile", rs);
			dispatcher = getServletContext().getRequestDispatcher("/viewclientprofile.jsp");
					
		} catch(SQLException e){
			response.sendRedirect("errordisplay.jsp?code=1");
		}
			dispatcher.forward(request, response);
		}

}
