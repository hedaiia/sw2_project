package com.FCI.SWE.Services;


import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.Models.FriendsEntity;
import com.FCI.SWE.Models.UserEntity;


@Path("/")
@Produces(MediaType.TEXT_PLAIN)

public class FriendsServices {
	
	/**
	 * send Friend Request Rest Service, this service will be called to make send request process
	 * also will check users data from datastore
	 * @param uname provided user name
	 * @param currentUser provided current user
	 * @return object in json format
	 */
	
	@POST
	@Path("/sendFriendRequest")
	public String sendFriendRequest(@FormParam("email") String email) {
		String currentUser = Long.toString(User.currentActiveUser.getId());
		String toUser = Long.toString(UserEntity.getUserIDByEmail(email));
		FriendsEntity friendship = new FriendsEntity(currentUser, toUser);
		JSONObject json = new JSONObject();

		if(toUser.equals("-1"))
			json.put("Status", "Failed");
			
			
		if(friendship.sendFriendRequest())
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		return json.toJSONString();
	}	

}
