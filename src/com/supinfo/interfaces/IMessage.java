package com.supinfo.interfaces;

import java.util.List;

import com.supinfo.entities.Message;
import com.supinfo.entities.User;

public interface IMessage {
	
	public Message addMessageText(String message,Integer idUser,Integer idChat);
	public Message addMessageFile(String url,String urlLite,Integer type,Integer idUser,Integer idChat);
	
	public Message findMessageById(Integer id);
	public int delMessageById(Integer id);
	public Message updateMessage(Message message);
	
	public List<Message> getAllMessage();
	public List<Message> getMessageByString(String mc);

}
