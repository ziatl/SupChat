package com.supinfo.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.supinfo.database.PersistenceManager;
import com.supinfo.entities.Chat;
import com.supinfo.entities.UserHasChat;
import com.supinfo.interfaces.IChat;

public class ChatDaoImpl implements IChat {
	private EntityManager em;
	public CrudDaoImpl crudDao;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public ChatDaoImpl() {
		// TODO Auto-generated constructor stub
		super();
		em = PersistenceManager.getEntityManager();
		crudDao = new CrudDaoImpl();
	}

	@Override
	public Chat addChat(Chat chat) {
		chat.setDateCreate(new Date());
		chat.setDateUpdate(new Date());
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(chat);
			et.commit();
			UserHasChat uhc = new UserHasChat();
			uhc.setChat(chat);
			uhc.setUser(crudDao.findUserById(chat.getCreator()));
			uhc.setAdmin(true);
			uhc.setStatus(1);
			addUserHasChat(uhc);
			return chat;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}
	@Override
	public UserHasChat addUserHasChat(UserHasChat uhc) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(uhc);
			et.commit();
			em.close();
			return uhc;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}
	@Override
	public Chat getChatByUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
