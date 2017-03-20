package com.supinfo.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.model.MethodList.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supinfo.dao.ChatDaoImpl;
import com.supinfo.dao.CrudDaoImpl;
import com.supinfo.dao.MessageDaoImpl;
import com.supinfo.entities.Chat;
import com.supinfo.entities.Message;
import com.supinfo.entities.User;
import com.supinfo.entities.UserHasChat;


@Path("/rest")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class ChatService {

	public CrudDaoImpl crudDao;
	public MessageDaoImpl messageDao;
	public ChatDaoImpl chatDao;
	
	public ChatService() {
		crudDao = new CrudDaoImpl();
		messageDao = new MessageDaoImpl();
		chatDao = new ChatDaoImpl();
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
		u.setDateCreate(new Date());
		u.setDateUpdate(new Date());
		crudDao.addUser(u);
		return u; 
	}
	
	@PUT
	@Path("/user")
	public User updateUser(User u){
		u.setDateUpdate(new Date());
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
	public int deleteUser(@PathParam(value="idUser")Integer idUser) {
			return crudDao.delChatById(idUser);
	}
	@POST
	@Path("/login")
	public User login(@FormParam(value="login") String login,@FormParam(value="mdp") String mdp){
		return crudDao.login(login, mdp);
	}
	
	@POST
	@Path("/chat")
	public Chat addChat(Chat chat){
		return chatDao.addChat(chat);
	}
	@GET
	@Path("/chat/{id}")
	public List<Chat> getChatByUser(@PathParam(value="id")Integer userId){
		return chatDao.getChatByUser(userId);	
	}

	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test(){
		ObjectMapper mapper = new ObjectMapper();
		User user = crudDao.findUserById(3);
		Response response = Response.status(Status.OK).entity(user).build();
		return response;
	}
	
}