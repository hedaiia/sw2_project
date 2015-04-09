package com.FCI.SWE.Services;


import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.Models.GroupMessageEntity;
import com.FCI.SWE.Models.MessageEntity;
import com.FCI.SWE.Models.UserEntity;



@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class MessageServices {
	
	/**
	 make send message 
	 */

	@POST
	@Path("/sendMessage")
	public String sendMessageService(@FormParam("receiverEmail") String email,@FormParam("messageText") String message_text) {
		
		JSONObject object = new JSONObject();
		User currentUser = User.getCurrentActiveUser();
		String currentUserID = Long.toString(currentUser.getId());
		UserEntity receiver = new UserEntity();
		String receiverID = Long.toString(receiver.getUserIDByEmail(email));
		MessageEntity m = new MessageEntity(currentUserID, receiverID, message_text, false) ;
		
		JSONObject json = new JSONObject();
		if(m.sendMessage())
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		return json.toJSONString();
	}
	
	
	@POST
	@Path("/groupMessage")
	public String sendGroupMessage(@FormParam("messageText") String messageText,
			@FormParam("messageName") String messageName,
			@FormParam("receiverEmail") String receiverEmail)
	{
		MessageEntity m = new MessageEntity() ;
		JSONObject object = new JSONObject();
		GroupMessageEntity gm = new GroupMessageEntity();
		if(gm.sendGroupMessage(messageName , messageText, receiverEmail))
			object.put("Status", "OK");
		else
			object.put("Status", "Failed");
		
		return object.toJSONString();
		
		
	}
}
