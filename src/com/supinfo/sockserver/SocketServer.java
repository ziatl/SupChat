package com.supinfo.sockserver;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/server/{id}")
public class SocketServer {
	
	
	@OnOpen
	public void onOpen(Session session,@PathParam(value="id") String id) throws IOException{
		 System.out.println(session.getId() + " a ouvert une connexion"); 
	        try {
	            session.getBasicRemote().sendText("Connexion Etablie");
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	   
	}
	
	@OnMessage
	public void onMessage(String message, Session session,@PathParam(value="id") String id) throws IOException{
		session.getBasicRemote().sendText(message);
	}
	
	@OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" vient de se deconnecter");
        
    } 

}
