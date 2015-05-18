package com.FCI.SWE.Services;


import java.util.ArrayList;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.simple.JSONObject;
import com.FCI.SWE.Models.*;

@Path("/")
@Produces("text/html")
public class MessagesService {

	/**
	 * send message Rest Service, this service will be called to make sending message process
	 * also will check data from datastore
	 * @param uname provided user name
	 * @param currentUser provided current user
	 * @param message_text provided message_text
	 * @return object in json format
	 */
	
		@POST
		@Path("/sendMessageService")
		public String sendMessageService(@FormParam("uname") String uname,@FormParam("currentUser") String currentUser,@FormParam("message") String message_text) {
			JSONObject object = new JSONObject();
			int flag = MessagesEntity.sendMessage(uname , currentUser,message_text);
			if (flag == 0)
			{
				object.put("Status", "Failed");
			} else
				{
					object.put("Status", "success");
				}
	
			return object.toString();
		}
		
		/**
		 * send group message Rest Service, this service will be called to make sending group message process
		 * also will check data from datastore
		 * @param mName provided message name
		 * @param toUser provided receiver of message
		 * @param message_text provided message_text
		 * @return object in json format
		 */
		
		@POST
		@Path("/sendGroupMessageService")
		public String sendGroupMessageService(@FormParam("messageName") String mName,
				@FormParam("receiver") String toUser,
				@FormParam("message") String message_text) {
			
			JSONObject object = new JSONObject();
			int flag = MessagesEntity.sendGroupMessage(mName, toUser, message_text);
			ArrayList<String> R = new ArrayList<String>();
			for (String receivers : toUser.split(",", 100)) {
				R.add(receivers);
			}
			R.clear();
			if (flag == 0) 
			{
				object.put("Status", "success");
			} else
				{
					object.put("Status", "failed");
				}
	
			return object.toString();
		}
	
}