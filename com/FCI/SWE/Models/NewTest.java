package com.FCI.SWE.Models;

import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class  UserEntityTest{
 UserEntity=new UserEntity();

 
 private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
 
 @BeforeClass
 public void setup(){
	 helper.setUp();
 }
 
 @DataProvider(name = "test1")
public static Object[][]GetUserTest(){
	return new ;
}
  @Test
  public void f() {
  
  }
}
