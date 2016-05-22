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

@WebServlet("/broadcastOrderMaintenance.html")
public class broadcastOrderMaintenance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boID = Integer.parseInt(request.getParameter("boID"));
		
		RequestDispatcher dispatcher = null;
		if(request.getParameter("action").equals("delete")) {
			ResultSet rs;
			
			try {
				SQLOperations.deleteBroadcastOrder(boID);
				rs = SQLOperations.getAllBroadcastOrder();
				dispatcher = getServletContext().getRequestDispatcher("/listbroadcastorder.jsp");
			}catch (SQLException e) {
				response.sendRedirect("errordisplay.jsp?code=1");
			}	
		} else if(request.getParameter("action").equals("update")) {
			ResultSet rs;
			try {
				SQLOperations.updateBroadcastOrder(boID);
				rs = SQLOperations.getAllBroadcastOrder();
				request.setAttribute("listbroadcastorder", rs);
				dispatcher = getServletContext().getRequestDispatcher("/listbroadcastorder.jsp");
			} catch (SQLException e) {
				response.sendRedirect("errordisplay.jsp?code=1");
			}
		}
		dispatcher.forward(request, response);
	}
}
