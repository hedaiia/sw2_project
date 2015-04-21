package com.FCI.SWE.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	
	@GET
	@Path("/pageTimeline")
	public Response viewPageTimeline() {
		return Response.ok(new Viewable("/jsp/")).build();
	}	
	
	
	@POST
	@Path("/pageTimeline")
	@Produces("text/html")
	public String viewPageTimeline(@Context HttpServletRequest request, @FormParam("name") String pageName)
	{
		String serviceUrl = "http://localhost:8888/rest/pageTimelineService";
		try {
			URL url = new URL(serviceUrl);
			HttpSession session = request.getSession(true);	
			String urlParameters = "name=" + pageName;
				
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
				
			connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	
			while ((line = reader.readLine()) != null) {
				retJson += line;}
			
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			ArrayList<Map> pagePosts = (ArrayList) object.get("pagePosts");
			if (object.get("Status").equals("Ok"))
			{
				request.getSession(true).setAttribute("name", pageName);
			}
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
	}
	
	@GET
	@Path("/userTimeline")
	public Response viewUserTimeline() {
		return Response.ok(new Viewable("/jsp/")).build();
	}	
	
	
	@POST
	@Path("/userTimeline")
	@Produces("text/html")
	public String viewUserTimeline(@Context HttpServletRequest request)
	{
		String serviceUrl = "http://localhost:8888/rest/userTimelineService";
		try {
			URL url = new URL(serviceUrl);
			HttpSession session = request.getSession(true);	
			String urlParameters = "currentUser=" + session.getAttribute("name");
				
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
				
			connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	
			while ((line = reader.readLine()) != null) {
				retJson += line;}
			
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			ArrayList<Map> posts = (ArrayList) object.get("posts");
			if (object.get("Status").equals("Failed"))
			{
				return "Failed";
			}
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
	}
	
	
}


