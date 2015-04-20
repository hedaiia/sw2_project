package com.FCI.SWE.Services;


import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.PageEntity;
import com.FCI.SWE.Models.UserEntity;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")
public class UserServices {
	

		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
//		System.out.println(object.toString());
		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
		}
		return object.toString();
	}
	
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
			int flag = UserEntity.sendFriendRequest(uname , currentUser);
			if (flag == 0) {
				object.put("Status", "Failed");
	
			} 
			else if(flag == 1){
				object.put("Status", "Exist");
			}
			else {
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
			boolean flag = UserEntity.acceptFriendRequest(uname , currentUser);
			if (flag == false) {
				object.put("Status", "Failed");
	
			} else {
				object.put("Status", "OK");
				
			}
	
			return object.toString();
	
		}
		
		/**
		 make send message 
		 */
	
	
		@POST
		@Path("/sendMessageService")
		public String sendMessageService(@FormParam("uname") String uname,@FormParam("currentUser") String currentUser,@FormParam("message") String message_text) {
			JSONObject object = new JSONObject();
			int flag = UserEntity.sendMessage(uname , currentUser,message_text);
			if (flag == 0) {
				object.put("Status", "Failed");
	
			}
			else {
				object.put("Status", "success");
				
			}
	
			return object.toString();
	
		}
		
		@POST
		@Path("/sendGroupMessageService")
		public String sendGroupMessageService(@FormParam("messageName") String mName,
				@FormParam("receiver") String toUser,
				@FormParam("message") String message_text) {
			
			JSONObject object = new JSONObject();
			int flag = UserEntity.sendGroupMessage(mName, toUser, message_text);
			ArrayList<String> R = new ArrayList<String>();
			for (String receivers : toUser.split(",", 100)) {
				R.add(receivers);
			}
			R.clear();
			if (flag == 0) {
				object.put("Status", "success");
	
			}
			else {
				object.put("Status", "failed");
				
			}
	
			return object.toString();
	
		}
		@POST
		@Path("/CreatePage")
		public String CreatePageService(@FormParam("name") String pageName,@FormParam("Type") String pageType,@FormParam("pageDescription") String pageDescription,@FormParam("adminID") String adminID,@FormParam("pageOwner") String pageOwner,@FormParam("numberOfLikes") int numberOfLikes) {
			JSONObject object = new JSONObject();
			String flag = PageEntity.createPage(pageName , pageType,pageDescription,adminID,pageOwner,numberOfLikes);
			if (flag == "failed") {
				object.put("numberOfLikes", "Failed");

			}
			else {
				object.put("numberOfLikes", "0");
				
			}

			return object.toString();

		}

}