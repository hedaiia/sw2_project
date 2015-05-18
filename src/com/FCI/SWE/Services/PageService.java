package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.simple.JSONObject;
import com.FCI.SWE.Models.PageEntity;

@Path("/")
@Produces("text/html")
public class PageService {
	
		/**
		 * create page Rest Service, this service will be called to make creation of new page process
		 * also will check data from datastore
		 * @param pageName provided page name
		 * @param pageType provided page type
		 * @param pageDescription provided page description
		 * @param adminID provided id of page owner
		 * @param pageOwner provided name of page owner
		 * @param numberOfLikes
		 * @return object in json format
		 */
	
		@POST
		@Path("/createPageService")
		public String createPageService(@FormParam("name") String pageName,@FormParam("Type") String pageType,
				@FormParam("pageDescription") String pageDescription,@FormParam("adminID") String adminID,
				@FormParam("pageOwner") String pageOwner,
				@FormParam("numberOfLikes") int numberOfLikes) {
			
			JSONObject object = new JSONObject();
			String flag = PageEntity.createPage(pageName , pageType,pageDescription,adminID,pageOwner,numberOfLikes);
			if (flag == "failed") 
			{
				object.put("numberOfLikes", "Failed");
			} else
				{
					object.put("numberOfLikes", "0");
				}

			return object.toString();
		}

}