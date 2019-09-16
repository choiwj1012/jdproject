package com.facebookc.spring.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("cronDAO")
public class CronDAO {

	@Autowired
	private SqlSession sqlSession;

	public void setFbLinkStart() {
		// TODO Auto-generated method stub
		sqlSession.selectList("cronSql.setFbLinkStart");

		return;
	}

	public List<Map<String, Object>> setLinkServer(Map<String, Object> map) throws Exception {
		// return (List<Map<String,
		// Object>>)selectList("sample.selectBoardList", map);

		List<Map<String, Object>> result = sqlSession.selectList("cronSql.selectLinkTokenList", map);

		if (result.size() > 0) {
			map.put("flIdx", result.get(0).get("fl_idx"));
			sqlSession.selectOne("cronSql.modifyLinkTokenList", map);
		}

		return result;
	}

	public List<Map<String, Object>> getFbToken(Map<String, Object> map) throws Exception {
		// return (List<Map<String,
		
		List<Map<String, Object>> result = sqlSession.selectList("cronSql.getFbToken", map);

		return result;
	}

	public List<Map<String, Object>> chkFbToken(Map<String, Object> map) throws Exception {
		// System.out.println("sample2.chkFbCookie");

		List<Map<String, Object>> result = sqlSession.selectList("cronSql.chkFbToken", map);

		return result;

	}

	public void setFbLinkSuccess(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub

		sqlSession.selectList("cronSql.setFbLinkSuccess", map);

		return;
	}

	public void setFbTokenSuccess(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.selectList("cronSql.setFbTokenSuccess", map);
		sqlSession.selectList("cronSql.setFbLinkDetailTokenSuccess", map);

		return;
	}

	public void setFbLinkFail(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub

		sqlSession.selectList("cronSql.setFbLinkFail", map);

		return;
	}

	public void setFbUserUrlFail(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub

		sqlSession.selectList("cronSql.setFbUserUrlFail", map);

		return;
	}

	public void setFbUserUrlLinkFail(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub

		sqlSession.selectList("cronSql.setFbUserUrlLinkFail", map);

		return;
	}

	public void setFbTokenCancel(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("sample2.setFbCookieCancel");

		sqlSession.selectList("cronSql.setFbTokenCancel", map);

	}

	public List<Map<String, Object>> selectUserUrl() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> result = sqlSession.selectList("cronSql.selectUserUrl");

		return result;
	}

	public void setFbUserUrlLink(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int flCount = Integer.parseInt(map.get("flCount").toString());
		int flCountCookie =  Integer.parseInt(map.get("flCountCookie").toString());
		
		if(flCount > 0){
			map.put("flCount", flCount);
			map.put("flCountCookie", 0);
			map.put("flServer", 1);
			
			sqlSession.selectList("cronSql.setFbUserUrlLink", map);
		}
		
		if(flCountCookie > 0){
			map.put("flCount", 0);
			map.put("flCountCookie", flCountCookie);
			map.put("flServer", 3);
			
			sqlSession.selectList("cronSql.setFbUserUrlLink", map);
		}

		return;
	}
	
	public List<Map<String, Object>> setLinkCookieServer(Map<String, Object> map) throws Exception {
		// return (List<Map<String,
		// Object>>)selectList("sample.selectBoardList", map);

		List<Map<String, Object>> result = sqlSession.selectList("cronSql.selectLinkCookieList", map);

		if (result.size() > 0) {
			map.put("flIdx", result.get(0).get("fl_idx"));
			sqlSession.selectOne("cronSql.modifyLinkCookieList", map);
		}

		return result;
	}
	
	public List<Map<String, Object>> getFbCookie(Map<String, Object> map) throws Exception {
		// return (List<Map<String,
		
		List<Map<String, Object>> result = sqlSession.selectList("cronSql.getFbCookie", map);

		return result;
	}

	public List<Map<String, Object>> chkFbCookie(Map<String, Object> map) throws Exception {
		// System.out.println("sample2.chkFbCookie");

		List<Map<String, Object>> result = sqlSession.selectList("cronSql.chkFbCookie", map);

		return result;

	}

	public void setFbCookieCancel(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("sample2.setFbCookieCancel");

		sqlSession.selectList("cronSql.setFbCookieCancel", map);

	}
	
	public void setFbCookieSuccess(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		
		//sqlSession.selectList("cronSql.setFbCookieSuccess", map);
		sqlSession.selectList("cronSql.setFbCookieSuccess", map);
		sqlSession.selectList("cronSql.setFbLinkDetailCookieSuccess", map);

		return;
	}
	
	public void setTokenArrange(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sqlSession.insert("cronSql.setTokenArrange", map);
	    
        return;
	}
	
}
