package com.supinfo.interfaces;

import java.io.Serializable;
import java.util.List;

import com.supinfo.entities.Message;
import com.supinfo.entities.User;


public interface ICrud {
	//Crud du User
	public User addUser(User u);
	public User findUserById(Integer id);
	public int delUserById(Integer id);
	public User updateUser(User u);
	
	public List<User> getAllUser();
	public List<User> getUserByString(String mc);
	

}
