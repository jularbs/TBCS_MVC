package tbcs.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tbcs.model.AdvertisingMaterialBean;
import tbcs.model.ClientBean;
import tbcs.model.boScheduleBean;
import tbcs.model.broadcastOrderBean;
import tbcs.utility.SQLOperations;

/**
 * Servlet implementation class insertBroadcastOrder
 */
@WebServlet("/insertBroadcastOrder")
public class insertBroadcastOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//INCOMPLETE: EMAIL CLIENT WHEN BROADCAST ORDER INSERTED
		//			  EMAIL ACCOUNT EXECUTIVE IF THERE IS A PENDING BO
		
		int BroadcastID = -1;
		String filename = "";
		
		broadcastOrderBean boBean = new broadcastOrderBean();
		boScheduleBean scheduleBean = new boScheduleBean();
		AdvertisingMaterialBean amBean = new AdvertisingMaterialBean();
		
		HttpSession session = request.getSession();
		
		ClientBean clientBean = (ClientBean) session.getAttribute("userLoggedin");
		boBean.setClientID(clientBean.getId());
		amBean.setClientID(clientBean.getId());
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
 		 if (isMultipart) {
		        FileItemFactory factory = new DiskFileItemFactory();
		        ServletFileUpload upload = new ServletFileUpload(factory);
		        
			    try {
			        List items = upload.parseRequest(request);
			        Iterator iterator = items.iterator();
			        System.out.println("I'm here");
			        while (iterator.hasNext()) {
			    
			            FileItem item = (FileItem) iterator.next();
			            if (item.isFormField()) { //if the item is a String
			            	System.out.println();
			            	switch(item.getFieldName()){
			            	case "name":
			            		amBean.setName(item.getString());
			            		break;
			            	case "product":
			            		amBean.setProduct(item.getString());
			            		break;
			            	case "version":
			            		amBean.setVersion(item.getString());
			            		break;
			            	case "tagline":
			            		amBean.setTagline(item.getString());
			            		break;
			            	
			            	// getting ID of old advertising material
			            	case "materialID":
			            		boBean.setMaterialID(Integer.parseInt(item.getString()));
			            		break;
			            	//end of advertisement material
			            	
			            	case "startDate":
			            		boBean.setStartDate(item.getString());
			            		break;
			            	case "endDate":
			            		boBean.setEndDate(item.getString());
			            		break;
			            	case "additionalInstructions":
			            		boBean.setAdditionalInstruction(item.getString());
				            	
			            		//get id
				            	//end of broadcastorder
			            		BroadcastID = SQLOperations.createBroadcastOrder(boBean);
			            		//auditTrail: "Broadcast Order with ID " + BroadcastID + " inserted in database";
			            		scheduleBean.setBoID(BroadcastID); // setting BOid of schedules to inserted BO
			            		break;
			            		
			            	
			            	//insertBroadcastorder
			            	case "radioStation[]":
			            		scheduleBean.setStationID(Integer.parseInt(item.getString()));
			            		break;
			            	case "startTime[]":
			            		scheduleBean.setStartTime(item.getString());
			            		break;
			            	case "endTime[]":
			            		scheduleBean.setEndTime(item.getString());
			            		break;
			            	case "spotsPerDay[]":
			            		scheduleBean.setSpotsPerDay(Integer.parseInt(item.getString()));
			            		break;
			            	case "mon[]":
			            		System.out.println("Iterated Monday");
			            		scheduleBean.setMon(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "tue[]":
			            		System.out.println("Iterated Tuesday");
			            		scheduleBean.setTue(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "wed[]":
			            		System.out.println("Iterated Wednesday");
			            		scheduleBean.setWed(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "thu[]":
			            		System.out.println("Iterated Thursday");
			            		scheduleBean.setThu(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "fri[]":
			            		System.out.println("Iterated Friday");
			            		scheduleBean.setFri(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "sat[]":
			            		System.out.println("Iterated Saturday");
			            		scheduleBean.setSat(Boolean.parseBoolean(item.getString()));
			            		break;
			            	case "sun[]":
			            		System.out.println("Iterated Sunday");  
			            		scheduleBean.setSun(Boolean.parseBoolean(item.getString()));
			            		break; 
			            	case "buffer":
			            		SQLOperations.insertBroadcastSchedule(scheduleBean);
			            		//auditTrail: "Schedule inserted to Broadcast Order ID: " + BroadcastID;
			            		
			            		//instantiate new schedule bean with id of broadcast ID
			            		scheduleBean = new boScheduleBean();
			            		scheduleBean.setBoID(BroadcastID);
			            		break;
			            	//end of broadcastschedule
			            	}
			            	
	
			            }else{ //if the item is a file
			            	if(item.getName() != ""){
			            		filename = item.getName();
			            		File pathFile = new File(getServletContext().getRealPath("/") + "\\advertisements\\" + filename);
			            		amBean.setLink("\\advertisements\\" + filename);
			            		item.write(pathFile);			            		
			            		//auditTrail "File saved at" + pathFile.getAbsolutePath();
			            		amBean.setId(SQLOperations.uploadAdvertisingMaterial(amBean));
			            		boBean.setMaterialID(amBean.getId());
			            		//auditTrail "Advertisement Material " + filename + "inserted in database";
			            	}
			            }
			        }
			        }catch (Exception e) {
			        System.out.println("Error at testBo: " + e.getMessage());
			    }
		    }

	}

}
