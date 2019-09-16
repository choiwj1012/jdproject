package com.facebookc.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("adminDAO")
public class AdminDAO {

	@Autowired
	private SqlSession sqlSession;

	public int selectUserTotal() {
		// TODO Auto-generated method stub
		int result = 0;
		result = sqlSession.selectOne("adminSql.selectUserTotal");

		return result;
	}

	public List<Map<String, Object>> selectUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> result = sqlSession.selectList("adminSql.selectUser", map);

		return result;
	}
}
