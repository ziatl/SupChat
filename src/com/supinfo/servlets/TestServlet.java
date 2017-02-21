package com.supinfo.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.database.PersistenceManager;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManager em= PersistenceManager.getEntityManager();
		Query q = em.createQuery("select u from User u");
		q.getResultList();
		
		req.getServletContext().getRequestDispatcher("/index.html").forward(req, resp);	
	}
}
