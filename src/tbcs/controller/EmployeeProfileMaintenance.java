package tbcs.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tbcs.model.EmployeeBean;
import tbcs.utility.SQLOperations;

@WebServlet("/employeeprofilemaintenance.html")
public class EmployeeProfileMaintenance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int employeeID = Integer.parseInt(request.getParameter("employeeID"));
		
		RequestDispatcher dispatcher = null;
		
		if (request.getParameter("action").equals("update")) {
			EmployeeBean employee = new EmployeeBean();
			
			try {
				employee = SQLOperations.searchEmployeeProfile(employeeID);
				
				request.setAttribute("employee", employee);
				request.setAttribute("employeeID", employeeID);
				
				dispatcher = getServletContext().getRequestDispatcher("/updateemployeeprofile.jsp?action=update");
			} catch (SQLException e){
				response.sendRedirect("errordisplay.jsp?code=1");
			}
			
			dispatcher.forward(request, response);
		}
	
	}

}
