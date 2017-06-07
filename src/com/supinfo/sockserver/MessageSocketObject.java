package com.supinfo.sockserver;

public class MessageSocketObject {
		private int id;
		private String content;
		private int type;
		private int idUser;
		private String author;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public int getIdUser() {
			return idUser;
		}
		public void setIdUser(int idUser) {
			this.idUser = idUser;
		}
		
		public String getAuthor() {
			return author;
		}
		
		public void setAuthor(String author) {
			this.author = author;
		}
		
		public MessageSocketObject() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
