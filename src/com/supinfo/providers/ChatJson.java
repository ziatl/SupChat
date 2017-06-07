package com.supinfo.providers;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.supinfo.entities.Message;
import com.supinfo.entities.UserHasChat;

public class ChatJson {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String libelle;
	private String detail;
	private int statut;
	private int type;
	private Date dateCreate;
	private Date dateUpdate;
	private Integer creator;
	private List<Message> messages;
	private List<UserHasChat> userHasChats;
	
	
	public List<UserHasChat> getUserHasChats() {
		return userHasChats;
	}
	public void setUserHasChats(List<UserHasChat> userHasChats) {
		this.userHasChats = userHasChats;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getStatut() {
		return statut;
	}
	public void setStatut(int statut) {
		this.statut = statut;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
//	public List<User> getUsers() {
//		return users;
//	}
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public ChatJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
}
