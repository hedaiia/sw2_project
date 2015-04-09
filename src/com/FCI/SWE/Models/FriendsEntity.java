package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.List;


import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class FriendsEntity {    //friend request receiver

	
	String currentUser;
	String toUser;
	String friendsID;
	

	public FriendsEntity(){}
	public FriendsEntity(String friendsID) 
	{
		this.friendsID = friendsID ;
	}
	public FriendsEntity(String currentUser , String toUser)
	{
		this.currentUser = currentUser ;
		this.toUser = toUser ;
		
	}
	
	/**
	 * This method will be used to save friend request object in datastore
	 * 
	 * @return if request is saved correctly or not
	 */
	
	public boolean sendFriendRequest()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity friends = new Entity("friends", list.size() + 1);		
		friends.setProperty("friend1",currentUser);
		friends.setProperty("friend2", toUser);
		friends.setProperty("status", 1);
		
		if(datastore.put(friends).isComplete())
		{
			return true ;
		}
		else
		{
			return false ;
		}
	}
	
	/**
	 * This method will be used to save friend request object in datastore
	 * 
	 * @return boolean if accepted request is saved correctly or not
	 */
	
	
	public boolean acceptFriendRequest()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if (entity.getKey().getName().equals(friendsID))
			{
				entity.setProperty("status", 0);
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
	
	
	
	public ArrayList<String> getFriendsName() 
	{
		ArrayList<String> array = new ArrayList();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		String currentUserID = Long.toString(User.currentActiveUser.getId());
		for (Entity entity : pq.asIterable()) 
		{
				if (entity.getProperty("friend1").toString().equals(currentUserID) 
						&& entity.getProperty("status").toString().equals("0"))
				{
					
					String toUser = entity.getProperty("friend2").toString();
					gaeQuery = new Query("users");
				    pq = datastore.prepare(gaeQuery);
					for (Entity entity2 : pq.asIterable())
					{
						if(Long.toString(entity2.getKey().getId()).equals(toUser))
						{
							array.add((String) entity2.getProperty("name"));
						}
					}
				}
				else if (entity.getProperty("friend2").toString().equals(currentUserID) 
						&& entity.getProperty("status").toString().equals("0"))
				{		
					String currentUser = entity.getProperty("friend1").toString();
					gaeQuery = new Query("users");
				    pq = datastore.prepare(gaeQuery);
					for (Entity entity2 : pq.asIterable())
					{
						if(Long.toString(entity2.getKey().getId()).equals(currentUser))
						{
							array.add((String) entity2.getProperty("name"));
						}
					}
				}
		}
		return array;
	}
		
}
