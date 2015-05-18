package com.FCI.SWE.Controller;

import java.util.ArrayList;
import java.util.Map;
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
public class TimelineController {
	
	/**
	 * Action function to render view timeline for pages page, this function will be executed
	 * using url like this /rest/pageTimeline
	 * 
	 * @return timeline page
	 */
	
	@GET
	@Path("/pageTimeline")
	public Response viewPageTimeline() {
		return Response.ok(new Viewable("/jsp/")).build();
	}	
	
	/**
	 * Action function to view page timeline This function will act as a
	 * controller part, it will calls page Timeline service to get
	 * requests from datastore
	 * 
	 * @param name 
	 *            provided page name
	 * @return failed or null
	 * @throws ParseException 
	 */
	
	@POST
	@Path("/pageTimeline")
	@Produces("text/html")
	public String viewPageTimeline(@Context HttpServletRequest request, @FormParam("name") String pageName) throws ParseException
	{
		String serviceUrl = "http://localhost:8888/rest/pageTimelineService";
		String urlParameters = "name=" + pageName;
		String retJson = Connection.connect(serviceUrl ,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			ArrayList<Map> pagePosts = (ArrayList) object.get("pagePosts");
			if (object.get("Status").equals("Ok"))
			{
				request.getSession(true).setAttribute("name", pageName);
			}
			
			return null;
	}
	
	/**
	 * Action function to render view timeline for users page, this function will be executed
	 * using url like this /rest/userTimeline
	 * 
	 * @return timeline page
	 */
	
	@GET
	@Path("/userTimeline")
	public Response viewUserTimeline() {
		return Response.ok(new Viewable("/jsp/")).build();
	}	
	
	/**
	 * Action function to view user timeline This function will act as a
	 * controller part, it will calls user Timeline service to get
	 * requests from datastore
	 * 
	 * @return failed or null
	 * @throws ParseException 
	 */
	
	@POST
	@Path("/userTimeline")
	@Produces("text/html")
	public String viewUserTimeline(@Context HttpServletRequest request) throws ParseException
	{
		String serviceUrl = "http://localhost:8888/rest/userTimelineService";
		HttpSession session = request.getSession(true);	
		String urlParameters = "currentUser=" + session.getAttribute("name");
		String retJson = Connection.connect(serviceUrl ,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			ArrayList<Map> posts = (ArrayList) object.get("posts");
			if (object.get("Status").equals("Failed"))
			{
				return "Failed";
			}
			
		return null;
	}
	
	
}


