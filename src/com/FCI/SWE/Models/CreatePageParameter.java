package com.FCI.SWE.Models;

public class CreatePageParameter {
	public String pageName;
	public String pageType;
	public String pageDescription;
	public String adminID;
	public String pageOwner;
	public int numberOfLikes;

	public CreatePageParameter(String pageName, String pageType,
			String pageDescription, String adminID, String pageOwner,
			int numberOfLikes) {
		this.pageName = pageName;
		this.pageType = pageType;
		this.pageDescription = pageDescription;
		this.adminID = adminID;
		this.pageOwner = pageOwner;
		this.numberOfLikes = numberOfLikes;
	}

	public String getPageName() {
		return pageName;
	}


	public String getPageType() {
		return pageType;
	}


	public String getPageDescription() {
		return pageDescription;
	}


	public String getAdminID() {
		return adminID;
	}


	public String getPageOwner() {
		return pageOwner;
	}


	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	@Override
	public String toString()
	{
		return this.adminID + " " + this.numberOfLikes + " " + this.pageDescription + " " +
				this.pageName + " " + this.pageOwner + " " + this.pageType + " ";
	}

	
}