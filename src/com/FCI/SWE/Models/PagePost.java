package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PagePost extends PostBuilder{    //page builder
	String currentUser;
	String postContent;
	String privacy;
	String feeling;
	Vector<String> postHashtag = new Vector<String>();
	int numberOfLikes;
	int numberOfSeens;
public int createPagePost() {
	// TODO Auto-generated method stub
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	Query gaeQuery = new Query("posts");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
	Entity entity = new Entity("posts", list.size() + 1);
	entity.setProperty("postOwner", currentUser);
	entity.setProperty("postContent", postContent);
	entity.setProperty("type", "pagePost");
	
	return 0;
}

public ArrayList<Map> viewTimeLine(String currentUser, String pageName) 
{
	ArrayList<Map> array = new ArrayList<Map>() ;
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	Query gaeQuery = new Query ("posts");
	PreparedQuery pq = datastore.prepare(gaeQuery);

	for(Entity e : pq.asIterable())
	{
		if(e.getProperty("type").toString().equals("pagePost") && e.getProperty("pageName").toString().equals(pageName))
		{
			Map <String ,String> posts = new HashMap();					
			posts.put("postOwner", e.getProperty("postOwner").toString() + " posted this.");
			posts.put("postContent", e.getProperty("postContent").toString());

			array.add(posts);
		}
	}

	return array ;			
}
	

}
