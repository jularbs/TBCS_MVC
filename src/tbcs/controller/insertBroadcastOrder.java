package tbcs.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.naming.java.javaURLContextFactory;

import tbcs.model.AdvertisingMaterialBean;
import tbcs.model.ClientBean;
import tbcs.model.broadcastOrderBean;
import tbcs.utility.SQLOperations;

/**
 * Servlet implementation class createBroadcastOrder
 */
@WebServlet("/insertbroadcastorder")
public class insertBroadcastOrder extends HttpServlet {
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
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ClientBean cl = (ClientBean) session.getAttribute("userLoggedin");
		AdvertisingMaterialBean am = new AdvertisingMaterialBean();
		broadcastOrderBean bo = new broadcastOrderBean();
		
		bo.setClientID(cl.getId());
		am.setClientID(cl.getId());
		String fileName = "";
 		 if (isMultipart) {
		        FileItemFactory factory = new DiskFileItemFactory();
		        ServletFileUpload upload = new ServletFileUpload(factory);
		        
			    try {
			        List items = upload.parseRequest(request);
			        Iterator iterator = items.iterator();
			        while (iterator.hasNext()) {
			            FileItem item = (FileItem) iterator.next();
			            if (item.isFormField()) { //if the item is a String
			            	switch(item.getFieldName()){
			            	case "name":
			            		am.setName(item.getString());
			            		break;
			            	case "product":
			            		am.setProduct(item.getString());
			            		break;
			            	case "version":
			            		am.setVersion(item.getString());
			            		break;
			            	case "tagline":
			            		am.setTagline(item.getString());
			            		break;
			            	case "materialID":
			            		am.setId(Integer.parseInt(item.getString()));
			            		bo.setMaterialID(Integer.parseInt(item.getString()));
			            		break;
			            	case "radioStation":
			            		bo.setStationID(Integer.parseInt(item.getString()));
			            		System.out.println(item.getString());
			            		break;
			            	case "spotsPerDay":
			            		bo.setSpotsPerDay(Integer.parseInt(item.getString()));
			            		break;
			            	case "startDate":
			            		bo.setStartDate(item.getString());
			            		break;
			            	case "endDate":
			            		bo.setEndDate(item.getString());
			            		break;
			            	case "startTime":
			            		bo.setStartTime(item.getString());
			            		break;
			            	case "endTime":
			            		bo.setEndTime(item.getString());
			            		break;
			            	case "mon":
			            		bo.setMon(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "tue":
			            		bo.setTue(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "wed":
			            		bo.setWed(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "thu":
			            		bo.setThu(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "fri":
			            		bo.setFri(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "sat":
			            		bo.setSat(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "sun":
			            		bo.setSun(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "additionalInstructions":
			            		bo.setAdditionalInstructions(item.getString());
			            	}
	
			            }else{ //if the item is a file
			            	fileName = item.getName();
			            	File pathFile = new File(getServletContext().getRealPath("/") + "\\advertisements\\" + fileName);
			            	am.setLink("\\advertisements\\" + fileName);
			            	item.write(pathFile);
			            	System.out.println(pathFile.getAbsolutePath());
			            }
			        }
			    } catch (FileUploadException e) {
			        e.printStackTrace();
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
		    }
 		 
 		 
 		 if(am.getId() == -1){//upload file if am.getId() == -1
 			 am.setId(SQLOperations.uploadAdvertisingMaterial(am));
 			 bo.setMaterialID(am.getId());
 			 SQLOperations.createBroadcastOrder(bo);
 			 response.sendRedirect("bosuccess.jsp");
 		 }else{//else create BO
 			SQLOperations.createBroadcastOrder(bo);
 			response.sendRedirect("bosuccess.jsp");
 		 }
 		 
 		 

	}
}
