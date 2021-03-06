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

import tbcs.model.ClientBean;
import tbcs.utility.SQLOperations;

@WebServlet("/viewclientprofile.html")
public class ViewClientProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		//add session
		try{
			HttpSession session = request.getSession();
			ClientBean clientBean = (ClientBean) session.getAttribute("userLoggedin");
			
			ResultSet rs = SQLOperations.viewClientProfile(clientBean.getId());
			request.setAttribute("viewClientProfile", rs);
			dispatcher = getServletContext().getRequestDispatcher("/viewclientprofile.jsp");
					
		} catch(SQLException e){
			response.sendRedirect("errordisplay.jsp?code=1");
		}
		dispatcher.forward(request, response);
		}

}
