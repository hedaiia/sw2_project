package com.FCI.SWE.Models;

import java.util.List;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class MessagesEntity {
	
	/**
	 * This method will be used to save friend message  object in datastore
	 * 
	 * @return 1 or 0 if sending message is saved correctly or not
	 */
	
	public static int sendMessage (String toUser, String currentUser,String message_text) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		boolean flag = false;
		//check if receiver is user in datastore
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable())
		{
			if (entity.getProperty("name").toString().equals(toUser))
			{
					flag = true;
					break;
			}
		}
		if (!flag)
			return 0;
		gaeQuery = new Query("Messages");
		pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity Message = new Entity("Messages", list.size() + 1);
		Message.setProperty("currentUser", currentUser);
		Message.setProperty("toUser", toUser);
		Message.setProperty("message_text",  message_text);
		Message.setProperty("status",  1);
		datastore.put(Message);	
			return 1;
	}
	
	/**
	 * This method will be used to save group message object in datastore
	 * 
	 * @return 0 if sending group message is saved correctly 
	 */
		
	public static int sendGroupMessage(String mName, String toUser,String message_text) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("GroupMessages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity Message = new Entity("GroupMessages", list.size() + 1);
		Message.setProperty("currentUser", User.getCurrentActiveUser());
		Message.setProperty("receiver", toUser);
		Message.setProperty("messageName",  mName);
		Message.setProperty("message", message_text);
		datastore.put(Message);	
			return 0;
	}
		
}