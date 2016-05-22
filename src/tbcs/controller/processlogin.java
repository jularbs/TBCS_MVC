package tbcs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tbcs.model.ClientBean;
import tbcs.model.EmployeeBean;
import tbcs.model.accountBean;
import tbcs.utility.SQLOperations;

/**
 * Servlet implementation class processlogin
 */
@WebServlet("/processlogin.html")
public class processlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		//get parameters and set to accountBean
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		accountBean acc = new accountBean();
		
		acc.setUsername(username);
		acc.setPassword(password);
		
		//get accountType row to know what table to look
		acc = SQLOperations.authorizeAccount(acc);
		
		//case accountType
		switch(acc.getAccountType()){
		case -1:
			response.sendRedirect("index.jsp?attempt=fail");
			break;
		//if 0 redirect to admin
		case 0:
			response.sendRedirect("admin.jsp");
			session.setAttribute("userType", "Admin");
			session.setAttribute("userLoggedin", "Admin");
			break;	
		case 1://if 1 query employee table look for accountID
			   //store employeebean to session
			EmployeeBean employee = SQLOperations.loginEmployee(acc);
			System.out.println(employee.getFirstName());
			//fix user levels
			session.setAttribute("userLoggedin", employee);	
			session.setAttribute("userType", employee.getPositionName());
			switch(employee.getPositionName()){
			case "Account Executive":
				//redirect to account executive view
				break;
			case "Station Traffic":
				//redirect to station Traffic view
				break;
			case "Studio Technician":
				//redirect to studio technician view
				break;
			case "Billing Department":
				//redirect to billing department view
				break;
			}
			response.sendRedirect("employeeindex.jsp");
			break;
		case 2://if 2 query client table look for accountID
			   //store client bean to session
			ClientBean client = SQLOperations.loginClient(acc);
			
			//fix user levels
			session.setAttribute("userLoggedin", client);	
			response.sendRedirect("clientindex.jsp");
			break;		
		}
		
		
		
		
		

	}

}
