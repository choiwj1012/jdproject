package com.facebookc.spring.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.facebookc.spring.dao.CronDAO;

//@Service
@Service("cronService")
public class CronServiceImpl implements CronService {
	
	private final Logger logger = LoggerFactory.getLogger(CronServiceImpl.class);
	
	@Resource(name="mainService")
	private MainService mainService;
	
	@Resource(name = "cronDAO")
	private CronDAO cronDAO;

}
