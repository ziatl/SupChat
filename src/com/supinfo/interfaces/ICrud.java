package com.supinfo.interfaces;

import java.io.Serializable;
import java.util.List;

import com.supinfo.entities.Chat;
import com.supinfo.entities.Message;
import com.supinfo.entities.User;
import com.supinfo.entities.UserHasChat;


public interface ICrud {
	//Crud du User
	public User addUser(User u);
	public User findUserById(Integer id);
	public int delUserById(Integer id);
	public User updateUser(User u);
	
	public List<User> getAllUser();
	public List<User> getUserByString(String mc);
	
	public User login(String login,String mdp);
	//Crud Chat
	public Chat addChat(Chat c);
	public Chat findChatById(Integer id);
	public int delChatById(Integer id);
	public Chat updateChat(Chat c);
	
	public List<Chat> getAllChat();
	public List<Chat> getChatByString(String mc);
	public List<Chat> getChatByUser(Integer idUser);
	
	//UserHasChat
	public UserHasChat addUHC(UserHasChat uhc);
	public UserHasChat findUHC(Integer id);
	public int delUHC(Integer id);
	public UserHasChat updateUHC (UserHasChat uhc);
	public List<UserHasChat> getAllUHC();
	
	
	

}
