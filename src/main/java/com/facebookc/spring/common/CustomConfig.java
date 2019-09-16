package com.facebookc.spring.common;

import javax.annotation.Resource;

import com.facebookc.spring.dao.MainDAO;
import com.facebookc.spring.service.MainService;

public class CustomConfig {
	
	@Resource(name="mainService")
	private MainService mainService;
	
	@Resource(name = "mainDAO")
	private MainDAO mainDAO;
	
//	public String config(String text){
//		
//		String result ="";
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("code", text);
//		
//		System.out.println(map);
//		result = mainDAO.getConfig(map);
//		
////	    String accessToken = "EAABwzLixnjYBADICw2C4s6mnbnhDipxhuHeXoiWEMGUOFFgsed7v3nROLMo8KMs7ZA7z3rFq3vfr6nqQy2weX3ftfxuiwFWVY15aHl0Rb22gFsOl81GK9IbDbvs65RSlduXL6ZBqA8HNuTs4G2XCL5zk6rWgYZD";
////	    String accessCookie = "datr=HJr9WJB7bDglLyB2WKiQdnMk; dats=1; sb=Ipr9WKv-_QmO5fs_l_SESPUt; c_user=100010796523367; xs=35%3Alrmsu1gFq6to5w%3A2%3A1493015074%3A13384; fr=0IkKhuLXVQVesGwzt.AWX1WEGQfuN7kgBnSJIsAc7zs8I.BY_Zoc.l7.AAA.0.0.BY_Zoi.AWVI37uS; pl=n; lu=ggcsGFKzY8tOYcdFJJE1VNNQ; presence=EDvF3EtimeF1493015080EuserFA21B10796523367A2EstateFDutF1493015077087CEchFDp_5f1B10796523367F0CC";
////	    
////	    if("accessToken".equals(text)){
////	    	result =  accessToken;
////	    }else if("accessCookie".equals(text)){
////	    	result =  accessCookie;
////	    }
//	    
//	    return result;
//	}

}
