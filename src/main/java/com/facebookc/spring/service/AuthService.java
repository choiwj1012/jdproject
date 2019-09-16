package com.facebookc.spring.service;

import java.util.Map;

public interface AuthService {
	
	Map<String, Object> selectUserLogin(Map<String, Object> mapa)  throws Exception;
	
	Map<String, Object> selectUserInfo(Map<String, Object> mapa)  throws Exception;
	
}

