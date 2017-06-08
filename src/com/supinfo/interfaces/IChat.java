package com.supinfo.interfaces;

import java.util.List;

import com.supinfo.entities.Chat;
import com.supinfo.entities.Message;
import com.supinfo.entities.User;
import com.supinfo.entities.UserHasChat;
import com.supinfo.providers.ChatJson;

public interface IChat {
	public Chat addChat(Chat chat);
	public List<ChatJson> getChatByUser(Integer id);
	public UserHasChat addUserHasChat(UserHasChat uhc);
	public Integer delUserHasCHat(Integer idUser,Integer idChat);
	
	public Chat findChatById(Integer id);
	public int delChatById(Integer id);
	public List<UserHasChat> findUHCByChat(Integer idChat);
	public int delUHCByChat(Integer idChat);
	public List<UserHasChat> findUserByUserId(Integer id);
	public List<User> findUserByUserIdNotAdd(Integer id);
	public List<Chat> getChatPublic();
	public List<Chat> getChatPrivate(Integer idUser);
	public List<UserHasChat> getInvitation(Integer idUser);
	public UserHasChat responseInvitation(Integer idInvitation,Integer status);
	public User createGroupe(Integer idUser1, String libelle);
	public List<Chat> getGroupe(Integer idUser);
	public List<Chat> getNoGroupe(Integer idUser);
	public UserHasChat addUserGroupe(Integer idUser, Integer idChat);
	public UserHasChat updateUHCLibelle (Integer idUHC, String libelle);
	public User addContact(Integer idUser1,Integer idUser2,String libelle);
	public int updateUHCStatus(Integer id1,Integer id2,Integer status);
	public UserHasChat banUser (Integer idUser, Integer idChat);
	
	public List<Message> getMessByCHat(Integer idCHat);
	
	

}
