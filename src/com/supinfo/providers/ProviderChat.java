package com.supinfo.providers;

import java.util.UUID;

public class ProviderChat {
	
	public static String genrateToken(){
		String key = UUID.randomUUID().toString();
		System.out.println(key);
		return  key;                                                                                         
	}

}
