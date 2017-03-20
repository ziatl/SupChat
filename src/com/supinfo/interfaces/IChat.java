package com.supinfo.interfaces;

import com.supinfo.entities.Chat;
import com.supinfo.entities.UserHasChat;

public interface IChat {
	public Chat addChat(Chat chat);
	public Chat getChatByUser(Integer id);
	public UserHasChat addUserHasChat(UserHasChat uhc);
	
	

}
