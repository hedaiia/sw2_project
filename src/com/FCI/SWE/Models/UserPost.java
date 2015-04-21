package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class UserPost extends PostBuilder { //builder

public static int createUserPost(String currentUser, String postContent, String privacy,String feeling)
	
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

	public ArrayList<Map> viewTimeLine(String currentUser)
	{
		ArrayList<Map> array = new ArrayList<Map>() ;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query ("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Map <String ,String>  post = new HashMap();

	for(Entity e : pq.asIterable())
	{
		if(e.getProperty("type").toString().equals("pagePost"))
		if(e.getProperty("postOwner").toString().equals(currentUser))
		{
				post.put("postOwner", currentUser + " posted this");
				post.put("postContent", e.getProperty("postContent").toString());
				post.put("feeling", (String) e.getProperty("feeling"));
				array.add(post);
			}
	}
	return array ;			
}
}