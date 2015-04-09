package com.FCI.SWE.Command;

import com.FCI.SWE.Models.FriendsEntity;

public class AcceptRequestConcreteCommand implements ICommnad {

	private FriendsEntity f ; //friend request receiver
	public AcceptRequestConcreteCommand(FriendsEntity f)
	{
		this.f = f ;
	}
	
	@Override
	public boolean excute() 
	{
		if(f.acceptFriendRequest())
			return true ;
		return false ;
	}

}
