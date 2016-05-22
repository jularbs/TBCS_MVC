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

import tbcs.model.ClientBean;
import tbcs.utility.SQLOperations;
import tbcs.utility.emailer;

/**
 * Servlet implementation class prepareEmployeeRegistration
 */
@WebServlet("/registeremployee")
public class prepareEmployeeRegistration extends HttpServlet {
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
		
		RequestDispatcher dispatcher = null;
		
		//GET POSITIONS AND RADIO STATIONS
		
			ResultSet rsRadioStation, rsPositions;
			
			rsRadioStation = SQLOperations.ddRadioStations();
			rsPositions = SQLOperations.ddPositions();
			
			request.setAttribute("radioStations", rsRadioStation);
			request.setAttribute("positions", rsPositions);

			dispatcher = getServletContext().getRequestDispatcher("/registeremployee.jsp");
			dispatcher.forward(request, response);
		
	}

}
