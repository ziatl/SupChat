package com.supinfo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="parametre")
public class Parametre implements Serializable {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Integer id;
		private boolean push;
		private boolean audio;
		private boolean video;
		private boolean son;
		private boolean search;
	
		@OneToOne
		@JoinColumn(name="idUser")
		private User user;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public boolean isPush() {
			return push;
		}
		public void setPush(boolean push) {
			this.push = push;
		}
		public boolean isAudio() {
			return audio;
		}
		public void setAudio(boolean audio) {
			this.audio = audio;
		}
		public boolean isVideo() {
			return video;
		}
		public void setVideo(boolean video) {
			this.video = video;
		}
		public boolean isSon() {
			return son;
		}
		public void setSon(boolean son) {
			this.son = son;
		}
		public boolean isSearch() {
			return search;
		}
		public void setSearch(boolean search) {
			this.search = search;
		}
		public Parametre() {
			super();
			// TODO Auto-generated constructor stub
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}				
		
}
