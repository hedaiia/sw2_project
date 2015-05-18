package com.FCI.SWE.Models;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


public class PageEntity {
	UserEntity user = new UserEntity();
	long userID = user.getId();
	private int numberOfLikes ;
	
	public PageEntity (){}
	
	public int getNumberOfLikes (){
		return numberOfLikes;
	}
	
	public static String createPage(CreatePageParameter parameterObject)
		{
			int numberOfLikes = parameterObject.numberOfLikes;
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query gaeQuery = new Query("Page");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			
			numberOfLikes = 0 ;
			Entity page = new Entity ("Page" , list.size() + 1);
			page.setProperty("PageName", parameterObject.pageName);
			page.setProperty("PageType" , parameterObject.pageType);
			page.setProperty("PageDescription" , parameterObject.pageDescription);
			page.setProperty("AdminID", parameterObject.adminID);
			page.setProperty("NumberOfLikes" , numberOfLikes);
			page.setProperty("pageOwner" , parameterObject.pageOwner);
			
			datastore.put(page);
			return "Ok";
		}
		
		/**
		 * This method will be used to like a page
		 * 
		 * @return "Ok" 
		 */
		

	public String likePage (String pageName , long id )
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("LikePage");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
		// id of current active user
		id = userID;
		Entity pagelikes = new Entity ("LikePage" , list.size() + 1);
		pagelikes.setProperty("PageName", pageName);
		pagelikes.setProperty("UserID" , id);
		
		datastore.put(pagelikes);
		numberOfLikes++;
			
			return "Ok";
	}
	
}
