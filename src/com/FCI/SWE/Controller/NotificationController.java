package com.FCI.SWE.Controller;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("/")
@Produces("text/html")
public class NotificationController 
{
	@GET
	@Path("/notificationsHandler/{ID}/{type}")
	 
	public Response notificationsHandler(@PathParam("ID") String ID, @PathParam("type") String type)
	{
		
		String serviceUrl = "http://localhost:8888/rest/notificationsHandler";
		String urlParameters = "ID=" + ID + "&type=" + type ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser ();
		Object obj ;
		try
		{
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject)obj ;
			if(object.get("Status").equals("OK"))
					return Response.ok(object.get("Response")).build();
			
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GET
	@Path("/allNotifications")
	@Produces("text/html")
	
	public Response allNotifications()
	{
		
		String retJson = Connection.connect("http://localhost:8888/rest/allNotifications" , ""
				,"POST" + "", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			Map notifications = (Map)object.get("notifications");
			return Response.ok(new Viewable("/jsp/notifications", notifications)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	

}
