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
public class FriendsController {
	
	/**
	 * Action function to render send friend request page this function will be executed using
	 * url like this /rest/sendFriendRequestService
	 * 
	 * @return send friend request page
	 */
	
	@GET
	@Path("/sendFriend")
	public Response sendFriendRequestPage()
	{
		return Response.ok(new Viewable("/jsp/sendfriendrequest")).build();
		
	}
	
	
	/**
	 * Action function to render accept friend request page this function will be executed using
	 * url like this /rest/acceptFriendRequestService
	 * 
	 * @return accept friend request page
	 */
	
	@GET
	@Path("/notifications")
	public Response acceptFriendPage()
	{
		return Response.ok(new Viewable("/jsp/notifications")).build();
		
	}
	
	/**
	 * Action function to response to send friend request. This function will act as a
	 * controller part, it will calls send friend request service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @return success or failed
	 * @throws ParseException 
	 */
	
	@POST
	@Path("/sendFriend")
	@Produces("text/html")
	public String sendFriendRequest(@Context HttpServletRequest request ,@FormParam("uname") String uname) throws ParseException {
		
		String serviceUrl = "http://localhost:8888/rest/sendFriendRequestService";
		HttpSession session = request.getSession(true);	
		String urlParameters = "uname=" + uname + "&currentUser=" + session.getAttribute("name");
		String retJson = Connection.connect(serviceUrl ,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return "Failed";
			if (object.get("Status").equals("Exists"))
				return "Fail. You are already friends";
				
			return "Your friend request successfully sent";
	}
	
	
	/**
	 * Action function to response to accept friend request. This function will act as a
	 * controller part, it will calls accept friend request service to check user data and get
	 * requests from datastore
	 * 
	 * @param uname
	 *            provided user name of friend request
	 * @return success or failed
	 * @throws ParseException 
	 */
	
	
	@POST
	@Path("/acceptFriendRequest")
	@Produces("text/html")
	public String acceptFriend(@Context HttpServletRequest request ,@FormParam("friendRequest") String uname) throws ParseException {
		String serviceUrl = "http://localhost:8888/rest/acceptFriendRequestService";
		HttpSession session = request.getSession(true);
		String urlParameters = "uname=" + uname + "&currentUser=" + session.getAttribute("name");
		String retJson = Connection.connect(serviceUrl ,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		if (object.get("Status").equals("Failed"))
				return "Failed";
				
			return "Success";
		
	}
	
	
}