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
	//Chat
	@POST
	@Path("/chat")
	public Chat addChat(Chat c) {
		c.setDateCreate(new Date());
		c.setDateUpdate(new Date());
		return crudDao.addChat(c);
	}
	@PUT
	@Path("/chat")
	public Chat updateChat(Chat c) {
		c.setDateUpdate(new Date());
		return crudDao.updateChat(c);
	}
	@GET
	@Path("/chat/{idChat}")
	public Chat getCHatByid (@PathParam(value="idChat")Integer idChat) {
		return crudDao.findChatById(idChat);
	}
	@GET
	@Path("/chat/mc")
	public List<Chat> getChatByMc (@QueryParam(value="mc")String mc) {
		return crudDao.getChatByString(mc);
	}
	@DELETE
	@Path("/chat")
	public int delChat (@PathParam(value="idChat")Integer idChat) {
		return crudDao.delChatById(idChat);
	}
	
	//Message
	@POST
	@Path("/message")
	public Message addMessageText(Message m,String Mess, Integer idUser, Integer idChat ) {
		m.setDateCreate(new Date());
		m.setDateUpdate(new Date());
		return messageDao.addMessageText(Mess, idUser, idChat);
	}
	@POST
	@Path("/message")
	public Message addMessageFile(Message m,String url,String urlLite, Integer type, Integer idUser, Integer idChat) {
		m.setDateCreate(new Date());
		m.setDateUpdate(new Date());
		return messageDao.addMessageFile(url, urlLite, type, idUser, idChat);
	}
	@PUT
	@Path("/message")
	public Message updateMessage (Message m) {
		m.setDateUpdate(new Date());
		return messageDao.updateMessage(m);
	}
	@GET
	@Path("/message/{idMessage}")
	public Message getMessageByid (@PathParam(value="idMessage") Integer idMessage) {
		return messageDao.findMessageById(idMessage);
	}
	@GET
	@Path("/message/mc")
	public List<Message> getMessageByMc (@QueryParam(value="mc")String mc) {
		return messageDao.getMessageByString(mc);
	}
	@DELETE
	@Path("/message")
	public int delMessage (int idMess) {
		return messageDao.delMessageById(idMess);
	}
	
	//UserHasChat
	@POST
	@Path("/userhasChat")
	public UserHasChat addUHC(UserHasChat uhc) {
		return crudDao.addUHC(uhc);
	}
	@PUT
	@Path("/userhasChat")
	public UserHasChat updateUHC(UserHasChat uhc) {
		return crudDao.updateUHC(uhc);
	}
	@GET
	@Path("/userhasChat/{idUHC}")
	public UserHasChat getUHCByid (@PathParam(value="idUHC")Integer idUHC) {
		return crudDao.findUHC(idUHC);
	}
	@DELETE
	@Path("/userhasChat")
	public int delUserHasChat (@PathParam(value="idUHC")Integer idUHC) {
		return crudDao.delChatById(idUHC);
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