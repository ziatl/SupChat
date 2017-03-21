package com.supinfo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.database.PersistenceManager;
import com.supinfo.entities.Chat;
import com.supinfo.entities.User;
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
	public List<Chat> getChatByUser(Integer id) {
		Query q = em.createQuery("SELECT u.chat FROM UserHasChat u WHERE u.user.id=:X");
		q.setParameter("X", id);
		try {
			List<Chat> lchat = q.getResultList();
			if (lchat.size() > 0){
				return lchat;
			}else{
				return new ArrayList<Chat>();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<Chat>();
		}	
	
	}

	@Override
	public Chat findChatById(Integer id) {
		Chat u = new Chat();
		u = em.find(Chat.class, id);
		if (u == null){
			
			return new Chat();
		}
		return em.find(Chat.class, id);
	}
	
	@Override
	public List<UserHasChat> findUHCByChat(Integer idChat) {
		Query q = em.createQuery("SELECT u FROM UserHasChat u WHERE u.chat.id=:X");
		q.setParameter("X", idChat);
		try {
			List<UserHasChat> lchat = q.getResultList();
			if (lchat.size() > 0){
				return lchat;
			}else{
				return new ArrayList<UserHasChat>();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<UserHasChat>();
		}	
	}
	@Override
	public int delUHCByChat(Integer idChat) {
		List<UserHasChat> liste = findUHCByChat(idChat);
		System.out.println(liste.size());
		if (liste.size()>0) {
			for (UserHasChat userHasChat : liste) {
				EntityManager em = PersistenceManager.getEntityManager();
				EntityTransaction et = em.getTransaction();
				try {
					Integer vv = userHasChat.getId();
					System.out.println("try");
					et.begin();
					Query q = em.createQuery("delete from UserHasChat u where u.id=:X");
					q.setParameter("X", vv);
					q.executeUpdate();
					et.commit();
					em.close(); 
					return 1;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("catch");
					em.close();
					return 0;
				}	
			}
			
		}
		return 0;
	}
	
	@Override
	public int delChatById(Integer id) {
		// TODO Auto-generated method stub
		try {
			delUHCByChat(id);
		} catch (Exception e) {
			System.out.println("non non non");
		}
		if (String.valueOf(findChatById(id).getId()).equals("null")){
			return 0;
		}
		
		EntityManager em = PersistenceManager.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			Query q = em.createQuery("delete from Chat u where u.id=:X");
			q.setParameter("X", id);
			q.executeUpdate();
			et.commit();
			em.close(); 
			return 1;
		} catch (Exception e) {
			em.close();
			return 0;
		}	
	}
}
