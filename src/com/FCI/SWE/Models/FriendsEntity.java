package com.FCI.SWE.Models;

import java.util.List;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class FriendsEntity {

	/**
	 * This method will be used to save friend request object in datastore
	 * 
	 * @return if request is saved correctly or not
	 */
	
	public static int sendFriendRequest(String toUser, String currentUser) {
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
		//check if they are already friends or not
		gaeQuery = new Query("friends");
		pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			if ((entity.getProperty("friend1").toString().equals(toUser) && entity.getProperty("friend2").toString().equals(currentUser)) || (entity.getProperty("friend1").toString().equals(currentUser) && entity.getProperty("friend2").toString().equals(toUser))) {
				flag = false;
				break;
				}
		}
		if(!flag)
			return 1;
		gaeQuery = new Query("requests");
		pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity request = new Entity("requests", list.size() + 1);
		request.setProperty("currentUser", currentUser);
		request.setProperty("toUser", toUser);
		request.setProperty("status", 1);
		datastore.put(request);
		return 2;
	}
	
	/**
	 * This method will be used to save friend request object in datastore
	 * 
	 * @return boolean if accepted request is saved correctly or not
	 */
	
	public static boolean acceptFriendRequest(String toUser, String currentUser) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if(entity.getProperty("currentUser").equals(toUser) && entity.getProperty("toUser").equals(currentUser))
			{
				entity.setProperty("status", 0);
				datastore.put(entity);
				gaeQuery = new Query("friends");
				pq = datastore.prepare(gaeQuery);
				List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
				Entity friends = new Entity("friends", list.size() + 1);
				friends.setProperty("friend1", currentUser);
				friends.setProperty("friend2", toUser);
				datastore.put(friends);
				return true;
			}
		}
		return false;
	}	
	
}