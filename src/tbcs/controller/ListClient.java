package tbcs.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tbcs.utility.SQLOperations;

@WebServlet("/viewclient.html")
public class ListClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ResultSet rs = SQLOperations.getAllClient();
			System.out.println("Successful Connection");
			
			request.setAttribute("listclient", rs);
			getServletContext().getRequestDispatcher("/listclient.jsp").forward(request, response);
		}catch (NullPointerException npe) {
			System.err.println("Invalid Connection");
			response.sendRedirect("errodisplay.jsp");
		}catch (Exception e) {
			System.err.println("Exception - " + e.getMessage());
			response.sendRedirect("errodisplay.jsp");
		}
	}

}
