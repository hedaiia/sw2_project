package com.FCI.SWE.Services;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.PagePost;
import com.FCI.SWE.Models.UserPost;


@Path("/")
@Produces("text/html")
public class TimelineService {
	
	@POST
	@Path("/pageTimeline")
	public String pageTimelineService(@FormParam("currentUser") String currentUser,
			@FormParam("name") String pageName)
	{
		JSONObject object = new JSONObject();
		PagePost pp = new PagePost();
		ArrayList<Map> pagePosts = pp.viewTimeLine(currentUser , pageName);
		object.put("Status", "Ok");
		object.put("pageView", pagePosts);
		return object.toString();
	}
	
	@POST
	@Path("/userTimeline")
	public String userTimelineService(@FormParam("currentUser") String currentUser)
	{
		UserPost up = new UserPost() ;
		ArrayList<Map> myposts = up.viewTimeLine(currentUser);
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		object.put("posts", myposts);
		return object.toString();
	}
						

}
