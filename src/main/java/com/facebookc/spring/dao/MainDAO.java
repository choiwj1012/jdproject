package com.facebookc.spring.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("mainDAO")
public class MainDAO {

	@Autowired
	private SqlSession sqlSession;

	public int userDuplicationCheck(Map<String, Object> map) {

		int result = 0;
		result = sqlSession.selectOne("mainSql.userDuplicationCheck", map);
		return result;
	}

	public void saveUser(Map<String, Object> map) {

		sqlSession.insert("mainSql.saveUser", map);

	}

}
