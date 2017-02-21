package com.supinfo.entities;

import java.io.Serializable;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_has_chat")
public class UserHasChat implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Double id;
	
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;
	@ManyToOne
	@JoinColumn(name="idChat")
	private Chat chat;
	
	private boolean admin;
	private int status; 
	private String detials;
	private boolean push;
	
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Chat getChat() {
		return chat;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDetials() {
		return detials;
	}
	public void setDetials(String detials) {
		this.detials = detials;
	}
	public boolean isPush() {
		return push;
	}
	public void setPush(boolean push) {
		this.push = push;
	}
	public UserHasChat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserHasChat(User user, Chat chat, boolean admin, int status, String detials, boolean push) {
		super();
		this.user = user;
		this.chat = chat;
		this.admin = admin;
		this.status = status;
		this.detials = detials;
		this.push = push;
	}
	
	
	
	
	
	
	

}
