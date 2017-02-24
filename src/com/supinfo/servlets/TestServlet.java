package com.supinfo.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.dao.CrudDaoImpl;
import com.supinfo.database.PersistenceManager;
import com.supinfo.entities.User;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManager em= PersistenceManager.getEntityManager();
//		Query q = em.createQuery("select u from User u");
//		q.getResultList();
		CrudDaoImpl cr = new CrudDaoImpl();
//		cr.getAllUser();
//		
//		User u = new User();
//		u.setNom("liloudini");
//		u.setPrenom("azzi");
//		
//		User mm = cr.addUser(u);
//		if (mm == null){
//			System.out.println("Insertion echoue");
//		}else{
//			System.out.println("Insertion reussit");
//		}
		List<User> ll = cr.getUserByString("a");
		System.out.println(ll.size());
		for (User user : ll) {
			System.out.println(user.getNom());
		}
		
		
		
		req.getServletContext().getRequestDispatcher("/index.html").forward(req, resp);	
	}
}
