package com.FCI.SWE.Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

@Path("/")
@Produces("text/html")
public class MessagesController {
	

	/**
	 * Action function to render send message page this function will be executed using
	 * url like this /rest/sendMessage
	 * 
	 * @return messages page
	 */
	
	
	@GET
	@Path("/sendMessage")
	public Response sendMessagePage()
	{
		return Response.ok(new Viewable("/jsp/Messages")).build();
		
	}
	
	/**
	 * Action function to render send group message page this function will be executed using
	 * url like this /rest/groupMessage
	 * 
	 * @return group messages page
	 */
	
	@GET
	@Path("/groupMessage")
	public Response SendGroupMessage() {
		return Response.ok(new Viewable("/jsp/GroupMessages")).build();

	}
	
	/**
	 * Action function for send message . This function will act as a
	 * controller part, it will calls send message service to get
	 * requests from datastore
	 * 
	 * @param uname
	 *            provided user name of receiver of the message
	 * @param message_text
	 *            provided message text        
	 * @return success or failed
	 * @throws ParseException 
	 */
	
	@POST
	@Path("/sendMessage")
	@Produces("text/html")
	public String sendMessage (@Context HttpServletRequest Message ,@FormParam("uname") String uname,@FormParam("message_text") String message_text) throws ParseException {
		String serviceUrl = "http://localhost:8888/rest/sendMessageService";
		HttpSession session = Message.getSession(true);
		String urlParameters = "message=" +message_text + "&uname=" + uname + "&currentUser=" + session.getAttribute("name");
		String retJson = Connection.connect(serviceUrl ,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return "Failed";
					
				
			return "Your message  successfully sent";
		
		}
	
	
	/**
	 * Action function for send group message . This function will act as a
	 * controller part, it will calls send group message service to get
	 * requests from datastore
	 * 
	 * @param mName
	 *            provided message name 
	 * @param message_text
	 *            provided message text 
	 * @param toUser
	 *            provided receivers of message      
	 * @return success or failed
	 * @throws ParseException 
	 */
	
	@POST
	@Path("/groupMessage")
	@Produces("text/html")
	public String sendGroupMessage(@Context HttpServletRequest request,
			@FormParam("messageName") String mName,
			@FormParam("receiver") String toUser,
			@FormParam("message") String message_text) throws ParseException {
		
		String serviceUrl = "http://localhost:8888/rest/sendGroupMessageService";
		String urlParameters = "meassageName=" + mName  +"&receiver=" + toUser + "&message=" + message_text;
		String retJson = Connection.connect(serviceUrl ,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return "Failed";
				
			return "Your group message successfully sent";

	}
	
}