package com.FCI.SWE.FrontEndTesting;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;









////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.FCI.SWE.Controller.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.FCI.SWE.Models.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class TestEntity {
	UserEntity u=new UserEntity();
	UserPost p = new UserPost();
	FriendsEntity f = new FriendsEntity();
	MessagesEntity m = new MessagesEntity();
	PageEntity page = new PageEntity();
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@BeforeClass
	public void setup() {
		helper.setUp();
	}


	
	@AfterClass
	public void tearDown() {
		helper.tearDown();
	}
	@Test
@DataProvider(name = "test1")
public static Object[][]sendRequestTest(){
	return new Object[][] {{0 , "naeema","hagar"} , {1 , "wafaa","mayada"} , {2 , "ahmed","mayada"}};
}
	
	@DataProvider(name = "test2")
	public static Object[][]acceptRequestTest(){
		return new Object[][] {{true , "ahmed","mayada"} , {false , "hagar","mayada"} , {2 , "ahmed","mayada"}};
	}
	
	@DataProvider(name = "test3")
	public static Object[][]messageTest(){
		return new Object[][] {{0, "hagar","mayada","Hi"} , {1 , "wafaa","mayada"}};
	}
	
	
	@DataProvider(name = "test4")
	public static Object[][]pageTest(){
		return new Object[][] {{"Ok", "fci","faculty","study","3" , "mayada","0"} , {"failed" , "","faculty","study","2","mayada","0"}};
	}
	
	
	@DataProvider(name = "test5")
	public static Object[][]postTest(){
	return new Object[][] {{0, "mayada","hi","public","happy"} , {1 , "mayada","","public","sad"}};
	}
	
	@Test
	public void getUser()
	{
		Assert.assertNotNull(UserEntity.getUser("mayada", "mayada"));
	}
	
	@Test
	public void saveuser()
	{
		boolean b = u.saveUser();
		Assert.assertEquals(true, b);
	}
	
	@Test(dataProvider ="test1")
	public void sendRequestTest(int result, String toUser , String currentUser)
	{
		 Assert.assertEquals(result,f.sendFriendRequest(toUser, currentUser));
	}
	
	@Test(dataProvider ="test2")
	public void acceptRequestTest(boolean result, String toUser , String currentUser)
	{
		 Assert.assertEquals(result,f.acceptFriendRequest(toUser, currentUser));
	}
	
	@Test(dataProvider ="test3")
	public void messageTest(int result, String toUser , String currentUser , String message_text)
	{
		 Assert.assertEquals(result,m.sendMessage(toUser, currentUser, message_text));
	}
	
	@Test(dataProvider ="test4")
	public void pageTest(String result, String pageName , String pageType , String pageDescription , String adminID , String pageOwner ,int numberOfLikes)
	{
		 Assert.assertEquals(result,page.createPage(new CreatePageParameter(pageName, pageType, pageDescription, adminID, pageOwner,
				numberOfLikes)));
	}
	
	@Test(dataProvider ="test5")
	public void postTest(int result, String currentUser , String postContent , String privacy , String feeling)
	{
		 Assert.assertEquals(result,p.createUserPost(currentUser, postContent, privacy, feeling));
	}

  
}