package com.supinfo.providers;

import java.util.Random;
import java.util.UUID;

public class ProviderChat {
	
	public static String genrateToken(){
		String key = UUID.randomUUID().toString();
		System.out.println(key);
		return  key;                                                                                         
	}

	public static String newPassword() {
		// TODO Auto-generated method stub
		String mdp = randPass();
		
		return mdp ;
	}
	
	public static String randPass(){
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}

}
