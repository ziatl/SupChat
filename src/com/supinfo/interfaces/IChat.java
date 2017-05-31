package com.supinfo.interfaces;

import java.util.List;

import com.supinfo.entities.Chat;
import com.supinfo.entities.User;
import com.supinfo.entities.UserHasChat;

public interface IChat {
	public Chat addChat(Chat chat);
	public List<Chat> getChatByUser(Integer id);
	public UserHasChat addUserHasChat(UserHasChat uhc);
	public Integer delUserHasCHat(Integer idUser,Integer idChat);
	
	public Chat findChatById(Integer id);
	public int delChatById(Integer id);
	public List<UserHasChat> findUHCByChat(Integer idChat);
	public int delUHCByChat(Integer idChat);
	public List<User> findUserByUserId(Integer id);
	public List<User> findUserByUserIdNotAdd(Integer id);
	public List<Chat> getChatPublic();
	public List<Chat> getChatPrivate(Integer idUser);
	
	public User addContact(Integer idUser1,Integer idUser2,String libelle);
	public int updateUHCStatus(Integer id1,Integer id2,Integer status);
	
	

}
