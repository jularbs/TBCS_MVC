package tbcs.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tbcs.model.ClientBean;
import tbcs.utility.SQLOperations;

/**
 * Servlet implementation class prepareBroadcastOrder
 */
@WebServlet("/createbroadcastorder")
public class prepareBroadcastOrderForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			HttpSession session = request.getSession();
			ClientBean clientBean = (ClientBean) session.getAttribute("userLoggedin");

			ResultSet rsRadioStation, rsAdMaterial;	
			rsRadioStation = SQLOperations.ddRadioStations();
			rsAdMaterial = SQLOperations.ddAdvertisingMaterial(clientBean.getId());
			request.setAttribute("radioStations", rsRadioStation);
			request.setAttribute("adMaterials", rsAdMaterial);
			dispatcher = getServletContext().getRequestDispatcher("/createbroadcastorder.jsp");
			dispatcher.forward(request, response);
	}

}
