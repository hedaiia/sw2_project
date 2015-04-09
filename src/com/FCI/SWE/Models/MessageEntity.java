package com.FCI.SWE.Models;


import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class MessageEntity{   // message receiver
	
	
	private String currentUser;
	private String toUser;
	private String messageText;
	private boolean seen ;
	private String messageID ;
	
	public MessageEntity() {}
	public MessageEntity(String messageID) 
	{
		this.messageID = messageID ;
	}
	public String getMessageText()
	{
		return messageText ;
	}
	public MessageEntity(String currentUser, String toUser, String messageText, boolean seen)
	{
		this.currentUser = currentUser ;
		this.toUser = toUser ;
		this.messageText = messageText ;
		this.seen = seen ;
	}
	
	/**
	 * This method will be used to save friend rmessage  object in datastore
	 * 
	 * @return boolean if send message  is saved correctly or not
	 */
	
	public boolean sendMessage()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("messages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> l = pq.asList(FetchOptions.Builder.withDefaults());

		Entity message = new Entity("messages", l.size() + 1);
		
		message.setProperty("Sender" , currentUser);
		message.setProperty("Receiver" , toUser);
		message.setProperty("message_text" , messageText);
		message.setProperty("seen", seen);
		
		if(datastore.put(message).isComplete())
		{
			return true ;
		}
		else
		{
			return false ;
		}
	}
	public boolean readMessage()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("messages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if (Long.toString(entity.getKey().getId()).equals(messageID))
			{
				this.messageText = (String) entity.getProperty("message_text");
				entity.setProperty("seen", true);
				if(datastore.put(entity).isComplete())
				{
					return true ;
				}
				else
				{
					return false ;
				}
			}	
		}
		return false ;
	}
	
	
}
