package com.supinfo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.database.PersistenceManager;
import com.supinfo.entities.Message;
import com.supinfo.entities.User;
import com.supinfo.interfaces.IMessage;

public class MessageDaoImpl implements IMessage {

	public EntityManager em;
	public CrudDaoImpl crudDao;
	
	public MessageDaoImpl() {
		super();
		em = PersistenceManager.getEntityManager();
		crudDao = new CrudDaoImpl();
	}
	
	@Override
	public Message addMessageText(String message, Integer idUser, Integer idChat) {
		Message mes = new Message();
		mes.setDetail(message);
		mes.setType(1);
		
		mes.setUser(crudDao.findUserById(idUser));
		//mes.setChat(crudDao.findChatById(idChat));
		
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(mes);
			et.commit();
			em.close();
			return mes;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}

	@Override
	public Message addMessageFile(String url,String urlLite, Integer type, Integer idUser, Integer idChat) {
		Message mes = new Message();
		mes.setUrl(url);
		mes.setUrlLite(urlLite);
		mes.setType(type);
		
		mes.setUser(crudDao.findUserById(idUser));
		//mes.setChat(crudDao.findChatById(idChat));
		
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(mes);
			et.commit();
			em.close();
			return mes;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}

	@Override
	public Message findMessageById(Integer id) {	
		return em.find(Message.class, id);
	}

	@Override
	public int delMessageById(Integer id) {
		// TODO Auto-generated method stub
				EntityManager em = PersistenceManager.getEntityManager();
				EntityTransaction et = em.getTransaction();
				Message mes = em.find(Message.class, id);
				
				try {
					et.begin();
					em.remove(mes);
					et.commit();
					em.close(); 
					return 1;
				} catch (Exception e) {
					em.close();
					return 0;
				}	
	}

	@Override
	public Message updateMessage(Message message) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(message);
			et.commit();
			em.close();
			return message;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}

	@Override
	public List<Message> getAllMessage() {
		Query q = em.createNamedQuery("select m from Message m");
		List<Message> mm = q.getResultList();
		return mm;
	}

	@Override
	public List<Message> getMessageByString(String mc) {
		Query q = em.createNamedQuery("select m from Message m where m.detail=:X");
		q.setParameter("X", "%"+mc+"%");
		List<Message> ll = q.getResultList();
		return ll;	
	}

}
