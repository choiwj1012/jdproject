package com.facebookc.spring.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.facebookc.spring.dao.MainDAO;

//@Service
@Service("mainService")
public class MainServiceImpl implements MainService {

	@Resource(name = "mainDAO")
	private MainDAO mainDAO;

	public int userduplicationCheck(Map<String, Object> map)  throws Exception{

		int userCnt  = mainDAO.userDuplicationCheck(map);
		
		return userCnt;
	}
	
	public void saveUser(Map<String, Object> map) {

		mainDAO.saveUser(map);	
		
	}
	
}
