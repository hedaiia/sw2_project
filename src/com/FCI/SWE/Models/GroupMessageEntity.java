package com.FCI.SWE.Models;


import java.util.List;
import java.util.Scanner;
import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class GroupMessageEntity {    //group message receiver
	
	private String currentUser;
	private String toUsers;
	private String messageText;
	private boolean seen ;
	private String groupMessageID ;
	
	public GroupMessageEntity() {}
	public GroupMessageEntity(String groupMessageID) 
	{
		this.groupMessageID = groupMessageID ;
	}
	
	public String getMessageText()
	{
		return messageText ;
	}
	
	public GroupMessageEntity(String currentUser, String toUsers, String messageText, boolean seen)
	{
		this.currentUser = currentUser ;
		this.toUsers = toUsers ;
		this.messageText = messageText ;
		this.seen = seen ;
	}
	
	
	public boolean sendGroupMessage(String messageName , String messageText , String receiverEmail)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("groupName");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> l = pq.asList(FetchOptions.Builder.withDefaults());
		String Emails="";
		boolean flag = false;
 
		for (Entity entity : pq.asIterable()) 
		{
			if (((String)entity.getProperty("messageName")).equals(messageName))
			{				
				Emails = (String) entity.getProperty("Emails");
				flag = true;
				break;
			}
		}
		if (flag == false)
			return false;
 
		User currentUser = User.getCurrentActiveUser();
		String currentUserEmail = currentUser.getEmail();
 
		seen = false; 
		Scanner s = new Scanner(Emails);
 
		s.useDelimiter(",");
		datastore = DatastoreServiceFactory.getDatastoreService();
		gaeQuery = new Query("groupMessages");
		pq = datastore.prepare(gaeQuery);		
		List<Entity> l2 = pq.asList(FetchOptions.Builder.withDefaults());
		int i = l2.size();
		while (s.hasNext())
		{
			String ReceiverEmail = s.next();
			if(ReceiverEmail.equals(currentUserEmail))
			{
				break ;
			}
			i++;
			datastore = DatastoreServiceFactory.getDatastoreService();
			gaeQuery = new Query("groupMessages");
			pq = datastore.prepare(gaeQuery);
 
			Entity messages = new Entity("groupMessages", i);
			messages.setProperty("messageName" , messageName);
			messages.setProperty("SenderEmail" , currentUserEmail);		
			messages.setProperty("ReceiverEmail" , ReceiverEmail);
			messages.setProperty("MessageText", messageText);
			messages.setProperty("seen", seen);
			datastore.put(messages);
		}
		return true ;
	}



	public boolean readGroupMessage()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("groupMessages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if (Long.toString(entity.getKey().getId()).equals(groupMessageID))
			{
				this.messageText = (String) entity.getProperty("MessageText");
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
/*	
	public ArrayList<Map> getGroupMessages() 
	{
		ArrayList<Map> array = new ArrayList();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("groupMessages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		String currentUserEmail = User.currentActiveUser.getEmail();
		
		for (Entity entity : pq.asIterable()) 
		{
			if (entity.getProperty("ReceiverEmail").toString().equals(currentUserEmail) &&
					entity.getProperty("seen").toString().equals("false"))
			{
				
				String chatName = entity.getProperty("messageName").toString();
				String SenderEmail = entity.getProperty("SenderEmail").toString();				
				UserEntity u = new UserEntity();
				String senderID = Long.toString(u.getUserIDByEmail(SenderEmail));
				gaeQuery = new Query("users");
			    pq = datastore.prepare(gaeQuery);
				for (Entity entity2 : pq.asIterable())
				{
					if(Long.toString(entity2.getKey().getId()).equals(senderID))
					{
						Map message = new HashMap();
						message.put("senderName", entity2.getProperty("name"));
						message.put("id", entity.getKey().getId());
						message.put("messageName",chatName );
						array.add(message);
					}
				}				 
			 }
		}
		return array ;
	}
	*/		
}
