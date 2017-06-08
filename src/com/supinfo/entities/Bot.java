package com.supinfo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bot")
public class Bot {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nom;
	private String wordBan;
	private String wordKick;
	private boolean kickable;
	private boolean banable;
	private String commandes;
	@ManyToOne
	@JoinColumn(name="idChat")
	private Chat chat;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getWordBan() {
		return wordBan;
	}
	public void setWordBan(String wordBan) {
		this.wordBan = wordBan;
	}
	public String getWordKick() {
		return wordKick;
	}
	public void setWordKick(String wordKick) {
		this.wordKick = wordKick;
	}
	public boolean isKickable() {
		return kickable;
	}
	public void setKickable(boolean kickable) {
		this.kickable = kickable;
	}
	public boolean isBanable() {
		return banable;
	}
	public void setBanable(boolean banable) {
		this.banable = banable;
	}
	public String getCommandes() {
		return commandes;
	}
	public void setCommandes(String commandes) {
		this.commandes = commandes;
	}
	public Chat getChat() {
		return chat;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	@Override
	public String toString() {
		return "Bot [id=" + id + ", nom=" + nom + ", wordBan=" + wordBan + ", wordKick=" + wordKick + ", kickable="
				+ kickable + ", banable=" + banable + ", commandes=" + commandes + ", chat=" + chat + "]";
	}
	
	
	
}
