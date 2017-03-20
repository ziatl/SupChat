package com.supinfo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="chat")
public class Chat implements Serializable {
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
				
//		//@JoinTable(name="user_has_cours",joinColumns=@JoinColumn(name="chat_id",referencedColumnName="id"),inverseJoinColumns=@JoinColumn(name="user_id",referencedColumnName="id"))
//		@ManyToMany
//		@JoinTable(name="user_has_chat")
//		private List<User> users;
		@JsonIgnore
		@OneToMany(mappedBy="chat")
		private List<Message> messages;
		@JsonIgnore
		@OneToMany(mappedBy="chat")
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
//		public List<User> getUsers() {
//			return users;
//		}
//		public void setUsers(List<User> users) {
//			this.users = users;
//		}
		public List<Message> getMessages() {
			return messages;
		}
		public void setMessages(List<Message> messages) {
			this.messages = messages;
		}
		public Chat() {
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
