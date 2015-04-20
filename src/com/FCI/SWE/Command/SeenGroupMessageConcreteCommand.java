/*package com.FCI.SWE.Command;

import com.FCI.SWE.Services.NotificationsService;
import com.FCI.SWE.Models.GroupMessageEntity;


public class SeenGroupMessageConcreteCommand implements ICommnad {

	GroupMessageEntity gm = new GroupMessageEntity(); // group message receiver
	public SeenGroupMessageConcreteCommand(GroupMessageEntity gm) 
	{
		this.gm = gm ;
	}

	@Override
	public boolean excute() {
		
		if(gm.readGroupMessage())
		{
			NotificationsService.response = gm.getMessageText();
			return true ;
		}
		return false ;
	}

}
*/