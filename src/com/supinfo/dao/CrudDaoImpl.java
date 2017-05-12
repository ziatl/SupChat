package com.supinfo.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.database.PersistenceManager;
import com.supinfo.entities.Chat;
import com.supinfo.entities.Device;
import com.supinfo.entities.Parametre;
import com.supinfo.entities.User;
import com.supinfo.entities.UserHasChat;
import com.supinfo.interfaces.ICrud;
import com.supinfo.providers.ProviderChat;

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
	public User findUserByToken(String token) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.token=:X");
		q.setParameter("X", token);
		try {
			User user = (User) q.getSingleResult();
			if (user==null){
				return new User();
			}else{
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new User();
		}	
		
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
			User user = (User) q.getSingleResult();
			user.setToken(ProviderChat.genrateToken());
			Date date = new Date();
			System.out.println(date);
			date.setMinutes(date.getMinutes()+20);
			System.out.println(date);
			user.setTokenExpire(date);
			CrudDaoImpl cc = new CrudDaoImpl();
			cc.updateUser(user);
			return user;
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
	
	//CRUD Parametre
	@Override
	public Parametre addParametre (Parametre p) {
		EntityTransaction et = em.getTransaction();
		try {
			
			et.begin();
			em.persist(p);
			et.commit();
			em.close();
			return p;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	@Override
	public Parametre updateParametre (Parametre p) {
		EntityTransaction et = em.getTransaction();
		try {
			
			et.begin();
			em.merge(p);
			et.commit();
			em.close();
			return p;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	@Override
	public Parametre findParametreById(Integer id) {
		Parametre p = new Parametre();
		p = em.find(Parametre.class, id);
		if (p == null){
			
			return new Parametre();
		}
		return em.find(Parametre.class, id);
	};
	@Override
	public int delParametreId(Integer id) {
		EntityManager em = PersistenceManager.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Parametre p = em.find(Parametre.class, id);			
		try {
			et.begin();
			em.remove(p);
			et.commit();
			em.close(); 
			return 1;
			} catch (Exception e) {
			em.close();
			return 0;
			}
	};
	@Override
	public List<Parametre> getAllParametre() {
		Query q = em.createQuery("SELECT p FROM Parametre p");
		List<Parametre> lp = q.getResultList();
		return lp;
	};
	@Override
	public List<Parametre> getParametreByUser(Integer idUser) {
		Query q = em.createQuery("SELECT p FROM Parametre p WHERE p.user_id=:X");
		q.setParameter("X",idUser);
		List<Parametre> lp = q.getResultList();
		return lp;
	};
	
	//Crud Device
	@Override
	public Device addDevice(Device d){
		EntityTransaction et = em.getTransaction();
		try {
			
			et.begin();
			em.persist(d);
			et.commit();
			em.close();
			return d;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	};
	@Override
	public Device findDeviceById(Integer id) {
		Device d=new Device();
		d=em.find(Device.class, id);
		if (d==null){
			
		return new Device();			
		
		}
		return em.find(Device.class, id);
	};
	@Override
	public int delDeviceId(Integer id) {
		EntityManager em = PersistenceManager.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Device d = em.find(Device.class, id);			
		try {
			et.begin();
			em.remove(d);
			et.commit();
			em.close(); 
			return 1;
			} catch (Exception e) {
			em.close();
			return 0;
			}
	};
	@Override
	public Device updateDevice(Device d) {
		EntityTransaction et = em.getTransaction();
		try {
			
			et.begin();
			em.merge(d);
			et.commit();
			em.close();
			return d;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	};
	@Override
	public List<Device> getAllDevice() {
		Query q=em.createQuery("SELECT d FROM Device d");
		List<Device>ld=q.getResultList();
		return ld;
	};
	@Override
	public List<Device> getDeviceByMc(String mc) {
		Query q=em.createQuery("SELECT d FROM Device d WHERE d.numero like :X OR d.marque like :X OR d.model like:X");
		q.setParameter("X", "%"+mc+"%");
		List<Device> ld = q.getResultList();
		return ld;
	};
	@Override
	public List<Device> getDeviceByUser(Integer idUser) {
		Query q=em.createQuery("SELECT d FROM Device d WHERE d.user_id=:X");
		q.setParameter("X", idUser);
		List<Device>ld=q.getResultList();
		return ld;
	};
}
