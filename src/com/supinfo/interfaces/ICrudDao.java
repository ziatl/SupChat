package com.supinfo.interfaces;

import java.util.List;

import com.supinfo.entities.User;

public interface ICrudDao {
	//CRUD general de la table User
	public int addUser(User user);
	public User findUserById(int id);
	public int delUserById(int id);
	public int updateUser(User user);
	public List<User> getUsers();
	public List<User> getUsersByString(String string);
	

}
