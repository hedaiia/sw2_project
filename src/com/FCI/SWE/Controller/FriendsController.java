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
public class FriendsController {

	/**
	 * Action function to render send friend request page this function will be executed using
	 * url like this /rest/sendFriendRequestService
	 * 
	 * @return send friend request page
	 */
	
	@GET
	@Path("/sendFriend")
	public Response sendFriendRequestPage(){
		
		return Response.ok(new Viewable ("/jsp/sendfriendrequest")).build();
	}

	/**
	 * a controller that calls the send friend request service and provides it with the 
	 * suitable parameters 
	 * @param email 
	 * 				the friend email to add
	 * @return JSON status
	 */
	@POST
	@Path("/sendFriend")
	public Response sendFriendRequest(@FormParam("email") String email)
	{
		
		String serviceUrl = "http://localhost:8888/rest/sendFriendRequest";
		String urlParameters = "email=" + email;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return Response.ok("Your friend request successfully sent").build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok("failed").build();
	}

}
