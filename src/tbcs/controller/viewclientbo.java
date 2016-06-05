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

import tbcs.model.AdvertisingMaterialBean;
import tbcs.model.ClientBean;
import tbcs.model.broadcastOrderBean;
import tbcs.utility.SQLOperations;

/**
 * Servlet implementation class boview
 */
@WebServlet("/viewclientbo")
public class ViewClientBo extends HttpServlet {
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
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		ClientBean clientBean = (ClientBean) session.getAttribute("userLoggedin");
		int boID = Integer.parseInt(request.getParameter("id"));
		
		broadcastOrderBean bo = SQLOperations.viewBroadcastOrder(boID);		
		AdvertisingMaterialBean am = SQLOperations.getAdvertisingMaterial(bo.getMaterialID());
		ResultSet rs = SQLOperations.getSchedule(bo.getBoID());
		
		if(bo.getClientID() == clientBean.getId()){
			request.setAttribute("broadcastorder", bo);
			request.setAttribute("advertisingmaterial", am);
			request.setAttribute("schedules", rs);	
		}else{
			response.sendRedirect("/error.jsp");
		}
		
		dispatcher = getServletContext().getRequestDispatcher("/viewclientbo.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

}
