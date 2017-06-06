package com.supinfo.sockserver;

import com.supinfo.dao.ChatDaoImpl;
import com.supinfo.dao.CrudDaoImpl;
import com.supinfo.dao.MessageDaoImpl;
import com.supinfo.interfaces.IMessageProvider;

public class MessageProvider implements IMessageProvider {
	public CrudDaoImpl crudDao;
	public MessageDaoImpl messageDao;
	public ChatDaoImpl chatDao;
	
	public MessageProvider() {
		crudDao = new CrudDaoImpl();
		messageDao = new MessageDaoImpl();
		chatDao = new ChatDaoImpl();
	}
	
	
}
