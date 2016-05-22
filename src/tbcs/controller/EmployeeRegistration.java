package tbcs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tbcs.model.EmployeeBean;
import tbcs.model.accountBean;
import tbcs.utility.SQLOperations;
import tbcs.utility.functions;

@WebServlet("/employeeregistration.html")
public class EmployeeRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String addressNo = request.getParameter("addressNo");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String email = request.getParameter("email");
		int position = Integer.parseInt(request.getParameter("position"));
		int station = Integer.parseInt(request.getParameter("radioStation"));
		String password = functions.createPassword(6);
		
		//fix stationID
		EmployeeBean employee = new EmployeeBean(firstName, middleName, lastName, gender, birthday, addressNo, street, city, zipCode, email);
		employee.setStationID(station);
		employee.setPositionID(position);
		accountBean account = new accountBean();
		
		account.setUsername(email);
		account.setPassword(password);
		
			if(SQLOperations.createAccount(account, SQLOperations.addEmployee(employee), 1)) {
				System.out.println("Added Successfully");
			}else {
				System.out.println("Failed");
				response.sendRedirect("errordisplay.jsp?success=false");
			}
			
			response.sendRedirect("clientstatus.jsp?success=true");
		}
	}


