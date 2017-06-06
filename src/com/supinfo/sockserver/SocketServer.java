package com.supinfo.sockserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/server/{id}")
public class SocketServer {
	
    private static List<Session> allSessions = new ArrayList<Session>();
    private static Map<Session, Integer> allMap = new HashMap<Session, Integer>();
	
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
						ss.getBasicRemote().sendText(message);
						System.out.println(message);
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
