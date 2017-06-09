package com.supinfo.sockserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supinfo.dao.ChatDaoImpl;
import com.supinfo.dao.CrudDaoImpl;
import com.supinfo.dao.MessageDaoImpl;
import com.supinfo.entities.Message;

@ServerEndpoint("/server/{id}")
public class SocketServer {
	
    private static List<Session> allSessions = new ArrayList<Session>();
    private static Map<Session, Integer> allMap = new HashMap<Session, Integer>();
	
    public CrudDaoImpl crudDao;
	public MessageDaoImpl messageDao;
	public ChatDaoImpl chatDao;
	
	public SocketServer() {
		// TODO Auto-generated constructor stub
		crudDao = new CrudDaoImpl();
		messageDao = new MessageDaoImpl();
		chatDao = new ChatDaoImpl();
	}
	
	@OnOpen
	public void onOpen(Session session,@PathParam(value="id") String id) throws IOException{
		 System.out.println(session.getId() + " a ouvert une connexion"); 
		 allSessions.add(session);
		 allMap.put(session, Integer.parseInt(id));
	        try {
	        	
	            session.getBasicRemote().sendText("Connexion Etablie");
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	   
	}
	
	@OnMessage
	public void onMessage(String message, Session session,@PathParam(value="id") String id) throws IOException{
		try {
        	for (Map.Entry<Session,Integer> m : allMap.entrySet()) {
				Session ss = m.getKey();
				if (ss.isOpen()) {
					if (ss.getId() != session.getId()) {
						System.out.println("******* "+message);
						ObjectMapper mapper = new ObjectMapper();
						MessageSocketObject mssko = mapper.readValue(message, MessageSocketObject.class);
						messageDao = new MessageDaoImpl();
						messageDao.addMessageText(mssko.getContent(), mssko.getIdUser(), mssko.getId(),mssko.getType());
						ss.getBasicRemote().sendText(mapper.writeValueAsString(mssko));
						System.out.println(mapper.writeValueAsString(mssko));
					}
				}
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" vient de se deconnecter");
        
    } 

}
