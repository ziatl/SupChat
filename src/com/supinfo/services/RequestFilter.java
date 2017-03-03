package com.supinfo.services;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class RequestFilter implements ContainerRequestFilter {
	
	private static final String AUTHORIZATION_HEADER_KEY = "authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("Entre du filter");
		String test = requestContext.getUriInfo().getPath();
		if (acceptUrl("/rest/login")){
			
		}else{
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		String token = "euiereirniu";

		System.out.println(authorizationHeader);
		String valeur = authorizationHeader.substring("Basic".length()).trim();
		System.out.println(token);
		
		//autorise
		if (valeur.equals(token)){
			System.out.println(authorizationHeader);
//			Response autho = Response.status(Status.OK).entity("Vous etes bien connecte")
//					.build();
//			requestContext.abortWith(autho);		
				return;
		   }
			//Non autohorise
			Response unAuthorizedStatus = Response.status(Status.UNAUTHORIZED).entity("Vous n'avez pas les bonnes information")
					.build();
			requestContext.abortWith(unAuthorizedStatus);					
		}
	}
	
	public Boolean acceptUrl(String url){
		
		
		return true;
	}
}
	