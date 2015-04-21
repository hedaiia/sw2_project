package com.FCI.SWE.Models;

import java.util.List;
import java.util.Vector;

import javax.ws.rs.FormParam;

import com.google.appengine.labs.repackaged.org.json.JSONArray;

import org.json.simple.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PostBuilder {
	
	/*String currentUser;
	String postContent;
	String privacy;
	String feeling;
	Vector<String> postHashtag = new Vector<String>();
	int numberOfLikes;
	int numberOfSeens;
	
	public String getPostHashtag()
	{
		String[] words = postContent.split(" ");
		for (int i = 0; i < words.length; i++)
		{
			if (words[i].charAt(0) == '#') 
			{
				postHashtag.add(words[i]);
			}
		}
		
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		for (int i = 0; i < postHashtag.size(); i++) {

			object.put("hashtags", postHashtag.get(i));
			array.put(object);
		}
		return array.toString();
	}
	*/
	
	
	//abstract int createPagePost();
	//abstract int createUserPost(String currentUser, String postContent, String privacy,String feeling);
	
		
}
