package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.FCI.SWE.Models.*;

import org.json.simple.JSONObject;

@Path("/")
@Produces("text/html")
public class PostService {
	
	@POST
	@Path("/WritePost")     //user post
	public String writePostService(@FormParam("currentUser") String currentUser,
								   @FormParam("postContent") String postContent,
								   @FormParam("privacy") String privacy,
								   @FormParam("feeling") String feeling)
	{
		JSONObject object = new JSONObject();
		int flag = UserPost.createUserPost(currentUser, postContent, privacy, feeling);
		if (flag == 0)
		{
			object.put("Status", "OK");
		}
		
		else {
			object.put("Status", "failed");
		}
		
		return object.toString();
	}
	
	

}
