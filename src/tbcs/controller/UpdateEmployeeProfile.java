package tbcs.controller;

import java.io.IOException;
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

@WebServlet("/updateemployeeprofile.html")
public class UpdateEmployeeProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			EmployeeBean employeeBean = (EmployeeBean) session.getAttribute("userLoggedin");
			
			employeeBean.setFirstName(request.getParameter("firstName"));
			employeeBean.setMiddleName(request.getParameter("middleName"));
			employeeBean.setLastName(request.getParameter("lastName"));
			employeeBean.setGender(request.getParameter("gender"));
			employeeBean.setBirthday(request.getParameter("birthday"));
			employeeBean.setAddressNo(request.getParameter("addressNo"));
			employeeBean.setStreet(request.getParameter("street"));
			employeeBean.setCity(request.getParameter("city"));
			employeeBean.setZipCode(request.getParameter("zipCode"));
			employeeBean.setEmail(request.getParameter("email"));
			
			int recordsAffected = SQLOperations.updateEmployeeProfile(employeeBean, Integer.parseInt(request.getParameter("employee_ID")));
			request.setAttribute("updateEmployeeProfile", employeeBean);
			RequestDispatcher dispatcher;
			
			if(recordsAffected > 0) {	
				dispatcher = getServletContext().getRequestDispatcher("/updateemployeeprofilestatus.jsp?success=true");
			} else {
				dispatcher = getServletContext().getRequestDispatcher("/updateemployeeprofilestatus.jsp?success=false");
			}
		dispatcher.forward(request, response);		
		} catch (SQLException e) {
			response.sendRedirect("errordisplay.jsp?code=1");
		}
		
	}
}
