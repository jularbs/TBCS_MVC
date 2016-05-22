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

import tbcs.model.ClientBean;
import tbcs.utility.SQLOperations;

@WebServlet("/updateclientprofile.html")
public class UpdateClientProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			ClientBean clientBean = (ClientBean) session.getAttribute("userLoggedin");
		
			clientBean.setName(request.getParameter("name"));
			clientBean.setAddressNo(request.getParameter("addressNo"));
			clientBean.setStreet(request.getParameter("street"));
			clientBean.setCity(request.getParameter("city"));
			clientBean.setZipCode(request.getParameter("zipCode"));
			clientBean.setContactFirstName(request.getParameter("contactFirstName"));
			clientBean.setContactMiddleName(request.getParameter("contactMiddleName"));
			clientBean.setContactLastName(request.getParameter("contactLastName"));
			clientBean.setAgency(Boolean.parseBoolean(request.getParameter("agency")));
			clientBean.setEmail(request.getParameter("email"));
			
			int recordsAffected = SQLOperations.updateClientProfile(clientBean, Integer.parseInt(request.getParameter("client_ID")));
			request.setAttribute("updateClientProfile", clientBean);
			RequestDispatcher dispatcher;
			
			if(recordsAffected > 0) {	
				dispatcher = getServletContext().getRequestDispatcher("/updateclientprofilestatus.jsp?success=true");
			} else {
				dispatcher = getServletContext().getRequestDispatcher("/updateclientprofilestatus.jsp?success=false");
			}
		dispatcher.forward(request, response);		
		} catch (SQLException e) {
			response.sendRedirect("errordisplay.jsp?code=1");
		}
		
	}
}
