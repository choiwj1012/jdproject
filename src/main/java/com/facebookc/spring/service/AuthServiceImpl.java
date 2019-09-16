package com.facebookc.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import com.facebookc.spring.common.CommonFn;
import com.facebookc.spring.dao.AuthDAO;


//@Service
@Service("authService")
public class AuthServiceImpl implements AuthService{
     
	@Resource(name="authDAO")
    private AuthDAO authDAO;
	
//    @Override
//    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
//
//    	System.out.println("들어옴");
//    	
//    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//
//    	Map<String,Object> mapa = new HashMap<String,Object>();
//
//    	mapa.put("id", "하하하");
//
//    	list.add(mapa);
//    	
//        return list;
//    }
	
	public Map<String, Object> selectUserLogin(Map<String, Object> map) throws Exception {
		
		String userId = map.get("userId").toString();
		String pwd = map.get("pwd").toString();
		
		Map<String, Object> resultMap = new HashMap<String,Object>();
		
		if(userId==""){
			resultMap.put("code", "1");
			resultMap.put("message", "이메일을 입력해주세요.");
			return resultMap;
		}
		
		if(pwd==""){
			resultMap.put("code", "1");
			resultMap.put("message", "비밀번호를 입력해주세요.");
			return resultMap;
		}
		
		authDAO.selectUserLogin(map);
		
		JSONObject object = (JSONObject)JSONValue.parse(map.get("outResult").toString());
		
		CommonFn CommonFn = new CommonFn();
		
		String code = (String) CommonFn.isEmptyR(object.get("code").toString(), "");
		String message = (String) CommonFn.isEmptyR(object.get("message").toString(), "");
		String idx = "";
		String grade = "";
		
		if("0".equals(code)){
			idx = (String) CommonFn.isEmptyR(object.get("idx").toString(), "");
			grade = (String) CommonFn.isEmptyR(object.get("grade").toString(), "");
		}
		
		resultMap.put("idx", idx);
		resultMap.put("grade", grade);
		resultMap.put("code", code);
		resultMap.put("message", message);
		
        return resultMap;
    }
	
	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> mapa) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> resultUserInjfo = new ArrayList<Map<String,Object>>();
		
		resultUserInjfo = authDAO.selectUserInfo(mapa);
		
		String license = "";
		String grade = "0";
		
		if(resultUserInjfo.size() > 0){
			for( int i = 0; i < resultUserInjfo.size(); i++){
				 license= resultUserInjfo.get(i).get( "license" ).toString();
				 grade = resultUserInjfo.get(i).get( "grade" ).toString();
			}
		}
		
		Map<String,Object> list = new HashMap<String,Object>();
		list.put("grade", grade);
		list.put("license", license);
		
		return list;
	}
}
