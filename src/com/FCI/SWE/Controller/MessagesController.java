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


@Path("/")
@Produces("text/html")
public class MessagesController {
	
	@GET
	@Path("/sendMessage")
	public Response sendMessagePage()
	{
		return Response.ok(new Viewable("/jsp/Messages")).build();
		
	}
	
	@POST
	@Path("/sendMessage")
	@Produces("text/html")
	public Response sendMessage(@FormParam("messageText") String Message,
			@FormParam("receiverEmail") String receiverEmail) {
		
		String urlParameters = "messageText=" + Message + "&receiverEmail=" + receiverEmail;
		String retJson = Connection.connect("http://localhost:8888/rest/sendMessageService", 
				urlParameters,"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok("Your message successfully sent").build();
	}
	
	@GET
	@Path("/sendGroupMessage")
	public Response sendGroupMessage()
	{
		
		return Response.ok(new Viewable("/jsp/GroupMessages")).build();
	}
	
	
	
	@POST
	@Path("/groupMessage")
	@Produces("text/html")
	public Response sendGroupMessage(@FormParam("messageText") String messageText,
			@FormParam("messageName") String messageName,
			@FormParam("receiverEmail") String receiverEmail) 
	{			
		String urlParameters = "messageText=" + messageText + "&messageName=" + messageName + "&receiverEmail=" + receiverEmail;
		String retJson = Connection.connect(
				"http://localhost:8888/rest/groupMessage", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok("Your message successfully sent").build();
	}
}
