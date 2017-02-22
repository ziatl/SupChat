package com.supinfo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.database.PersistenceManager;
import com.supinfo.entities.User;
import com.supinfo.interfaces.ICrud;

public class CrudDaoImpl implements ICrud {

	public EntityManager em;
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
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(u);
			et.commit();
			em.close();
			return u;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}

	@Override
	public List<User> getAllUser() {
		Query q = em.createNamedQuery("select u from User u");
		List<User> ll = q.getResultList();
		return ll;
	}

	@Override
	public List<User> getUserByString(String mc) {
		Query q = em.createNamedQuery("select u from User u where u.nom=:X or prenom=:X");
		q.setParameter("X", "%"+mc+"%");
		List<User> ll = q.getResultList();
		return ll;	
	}
	
	
	
}
