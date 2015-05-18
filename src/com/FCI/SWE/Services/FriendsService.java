package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.simple.JSONObject;
import com.FCI.SWE.Models.*;

@Path("/")
@Produces("text/html")
public class FriendsService {
	
		/**
		 * send Friend Request Rest Service, this service will be called to make send request process
		 * also will check users data from datastore
		 * @param uname provided user name
		 * @param currentUser provided current user
		 * @return object in json format
		 */
		
		@POST
		@Path("/sendFriendRequestService")
		public String sendFriendRequestService(@FormParam("uname") String uname,@FormParam("currentUser") String currentUser) {
			JSONObject object = new JSONObject();
			int flag = FriendsEntity.sendFriendRequest(uname , currentUser);
			if (flag == 0)
			{
				object.put("Status", "Failed");
			} else if(flag == 1)
				{
					object.put("Status", "Exist");
				} else
					{
						object.put("Status", "OK");
					}
			
			return object.toString();
		}
		
		/**
		 * accept Friend Request Rest Service, this service will be called to make accept request process
		 * also will check users data from datastore
		 * @param uname provided user name
		 * @param currentUser provided current user
		 * @return object in json format
		 */
		
		@POST
		@Path("/acceptFriendRequestService")
		public String accpetFriendService(@FormParam("uname") String uname,@FormParam("currentUser") String currentUser) {
			JSONObject object = new JSONObject();
			boolean flag = FriendsEntity.acceptFriendRequest(uname , currentUser);
			if (flag == false) 
			{
				object.put("Status", "Failed");
			} else 
				{
					object.put("Status", "OK");
				}
	
			return object.toString();
		}
		
}