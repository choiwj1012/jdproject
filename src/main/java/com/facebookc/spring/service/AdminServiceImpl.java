package com.facebookc.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.facebookc.spring.dao.AdminDAO;

//@Service
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Resource(name = "adminDAO")
	private AdminDAO adminDAO;

	@Override
	public int selectUserTotal() throws Exception {
		// TODO Auto-generated method stub
		int totalCnt = adminDAO.selectUserTotal();

		return totalCnt;
	}

	@Override
	public List<Map<String, Object>> selectUser(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultContent = new ArrayList<Map<String, Object>>();

		resultContent = adminDAO.selectUser(map);

		return resultContent;
	}

}
