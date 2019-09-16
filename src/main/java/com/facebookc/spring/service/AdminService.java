package com.facebookc.spring.service;

import java.util.List;
import java.util.Map;

public interface AdminService {
	
	int selectUserTotal() throws Exception;
	
	List<Map<String, Object>> selectUser(Map<String, Object> map) throws Exception;
	
}

