package com.supinfo.services;

import java.io.IOException;
import java.util.ArrayList;
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
	private User user;
	private Chat chat;
	private UserHasChat uhc;
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
	
	@DELETE
	@Path("/chat/{idChat}")
	public int deleteChat(@PathParam(value="idChat")Integer idChat) {
			return chatDao.delChatById(idChat);
	}
	@GET
	@Path("/chat/public")
	public List<Chat> getChatPublic() {
		return chatDao.getChatPublic();
	}
	@GET
	@Path("/chat/{idUser}/private")
	public List<Chat> getCHatPrivate(@PathParam(value="idUser")Integer idUser) {
		return chatDao.getChatPrivate(idUser);
	}
	//UHC
	@POST
	@Path("/userHasChat/{idUser}/{idChat}")
	public UserHasChat addUHC (@PathParam(value="idUser")Integer idUser,@PathParam(value="idChat")Integer idChat,UserHasChat uhc) {
		System.out.println("IdUser "+idUser);
		System.out.println("IdChat"+idChat);
		System.out.println(uhc.getId());
		uhc.setUser(crudDao.findUserById(idUser));
		uhc.setChat(chatDao.findChatById(idChat));
		return chatDao.addUserHasChat(uhc);		 
	}
	@DELETE
	@Path("/userHasChat")
	public UserHasChat delUHC (UserHasChat uhc) {
		return delUHC(uhc);
	}
	@DELETE
	@Path("/userHasChat/{idUser}/{idChat}")
	public Integer delUHCUC (@PathParam(value="idUser")Integer idUser,@PathParam(value="idChat")Integer idChat) {
		return chatDao.delUserHasCHat(idUser, idChat);
	}
	
	
	
	
	//Contacts
	@GET
	@Path("/contact/{idUser}")
	public List<User> contact (@PathParam(value="idUser")Integer idUser){
		return chatDao.findUserByUserId(idUser);
	}
	
	@POST
	@Path("/contact")
	public User addContact(@FormParam(value="id1")Integer idUser1,@FormParam(value="id2")Integer idUser2){
		return chatDao.addContact(idUser1, idUser2);
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
	@GET
	@Path("/testT")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> testUser(){	
		User user = crudDao.findUserById(3);
		List<User> us = new ArrayList<User>();
		us.add(user);
		return us;
	}
	
}