package com.supinfo.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.corba.se.impl.orbutil.RepositoryIdUtility;
import com.supinfo.dao.CrudDaoImpl;
import com.supinfo.dao.MessageDaoImpl;
import com.supinfo.entities.User;


@Path("/rest")
public class ChatService {
	
	public CrudDaoImpl crudDao;
	public MessageDaoImpl messageDao;
	
	public ChatService() {
	
		crudDao = new CrudDaoImpl();
		messageDao = new MessageDaoImpl();
	}
	
	
	
	
}
