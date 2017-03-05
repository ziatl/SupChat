package com.supinfo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.database.PersistenceManager;
import com.supinfo.entities.Chat;

import com.supinfo.entities.User;
import com.supinfo.entities.UserHasChat;
import com.supinfo.interfaces.ICrud;

public class CrudDaoImpl implements ICrud {

	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public CrudDaoImpl() {
		super();
		em = PersistenceManager.getEntityManager();
	}

	//CRUD User
	@Override
	public User addUser(User u) {
		EntityTransaction et = em.getTransaction();
		try {
			
			et.begin();
			em.persist(u);
			et.commit();
			em.close();
			return u;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}

	@Override
	public User findUserById(Integer id) {
		User u = new User();
		u = em.find(User.class, id);
		if (u == null){
			
			return new User();
		}
		return em.find(User.class, id);
	}

	@Override
	public int delUserById(Integer id) {
		// TODO Auto-generated method stub
		EntityManager em = PersistenceManager.getEntityManager();
		EntityTransaction et = em.getTransaction();
		User u = em.find(User.class, id);
		
		try {
			et.begin();
			em.remove(u);
			et.commit();
			em.close(); 
			return 1;
		} catch (Exception e) {
			em.close();
			return 0;
		}	
		
	}
	
	@Override
	public User updateUser(User u) {
		if (String.valueOf(findUserById(u.getId()).getId()).equals("null")){
			return new User();
		}
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(u);
			et.commit();
			em.close();
			return u;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new User();
		}	
	}

	@Override
	public List<User> getAllUser() {
		Query q = em.createQuery("select u from User u");
		List<User> ll = q.getResultList();
		return ll;
	}

	@Override
	public List<User> getUserByString(String mc) {
		Query q = em.createQuery("select u from User u where u.nom like :X or u.prenom like :X");
		q.setParameter("X", "%"+mc+"%");
		return q.getResultList();
	}
	@Override
	public User login(String login, String pwd) {
		Query q = em.createQuery("select u from User u where u.login=:X and u.mdp=:Y");
		q.setParameter("X", login);
		q.setParameter("Y", pwd);
		
		try {
			return (User) q.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return new User();
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.supinfo.interfaces.ICrud#addChat(com.supinfo.entities.Chat)
	 * 
	 * 
	 */

	@Override
	public Chat addChat(Chat c) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(c);
			et.commit();
			em.close();
			return c;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Chat();
		}	
	}

	@Override
	public Chat findChatById(Integer id) {
		Chat c = em.find(Chat.class, id);
		if (c==null){
			return new Chat();
		}
		return c;
	}

	@Override
	public int delChatById(Integer id) {
		if (String.valueOf(findUserById(id).getId()).equals("null")){
			return 0;
		}
		
		EntityManager em = PersistenceManager.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			Query q = em.createQuery("delete from User u where u.id=:X");
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

	@Override
	public Chat updateChat(Chat c) {
		if (String.valueOf(findChatById(c.getId()).getId()).equals("null")){
			return new Chat();
		}
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(c);
			et.commit();
			em.close();
			return c;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Chat();
		}	
		
	}

	@Override
	public List<Chat> getAllChat() {
		Query q = em.createQuery("select c from Chat c");
		List<Chat> cc = q.getResultList();
		return cc;
	}

	@Override
	public List<Chat> getChatByString(String mc) {
		Query q = em.createQuery("select c from Chat c where c.libelle like:X");
		q.setParameter("X", "%"+mc+"%");
		List<Chat> cc = q.getResultList();
		return cc;	
	}

	@Override
	public List<Chat> getChatByUser(Integer idUser) {
		Query q = em.createQuery("select c from Chat c where c.id in (select h.idChat from User_Has_Chat h where h.idUser=:X)");
		q.setParameter("X", idUser);
		return q.getResultList();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.supinfo.interfaces.ICrud#addUHC(com.supinfo.entities.UserHasChat)
	 */

	@Override
	public UserHasChat addUHC(UserHasChat uhc) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(uhc);
			et.commit();
			em.close();
			return uhc;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new UserHasChat();
		}	
	}

	@Override
	public UserHasChat findUHC(Integer id) {
		return em.find(UserHasChat.class, id);
	}

	@Override
	public int delUHC(Integer id) {
		EntityManager em = PersistenceManager.getEntityManager();
		EntityTransaction et = em.getTransaction();
		UserHasChat uhc = em.find(UserHasChat.class, id);
		try {
			et.begin();
			em.remove(uhc);
			et.commit();
			em.close(); 
			return 1;
		} catch (Exception e) {
			em.close();
			return 0;
		}	
	}

	@Override
	public UserHasChat updateUHC(UserHasChat uhc) {
		if (String.valueOf(findUHC(uhc.getId()).getId()).equals("null")){
			return new UserHasChat();
		}
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(uhc);
			et.commit();
			em.close();
			return uhc;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}

	@Override
	public List<UserHasChat> getAllUHC() {
		Query q = em.createQuery("select u from UserHasChat u");
		List<UserHasChat> uhc = q.getResultList();
		return uhc;
	}	
}
