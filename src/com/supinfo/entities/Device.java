package com.supinfo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.glassfish.jersey.server.JSONP;


@Entity
@Table(name="devise")
public class Device implements Serializable {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(nullable=false)
		private Integer id;
		private String numero;
		private String marque;
		private int type;
		private String model;
		private Date dateCreate;
		private Date dateUpdate;
		
		@ManyToOne
		@JoinColumn(name="idUser")
		private User user;

		public Integer getId() {
		
			return id;
			
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public String getMarque() {
			return marque;
		}

		public void setMarque(String marque) {
			this.marque = marque;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		
		public User getUser() {
			return user;
		}

		
		public void setUser(User user) {
			this.user = user;
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

		public Device() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
