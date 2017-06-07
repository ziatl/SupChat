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
		Query q = em.createQuery("SELECT c From Chat c where c.statut = 1 and c.id IN (SELECT u.chat.id FROM UserHasChat u WHERE u.user.id=:X)");
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
	public List<UserHasChat> findUserByUserId(Integer id) {
		Query u =  em.createQuery(""
				+ "SELECT h FROM UserHasChat h WHERE h.user.id!=:X AND h.chat.id IN "
				+ "(SELECT c.id FROM Chat c WHERE c.id IN "
				+ "(SELECT d.chat.id FROM UserHasChat d WHERE d.user.id =:X) and c.type = 0)  and h.status = 1");
		//Soit on fait un  OR ici pour Ajouter des conditions selon le statu, pending, Ban , Accepted
		u.setParameter("X", id);
		return u.getResultList();	
	}
	@Override
	public List<User> findUserByUserIdNotAdd(Integer id) {
		Query u =  em.createQuery(""
				+ "SELECT u FROM User u WHERE u.id NOT IN "
				+ "(SELECT h.user.id FROM UserHasChat h WHERE h.chat.id IN "
				+ "(SELECT c.id FROM Chat c WHERE c.id IN "
				+ "(SELECT d.chat.id FROM UserHasChat d WHERE d.user.id =:X) and c.type = 0) and h.status !=5) and u.id !=:X");
		u.setParameter("X", id);
		return u.getResultList();	
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
	public List<Chat> getChatPublic() {
		Query q = em.createQuery("SELECT c FROM Chat c WHERE c.type = 2");
		return q.getResultList();
	}
	@Override
	public List<Chat> getChatPrivate(Integer idUser) {
		Query q = em.createQuery("SELECT c FROM Chat c WHERE c.type = 0 AND c.id IN (SELECT h.chat.id FROM UserHasChat h WHERE h.user.id =:X)");
		q.setParameter("X", idUser);
		return q.getResultList();
	}
	@Override
	public Integer delUserHasCHat(Integer idUser, Integer idChat) {
		// TODO Auto-generated method stub
		
		EntityManager em = PersistenceManager.getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Query q = em.createQuery("delete from UserHasChat u where u.user.id=:X and u.chat.id=:Y");
			q.setParameter("X", idUser);
			q.setParameter("Y", idChat);
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
		EntityManager em = PersistenceManager.getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Query q1 = em.createQuery("delete from UserHasChat u where u.chat.id=:X");
			q1.setParameter("X", id);
			q1.executeUpdate();
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
	@Override
	public User addContact(Integer idUser1, Integer idUser2,String libelle) {
		List<UserHasChat> liste1 = new ArrayList<UserHasChat>();
		List<UserHasChat> liste2 = new ArrayList<UserHasChat>();
		UserHasChat uhc1 = new UserHasChat();
		UserHasChat uhc2 = new UserHasChat();
		Integer idH1 = 0;
		Integer idH2 = 0;
		Chat chat = new Chat();
		
		//Verifiation si uhc entre les deux contacts
		Query q = em.createQuery("Select h FROM UserHasChat h where h.user.id =:X and h.chat.type = 0");
		q.setParameter("X", idUser1);
		liste1 = q.getResultList();
		
		Query q2 = em.createQuery("Select h FROM UserHasChat h where h.user.id =:X and h.chat.type = 0");
		q2.setParameter("X", idUser2);
		liste2 = q2.getResultList();
		
		Boolean trouver = false;
		for (UserHasChat h1 : liste1) {
			for (UserHasChat h2 : liste2) {
				if (h1.getChat().getId() == h2.getChat().getId()) {
					trouver = true;
					idH1 = h1.getId();
					idH2 = h2.getId();
					System.out.println(idH1+" "+idH2);
					break;
				}
			}
		}
		
		if (trouver){
			System.out.println("existe deja");
			User user1 = crudDao.findUserById(idUser1);
			User user2 = crudDao.findUserById(idUser2);
			uhc1 = crudDao.findUHC(idH1);
			uhc2 = crudDao.findUHC(idH2);
			chat = crudDao.findChatById(uhc1.getChat().getId());
			
			uhc1.setStatus(4);
			uhc2.setStatus(2);
			System.out.println("****");
			System.out.println(uhc1.getId());
			System.out.println(uhc2.getId());
			System.out.println("*****");
			crudDao = new CrudDaoImpl();
			crudDao.updateUHC(uhc1);
			crudDao = new CrudDaoImpl();
			crudDao.updateUHC(uhc2);
			return user2;
		}else{		
		//Action a realiser si pas de contact entre les deux
		System.out.println("existe pas entre les deux");
		User user1 = crudDao.findUserById(idUser1);
		User user2 = crudDao.findUserById(idUser2);
		//crreation du chat entre les 2 user et automatique UserHasChat pour le premier User
		
		chat.setType(0);
		chat.setStatut(0);
		chat.setCreator(idUser1);
		chat.setLibelle(libelle);
		chat.setDateCreate(new Date());
		chat.setDateUpdate(new Date());

		//UserHasChat pour le second avec statut = 2 (pending)
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(chat);
			
			
			
			uhc1.setChat(chat);
			uhc1.setUser(user1);
			uhc1.setAdmin(true);
			uhc1.setStatus(4);
			User u1 = crudDao.findUserById(idUser1);
			uhc1.setLibelle(u1.getPrenom() + " "+ u1.getNom());
			em.persist(uhc1);
			
			
			uhc2.setChat(chat);
			uhc2.setUser(user2);
			uhc2.setAdmin(false);
			uhc2.setStatus(2);
			User u = crudDao.findUserById(idUser2);
			uhc2.setLibelle(u.getPrenom() + " "+ u.getNom());
			em.persist(uhc2);

			et.commit();
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
		return user2;
	  }
		
	}
	@Override
	public int updateUHCStatus(Integer id1,Integer id2,Integer status) {
		UserHasChat uhc = new UserHasChat();
		try {
			// Query q = em.createQuery("SELECT us FROM UserHasChat us WHERE
			// us.user.id =:X AND us.chat.id IN (SELECT c FROM Chat c JOIN
			// UserHasChat us ON us.chat.id = c.id WHERE c.type = 0 AND
			// us.user.id =:Y)");
			// tous les chat dont l id du user2
			
			List<UserHasChat> listeContact = findUserByUserId(id1);
			for (UserHasChat userHasChat : listeContact) {
				if (userHasChat.getUser().getId() == id2) {
					uhc = userHasChat;
					break;
				}
			}
			
			uhc.setStatus(status);
			crudDao.updateUHC(uhc);
			return uhc.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("catch");
			em.close();
			return 0;
		}	
	}
	
	//Invitation
	@Override
	public List<UserHasChat> getInvitation(Integer idUser) {
		Query u =  em.createQuery("SELECT h FROM UserHasChat h WHERE h.user.id=:X AND h.status = 2");
		//Soit on fait un  OR ici pour Ajouter des conditions selon le statu, pending, Ban , Accepted
		u.setParameter("X", idUser);
		List<UserHasChat> liste1 = u.getResultList();
		List<UserHasChat> liste2 = new ArrayList<UserHasChat>();
		for (UserHasChat userHasChat : liste1) {
			int idChat = userHasChat.getChat().getId();
			Query u2 =  em.createQuery("SELECT h FROM UserHasChat h WHERE h.user.id !=:X AND h.chat.id =:Y");
			u2.setParameter("X", idUser);
			u2.setParameter("Y", idChat);
			UserHasChat uhc = (UserHasChat) u2.getSingleResult();
			liste2.add(uhc);
		}
		
		List<UserHasChat> result = new ArrayList<UserHasChat>();
		for (UserHasChat uhc1 : liste1) {
			for (UserHasChat uhc2 : liste2) {
				if (uhc1.getChat().getId() == uhc2.getChat().getId()) {
					System.out.println("Id User = "+uhc2.getUser().getId());
					UserHasChat finale = uhc1;
					finale.setUser(uhc2.getUser());
					result.add(finale);
				}
			}
		}
		return result;
	}
	
	@Override
	public UserHasChat responseInvitation(Integer idUHC,Integer status) {
		UserHasChat uhc = crudDao.findUHC(idUHC);
		System.out.println(status + " Status");
		if (status == 1) {
			uhc.setStatus(status);
			crudDao.updateUHC(uhc);
			int idChat = uhc.getChat().getId();
			Query q = em.createQuery("select h from UserHasChat h where h.chat.id =:X and h.id !=:Y");
			q.setParameter("X", idChat);
			q.setParameter("Y", idUHC);
			UserHasChat uhc2 = (UserHasChat) q.getSingleResult();
			uhc2.setStatus(status);
			em = PersistenceManager.getEntityManager();
			crudDao = new CrudDaoImpl();
			crudDao.updateUHC(uhc2);
		}
		if (status == 3) {
			uhc.setStatus(status);
			crudDao.updateUHC(uhc);
		}
		
		return uhc;
	}
	
}
