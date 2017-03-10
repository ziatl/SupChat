package com.supinfo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import com.supinfo.dao.CrudDaoImpl;
import com.supinfo.entities.User;

@Provider
public class RequestFilter implements ContainerRequestFilter {
	
	private static final String AUTHORIZATION_HEADER_KEY = "authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private CrudDaoImpl crudDAO = new CrudDaoImpl();

	@SuppressWarnings("deprecation")
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("Entre du filter");
		String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		try {
			token = token.substring("Basic".length()).trim();
		} catch (Exception e) {
			System.out.println("requete sans token");
			Response unAuthorizedStatus = Response.status(Status.UNAUTHORIZED).entity("Requete sans token!!!")
					.build();
			requestContext.abortWith(unAuthorizedStatus);
		}
		String test = requestContext.getUriInfo().getPath();
		System.out.println(test);
		if (acceptUrl(test)){
			if (test.equals("rest/user")) {
				if(requestContext.getMethod().equals("POST")){
					return;
				}else{
					crudDAO = new CrudDaoImpl();
					User user = crudDAO.findUserByToken(token);
				
					if(user.getId()!=null){
						if (validToken(user.getTokenExpire())){
							Response unAuthorizedStatus = Response.status(Status.UNAUTHORIZED).entity("Token Exipire!!! Reconnectez vous pour un nouveau!")
									.build();
							requestContext.abortWith(unAuthorizedStatus);
						}else{
							Date date = new Date();
							System.out.println(date);
							date.setMinutes(date.getMinutes()+20);
							System.out.println(date);
							user.setTokenExpire(date);
							CrudDaoImpl cc = new CrudDaoImpl();
							cc.updateUser(user);
							//autorise
							return;
						}
					}else{
						//Non autohorise
						Response unAuthorizedStatus = Response.status(Status.UNAUTHORIZED).entity("Non authorise!!!")
									.build();
						requestContext.abortWith(unAuthorizedStatus);					
						}
				}
			}
			return;
		}else{
//		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		crudDAO = new CrudDaoImpl();
		User user = crudDAO.findUserByToken(token);
	
		if(user.getId()!=null){
			if (validToken(user.getTokenExpire())){
				Response unAuthorizedStatus = Response.status(Status.UNAUTHORIZED).entity("Token Exipire!!! Reconnectez vous pour un nouveau!")
						.build();
				requestContext.abortWith(unAuthorizedStatus);
			}else{
				Date date = new Date();
				System.out.println(date);
				date.setMinutes(date.getMinutes()+20);
				System.out.println(date);
				user.setTokenExpire(date);
				CrudDaoImpl cc = new CrudDaoImpl();
				cc.updateUser(user);
				//autorise
				return;
			}
		}else{
			//Non autohorise
			Response unAuthorizedStatus = Response.status(Status.UNAUTHORIZED).entity("Non authorise!!!")
						.build();
			requestContext.abortWith(unAuthorizedStatus);					
			}
		}
	}

	public Boolean validToken(Date dateExpire){
		if (dateExpire.before(new Date())) {
			System.out.println(dateExpire);
			System.out.println(new Date());
			System.out.println("Before");
			return true;
		}else{
			System.out.println(dateExpire);
			System.out.println(new Date());
			System.out.println("After");
			return false;
		}	
	}
	
	List<String> listUrl = new ArrayList<String>();
	public void remplir(){
		listUrl.add("rest/user");
		listUrl.add("rest/login");
	}
	public Boolean acceptUrl(String url){
		for (String u : listUrl) {
			if (url.contains(u)) {
				return true;
			}
		}
		return true;
	}
}
	