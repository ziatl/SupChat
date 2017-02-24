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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
@Entity
@Table(name="message")
public class Message implements Serializable {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Integer id;
		private String detail;
		private String url;
		private String urlLite;
		private int type;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date dateCreate;
		private Date dateUpdate;
		
		@ManyToOne
		@JoinColumn(name="idChat")
		private Chat chat;
		
		@ManyToOne
		@JoinColumn(name="idUser")
		private User user;
		
		@ManyToMany
		@JoinTable(name="lecture",joinColumns=@JoinColumn(name="idMessage",referencedColumnName="id"),inverseJoinColumns=@JoinColumn(name="idUser",referencedColumnName="id"))
		private List<User> users;
		
		
		public Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getUrlLite() {
			return urlLite;
		}
		public void setUrlLite(String urlLite) {
			this.urlLite = urlLite;
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
		public Chat getChat() {
			return chat;
		}
		public void setChat(Chat chat) {
			this.chat = chat;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public Message() {
			super();
			// TODO Auto-generated constructor stub
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}
		
		
		
}
