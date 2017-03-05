package com.supinfo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="user")
public class User implements Serializable {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Integer id;
		private String login;
		private String mdp;
		private String nom;
		private String prenom;
		private Date dateNaiss;
		private String email;
		private String bio;
		private String statut;
		private String token;
		private Date tokenExpire;
		private Date dateCreate;
		private Date dateUpdate;
		private Date lastConnect;
		
		@OneToMany(mappedBy="user")
		private List<Device> devices;
		
		@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
		private List<Message> messages;
		
		@OneToMany(mappedBy="user")
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
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getMdp() {
			return mdp;
		}
		public void setMdp(String mdp) {
			this.mdp = mdp;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public Date getDateNaiss() {
			return dateNaiss;
		}
		public void setDateNaiss(Date dateNaiss) {
			this.dateNaiss = dateNaiss;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getBio() {
			return bio;
		}
		public void setBio(String bio) {
			this.bio = bio;
		}
		public String getStatut() {
			return statut;
		}
		public void setStatut(String statut) {
			this.statut = statut;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
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
		public Date getLastConnect() {
			return lastConnect;
		}
		public void setLastConnect(Date lastConnect) {
			this.lastConnect = lastConnect;
		}
		
		public List<Device> getDevices() {
			return devices;
		}
		public void setDevice(List<Device> devices) {
			this.devices = devices;
		}
		public List<Message> getMessages() {
			return messages;
		}
		public void setMessages(List<Message> messages) {
			this.messages = messages;
		}
		
		public Date getTokenExpire() {
			return tokenExpire;
		}
		public void setTokenExpire(Date tokenExpire) {
			this.tokenExpire = tokenExpire;
		}
		public void setDevices(List<Device> devices) {
			this.devices = devices;
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	
}
