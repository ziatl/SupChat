package com.supinfo.services;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;



import com.supinfo.dao.CrudDaoImpl;
import com.supinfo.dao.MessageDaoImpl;
import com.supinfo.entities.User;


@Path("/rest")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ChatService {

	public CrudDaoImpl crudDao;
	public MessageDaoImpl messageDao;
	
	public ChatService() {
		crudDao = new CrudDaoImpl();
		messageDao = new MessageDaoImpl();
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<User> getUsers(){
		return crudDao.getAllUser();
	}
	
	@POST
	@Path("/user")
	public User addUser(User u){
		crudDao.addUser(u);
		return u; 
	}
	
	@PUT
	@Path("/user")
	public User updateUser(User u){
		crudDao.updateUser(u);
		return u; 
	}
	
	@GET
	@Path("/user/{idUser}")
	public User getUsersById(@PathParam(value="idUser") Integer idUser){
		return crudDao.findUserById(idUser);
	}
	
	@GET
	@Path("/user/mc")
	public List<User> findUseuByMc(@QueryParam(value="mc")String mc){
		System.out.println(mc);
		return crudDao.getUserByString(mc);
	}

	@DELETE
	@Path("/user/{idUser}")
	public Integer deleteUser(@PathParam(value="idUser")Integer idUser) {
		
		return crudDao.delChatById(idUser);
	}
	
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public String test(){
		return "test test";
	}
	
}





