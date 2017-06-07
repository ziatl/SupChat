package com.supinfo.interfaces;

import java.io.Serializable;
import java.util.List;

import com.supinfo.entities.Chat;
import com.supinfo.entities.Device;
import com.supinfo.entities.Message;
import com.supinfo.entities.Parametre;
import com.supinfo.entities.ResetPassword;
import com.supinfo.entities.User;
import com.supinfo.entities.UserHasChat;


public interface ICrud {
	//Crud du User
	public User addUser(User u);
	public User findUserById(Integer id);
	public User findUserByToken(String token);
	public int delUserById(Integer id);
	public User updateUser(User u);
	
	public List<User> getAllUser();
	public List<User> getUserByString(String mc);
	
	public User login(String login,String mdp);
	//Crud Chat
	public Chat addChat(Chat c);
	public Chat findChatById(Integer id);
	public int delChatById(Integer id);
	public Chat updateChat(Chat c);
	
	public List<Chat> getAllChat();
	public List<Chat> getChatByString(String mc);
	public List<Chat> getChatByUser(Integer idUser);
	
	//Crud Parametre
	public Parametre addParametre(Parametre p);
	public Parametre findParametreById(Integer id);
	public int delParametreId(Integer id);
	public Parametre updateParametre(Parametre p);
	
	public List<Parametre> getAllParametre();
	public Parametre getParametreByUser(Integer idUser);	
	
	//Crud Device
	public Device addDevice(Device d);
	public Device findDeviceById(Integer id);
	public int delDeviceId(Integer id);
	public Device updateDevice(Device d);
	
	public List<Device> getAllDevice();
	public List<Device> getDeviceByMc(String mc);
	public List<Device> getDeviceByUser(Integer idUser);
	
	//UserHasChat
	public UserHasChat addUHC(UserHasChat uhc);
	public UserHasChat findUHC(Integer id);
	public int delUHC(Integer id);
	public UserHasChat updateUHC (UserHasChat uhc);
	public List<UserHasChat> getAllUHC();
	
	//RestPassword
	public ResetPassword addReset(Integer idUser);

	
	
	

}
