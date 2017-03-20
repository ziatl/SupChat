package com.supinfo.interfaces;

import java.util.List;

import com.supinfo.entities.Chat;
import com.supinfo.entities.UserHasChat;

public interface IChat {
	public Chat addChat(Chat chat);
	public List<Chat> getChatByUser(Integer id);
	public UserHasChat addUserHasChat(UserHasChat uhc);
	
	

}
