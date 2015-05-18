package com.FCI.SWE.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("/")
@Produces("text/html")
public class PostController {
	
	/**
	 * Action function to render write post for user page, this function will be executed
	 * using url like this /rest/WritePost
	 * 
	 * @return write user page
	 */
	@GET
	@Path("/WritePost")
	public Response createUserPost() {
		return Response.ok(new Viewable("/jsp/writeUserPost")).build();
	}	
	
	/**
	 * Action function to create user post This function will act as a
	 * controller part, it will calls write post service to get
	 * requests from datastore
	 * 
	 * @param postContent 
	 *            provided post content
	 * @param privacy 
	 *            provided privacy of post
	 * @param feeling 
	 *            provided feeling
	 * @return success or failed
	 * @throws ParseException 
	 */
	
	@POST
	@Path("/WritePost")
	@Produces("text/html")
	public String createUserPost(@Context HttpServletRequest request, 
				@FormParam("postContent") String postContent,
			    @FormParam("privacy") String privacy,
			    @FormParam("feeling") String feeling) throws ParseException {
		
		String serviceUrl = "http://localhost:8888/rest/writePostService";
		HttpSession session = request.getSession(true);	
			String urlParameters = "postContent=" + postContent + "&currentUser=" + session.getAttribute("name")
					+ "&privacy=" + privacy + "&feeling=" + feeling;
			String retJson = Connection.connect(serviceUrl ,urlParameters, "POST",
					"application/x-www-form-urlencoded;charset=UTF-8");			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return "Failed";
		
			return "Your post successfully created";
	}
	

}
