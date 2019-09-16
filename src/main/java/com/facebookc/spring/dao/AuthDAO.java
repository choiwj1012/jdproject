package com.facebookc.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("authDAO")
public class AuthDAO{
	
	@Autowired
	private SqlSession sqlSession;
	 
    public List<Map<String, Object>> selectUserLogin(Map<String, Object> map) throws Exception{
        List<Map<String, Object>> result = sqlSession.selectList("authSql.selectUserLogin", map);
        return result ; 
    }
    
    public List<Map<String, Object>> selectUserInfo(Map<String, Object> map) throws Exception{
        List<Map<String, Object>> result = sqlSession.selectList("authSql.selectUserInfo", map);
        return result ; 
    }
    
}
