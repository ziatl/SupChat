package com.supinfo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.resources.SenderMessages;
import com.supinfo.dao.ChatDaoImpl;
import com.supinfo.dao.CrudDaoImpl;
import com.supinfo.dao.MessageDaoImpl;
import com.supinfo.dao.SendMail;
import com.supinfo.database.PersistenceManager;
import com.supinfo.entities.Chat;
import com.supinfo.entities.Message;
import com.supinfo.entities.Parametre;
import com.supinfo.entities.User;
import com.supinfo.entities.UserHasChat;
import com.supinfo.providers.ChatJson;


@Path("/rest")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ChatService {
	public CrudDaoImpl crudDao;
	public MessageDaoImpl messageDao;
	public ChatDaoImpl chatDao;
	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
		
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	
	public ChatService() {
		crudDao = new CrudDaoImpl();
		messageDao = new MessageDaoImpl();
		chatDao = new ChatDaoImpl();
		em = PersistenceManager.getEntityManager();
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<User> getUsers(ContainerRequestContext requestContext){
		List<User> liste = crudDao.getAllUser();
		try {
			int id = Integer.parseInt(requestContext.getHeaderString("id"));
			
			for (User user : liste) {
				if (user.getId() == id) {
					liste.remove(user);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return liste;
	}
	@GET
	@Path("/user/contact")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<UserHasChat> getUsersContact(ContainerRequestContext requestContext){
		List<UserHasChat> liste = new ArrayList<UserHasChat>();
		List<UserHasChat> contact = new ArrayList<UserHasChat>();
		try {
			int idUser = Integer.parseInt(requestContext.getHeaderString("id"));
			contact = chatDao.findUserByUserId(idUser);		
		} catch (Exception e) {
			return liste;
		}
		return contact;
	}
	@GET
	@Path("/user/notContact")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<User> getUsersNotContact(ContainerRequestContext requestContext){
		List<User> liste = crudDao.getAllUser();
		List<User> contact = new ArrayList<User>();
		try {
			int idUser = Integer.parseInt(requestContext.getHeaderString("id"));
			contact = chatDao.findUserByUserIdNotAdd(idUser);		
		} catch (Exception e) {
			return liste;
		}
		return contact;
	}
	
	
	
	@POST
	@Path("/user")
	public User addUser(User u){
		u.setDateCreate(new Date());
		u.setDateUpdate(new Date());
		u.setStatut("Hors Ligne");
		crudDao.addUser(u);
		Parametre p = new Parametre();
		p.setPush(true);
		p.setAudio(false);
		p.setVideo(false);
		p.setSearch(false);
		p.setSon(false);
		crudDao = new CrudDaoImpl();
		User user = crudDao.findUserById(u.getId());
		p.setUser(user);
		crudDao.addParametre(p);
		return u; 
	}
	
	@PUT
	@Path("/user")
	public User updateUser(User u){
		u.setDateUpdate(new Date());
		crudDao.updateUser(u);
		return u; 
	}
	@PUT
	@Path("/user/{id}/{status}")
	public User updateUser(@PathParam(value="status")String statut,@PathParam(value="id")Integer id,ContainerRequestContext requestContext){

		User user = crudDao.findUserById(id);
		user.setStatut(statut);
		user.setDateUpdate(new Date());
		crudDao.updateUser(user);
		return user; 
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
	public List<ChatJson> getChatByUser(@PathParam(value="id")Integer userId){
		return chatDao.getChatByUser(userId);	
	}
	@GET
	@Path("/chat/{idChat}/messages")
	public List<Message> getMessByCHat(@PathParam(value="idChat")Integer idChat){
		return chatDao.getMessByCHat(idChat);	
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
	@PUT
	@Path("/chat")
	public Chat updateChat(Chat c){
		c.setDateUpdate(new Date());
		crudDao.updateChat(c);
		return c; 
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
	@PUT
	@Path("/userHasChat/{idUHC}/{libelle}")
	public UserHasChat updateUHCLibelle (@PathParam(value="idUHC") Integer idUHC, @PathParam(value="libelle") String libelle) {
			return chatDao.updateUHCLibelle(idUHC, libelle);
	}
	//JoinGroup
		@POST
		@Path("/joinGroup/{idUser}/{idChat}")
		public UserHasChat joinGroup (@PathParam(value="idUser")Integer idUser,@PathParam(value="idChat")Integer idChat,UserHasChat uhc) {
			System.out.println("IdUser "+idUser);
			System.out.println("IdChat"+idChat);
			System.out.println(uhc.getId());
			uhc.setUser(crudDao.findUserById(idUser));
			uhc.setChat(chatDao.findChatById(idChat));
			uhc.setStatus(4);
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
	
	
	@POST
	@Path("/contact")
	public User addContact(@QueryParam(value="id1")Integer idUser1,@QueryParam(value="id2")Integer idUser2,@QueryParam("libelle") String libelle){
		return chatDao.addContact(idUser1, idUser2,libelle);
	}
	@PUT
	@Path("/user/groupe/")
	public User addGroupe(@QueryParam(value="idUser1") Integer idUser1, @QueryParam(value="libelle") String libelle) {
		return chatDao.createGroupe(idUser1, libelle);
	}
	@PUT
	@Path("/user/addgroupe/")
	public UserHasChat adUserGroupe(@QueryParam(value="idUser") Integer idUser, @QueryParam(value="idChat") Integer idChat) {
		return chatDao.addUserGroupe(idUser, idChat);
	}
	@GET
	@Path("/contact/{idUser}/{status}")
	public int updateUHCStatus(@PathParam(value="idUser")Integer id2,@PathParam(value="status")Integer status,ContainerRequestContext requestContext){
		int id = Integer.parseInt(requestContext.getHeaderString("id"));
		User user = crudDao.findUserById(id);
		return chatDao.updateUHCStatus(user.getId(), id2,status);
	} 
	@GET
	@Path("/user/invit")
	public List<UserHasChat> getInvitation(ContainerRequestContext containerRequestContext){
		int idUser = Integer.parseInt(containerRequestContext.getHeaderString("id"));
		return chatDao.getInvitation(idUser);		
	}
	
	@PUT
	@Path("/user/invit/{idUHC}/{status}")
	public UserHasChat responseInvitation(@PathParam(value="idUHC") Integer idUHC, @PathParam(value="status") Integer status){	
		return chatDao.responseInvitation(idUHC, status);
	}
	
	//Parametre
	@POST
	@Path("/parametre")
	public Parametre addParametre(Parametre p) {
		
		return crudDao.addParametre(p);
	}
	
	@PUT
	@Path("/parametre")
	public Parametre updateParametre(Parametre p) {
		Parametre pp = crudDao.findParametreById(p.getId());
		p.setUser(pp.getUser());
		return crudDao.updateParametre(p);
	}
	
	@DELETE
	@Path("/parametre/{idParametre}")
	public Integer delParametreById (@PathParam("idParametre")Integer idParametre){
		return crudDao.delParametreId(idParametre);
	}
	
	
	@GET
	@Path("/parametre/{idUser}")
	public Parametre getParametreByUserid (@PathParam(value="idUser")Integer idUser){
		return crudDao.getParametreByUser(idUser);
	}
	@GET
	@Path("/user/groupe/{idUser}")
	public List<Chat> getGroupe (@PathParam(value="idUser")Integer idUser) {
		return chatDao.getGroupe(idUser);
	}
	@GET
	@Path("/user/notgroupe/{idUser}")
	public List<Chat> getNotGroupe (@PathParam(value="idUser")Integer idUser) {
		return chatDao.getNoGroupe(idUser);
	}
	@PUT
	@Path("/chats/ban/{idUser}/{idChat}")
	public UserHasChat banUser (@PathParam(value="idUser")Integer idUser, @PathParam(value="idChat")Integer idChat) {
		return chatDao.banUser(idUser, idChat);
	}
	@POST
	@Path("/groupe")
	public User createGroupe (@QueryParam(value="id1")Integer idUser1,@QueryParam("libelle") String libelle) {
		return chatDao.createGroupe(idUser1, libelle);
	}
	//Device
	
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test(){
//		SendMail.SendEmail("supquirk2017@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
	
		User user = crudDao.findUserById(3);
		Response response = Response.status(Status.OK).entity(user).build();
		return response;
	}
	@POST
	@Path("/testT")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> testUser(User u){	
		System.out.println(u.getNom());
		User user = crudDao.findUserById(3);
		List<User> us = new ArrayList<User>();
		us.add(user);
		return us;
	}
	
	
}