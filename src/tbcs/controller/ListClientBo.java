package tbcs.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tbcs.model.ClientBean;
import tbcs.utility.SQLOperations;

/**
 * Servlet implementation class viewclientbo
 */
@WebServlet("/listclientbo")
public class ListClientBo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			ClientBean cl = (ClientBean) session.getAttribute("userLoggedin");
			ResultSet rs = SQLOperations.getClientBroadcastOrder(cl);
			
			request.setAttribute("listbroadcastorder", rs);
			getServletContext().getRequestDispatcher("/listclientbo.jsp").forward(request, response);
		
		// TODO Auto-generated method stub
	}

}
