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
import javax.servlet.http.HttpSession;

import tbcs.model.EmployeeBean;
import tbcs.utility.SQLOperations;

@WebServlet("/viewemployeeprofile.html")
public class ViewEmployeeProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		//add session
		try{
			HttpSession session = request.getSession();
			EmployeeBean employeeBean = (EmployeeBean) session.getAttribute("userLoggedin");
			
			ResultSet rs = SQLOperations.viewEmployeeProfile(employeeBean.getId());
			request.setAttribute("viewEmployeeProfile", rs);
			dispatcher = getServletContext().getRequestDispatcher("/viewemployeeprofile.jsp");
					
		} catch(SQLException e){
			response.sendRedirect("errordisplay.jsp?code=1");
		}
			dispatcher.forward(request, response);
		}

}
