package com.FCI.SWE.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.Command.AcceptRequestConcreteCommand;
import com.FCI.SWE.Command.ICommnad;
import com.FCI.SWE.Command.SeenGroupMessageConcreteCommand;
import com.FCI.SWE.Command.SeenMessageCommand;
import com.FCI.SWE.Models.FriendsEntity;
import com.FCI.SWE.Models.GroupMessageEntity;
import com.FCI.SWE.Models.MessageEntity;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class NotificationsService
{
	static public String response = ""; //the returned value to jsp page
	
	@POST
	@Path("/notificationsHandler")
	public String notificationsHandler(@FormParam("ID")String ID, @FormParam("type")String type)
	{
		JSONObject object = new JSONObject();
		String currentUserID = Long.toString(User.currentActiveUser.getId());
		
		ICommnad command = null ;
		
		if(type.equals("1"))
		{
			FriendsEntity f = new FriendsEntity(ID);
			command = new AcceptRequestConcreteCommand(f);
			response = "Your friend request is accepted";
		}
		
		else if(type.equals("2")){
			
			GroupMessageEntity gm = new GroupMessageEntity(ID);
			command = new SeenGroupMessageConcreteCommand(gm);		
			
		}
		
		else if(type.equals("3"))
		{
			MessageEntity m = new MessageEntity(ID);
			command = new SeenMessageCommand(m);
		}			
		
		if(command.excute())
		{
			object.put("Status", "OK");
			object.put("Response", response);
		}
			
		else
			object.put("Status", "Failed");
		return object.toJSONString();
	}	
	
	@POST
	@Path("/allNotifications")
	public String Notifications() 
	{
		
		JSONObject object = new JSONObject();
		FriendsEntity f = new FriendsEntity() ;
		MessageEntity m = new MessageEntity() ;
		Map <String, ArrayList> notifications = new HashMap();
		GroupMessageEntity gm = new GroupMessageEntity() ;
		object.put("notifications", notifications);
		System.out.println(object.toString());
		return object.toString();
	}

	
}
