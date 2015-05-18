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

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author hhmnw team
 * @version 3.3
 * @since 2014-02-12
 *
 */

@Path("/")
@Produces("text/html")
public class PageController {
	
	/**
	 * Action function to render create new Page page this function will be executed using
	 * url like this /rest/createPage
	 * 
	 * @return Page page
	 */
	
	@GET
	@Path("/createPage")
	public Response createPage()
	{
		return Response.ok(new Viewable("/jsp/Page")).build();
		
	}

	/**
	 * Action function for create new page . This function will act as a
	 * controller part, it will calls create page service to get
	 * requests from datastore
	 * 
	 * @param pageName
	 *            provided name of page
	 * @param pageType
	 *            provided type of page
	 * @param pageDescription
	 *            provided page description
	 * @param adminID
	 *            provided id of page owner (admin)
	 * @param numberOfLikes
	 * 			  provided number of likes and in the creation will be 0
	 * @return success or failed
	 * @throws ParseException 
	 */
	
	@POST
	@Path("/createPage")
	@Produces("text/html")
	public String createPage(@Context HttpServletRequest request ,@FormParam("name") String pageName,@FormParam("Type") String pageType,
			@FormParam("pageDescription") String pageDescription,@FormParam("adminID") String adminID,
			@FormParam("numberOfLikes") int numberOfLikes) throws ParseException {
		
		String serviceUrl = "http://localhost:8888/rest/createPageService";
		HttpSession session = request.getSession(true);	
		String urlParameters = "name=" + pageName + "&pageOwner=" + session.getAttribute("name")
					+ "&Type=" + pageType + "&pageDescription=" + pageDescription
					+ "&adminID=" + session.getAttribute("adminID") + "&numberOfLikes=" + numberOfLikes;
		String retJson = Connection.connect(serviceUrl ,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("numberOfLikes").equals("Failed"))
				return "Failed";
			
			return "Your page created";
	}
	
}