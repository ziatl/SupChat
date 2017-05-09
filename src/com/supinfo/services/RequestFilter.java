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

import com.sun.research.ws.wadl.Option;
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
		System.out.println(requestContext.getMethod());
		System.out.println(requestContext.getHeaderString("Content-Type"));
		System.out.println(requestContext.getHeaders());
		System.out.println(requestContext.getUriInfo().getQueryParameters());
		System.out.println(requestContext.getUriInfo().getPathParameters());
		return;
	}
}
	