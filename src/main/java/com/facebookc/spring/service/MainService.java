package com.facebookc.spring.service;

import java.util.Map;

public interface MainService {
	
	int userduplicationCheck(Map<String, Object> map)  throws Exception;
	
	void saveUser(Map<String, Object> map)  throws Exception;
	
}

