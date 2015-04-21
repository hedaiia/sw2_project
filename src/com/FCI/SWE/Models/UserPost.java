package com.FCI.SWE.Models;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class UserPost extends PostBuilder {

public int createUserPost(String currentUser, String postContent, String privacy,String feeling)
	
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity entity = new Entity("posts", list.size() + 1);
		entity.setProperty("postOwner", currentUser);
		entity.setProperty("postContent", postContent);
		entity.setProperty("privacy", privacy);
		entity.setProperty("feeling", feeling);
		entity.setProperty("type", "userPost");
		
		return 0;
		
	}
}