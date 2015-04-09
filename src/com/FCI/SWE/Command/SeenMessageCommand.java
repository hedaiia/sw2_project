package com.FCI.SWE.Command;

import com.FCI.SWE.Services.NotificationsService;
import com.FCI.SWE.Models.MessageEntity;

public class SeenMessageCommand implements ICommnad {

	MessageEntity m = new MessageEntity(); //receiver
	public SeenMessageCommand(MessageEntity m) 
	{
		this.m = m ;
	}

	@Override
	public boolean excute() {
		
		if(m.readMessage())
		{
			NotificationsService.response = m.getMessageText();
			return true ;
		}
		return false ;
	}

}
