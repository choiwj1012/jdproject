package com.facebookc.spring.aop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.facebookc.spring.common.CommonFn;
import com.facebookc.spring.common.CookieBox;
import com.facebookc.spring.common.Encrypt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class LoginJwt {
	
	//private static final Logger logger = LoggerFactory.getLogger(LoginJwt.class);
	
	String securityKey = "tkahbm0416";
	
	public String createToken(String tokenUserId, String idx, String grade) {
		String tokenStr = ""; //토큰 값이 저장될 변수
		String issure = "choitoo"; //토큰 발급자
		String subject = ""; //토큰의 주제 (즉 토큰에 담길 내용)
		
		Date exDate = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 3)); //토큰 만료 시간 (1000*60 => 1분)
		
		try {
			tokenStr = Jwts.builder()
					.setIssuer(issure)
					.setSubject(subject)
					.setAudience(tokenUserId)
					.setId(makeUUID2String())
					.setExpiration(exDate)
					.setIssuedAt(new Date())
					.claim("ttl", System.currentTimeMillis())
					.claim("ix", idx)
					.claim("gr", grade)
					.signWith(SignatureAlgorithm.HS256, securityKey.getBytes("UTF-8"))
					.compact();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return tokenStr;
	}
	
	public String createToken(String tokenUserId, String idx, String grade, Long ttl) {
		String tokenStr = ""; //토큰 값이 저장될 변수
		String issure = "choitoo"; //토큰 발급자
		String subject = ""; //토큰의 주제 (즉 토큰에 담길 내용)
		
		Date exDate = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 3)); //토큰 만료 시간 (1000*60 => 1분)
		
		try {
			tokenStr = Jwts.builder()
					.setIssuer(issure)
					/*.setSubject(subject)*/
					.setAudience(tokenUserId)
					.setId(makeUUID2String())
					.setExpiration(exDate)
					.setIssuedAt(new Date())
					.claim("ttl", ttl)
					.claim("ix", idx)
					.claim("gr", grade)
					.signWith(SignatureAlgorithm.HS256, securityKey.getBytes("UTF-8"))
					.compact();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return tokenStr;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject validToken(String tokenStr) throws UnsupportedJwtException, MalformedJwtException, IllegalArgumentException, UnsupportedEncodingException {
		String resultMsg = "";
		JSONObject info = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			Claims claims = Jwts.parser().setSigningKey(securityKey.getBytes("UTF-8")).parseClaimsJws(tokenStr).getBody();
			//System.out.println(Jwts.parser().setSigningKey(securityKey.getBytes("UTF-8")).parseClaimsJws(tokenStr).getBody());
			resultMsg="Pass";
			
			info.put("result", resultMsg);
			
			data.put("id", claims.getId());
			data.put("subject", claims.getSubject());
			data.put("Issuer", claims.getIssuer());
			data.put("aud", claims.getAudience());
			data.put("expiration", claims.getExpiration());
			data.put("tta", claims.get("tta"));
			data.put("ttl", claims.get("ttl"));
			data.put("ix", claims.get("ix"));
			data.put("gr", claims.get("gr"));
			
			info.put("data", data);
			
		    //System.out.println("ID: " + claims.getId());
		    //System.out.println("Subject: " + claims.getSubject());
		    //System.out.println("Issuer: " + claims.getIssuer());
		    //System.out.println("Expiration: " + claims.getExpiration());
		}
		catch(ExpiredJwtException eje) { //토큰의 만료시간이 지난 경우
			resultMsg = "expiredTokenDate";
			
			info.put("result", resultMsg);
			info.put("data", data);
		}
		catch(SignatureException se) { //토큰의 서명 검증이 위조되거나 문제가 생긴 경우
			resultMsg = "wrongSign";
			
			info.put("result", resultMsg);
			info.put("data", data);
			
		} 
		
		//resultMsg = info.toObject();
		return info;
	}
	
	/**
	 * UUID String을 만들어 리턴한다. 
	 * @return String으로 변환된 UUID
	 */
	public String makeUUID2String() {
		return UUID.randomUUID().toString();
	}
	
	public Map<String, Object> validToken(HttpServletRequest req, HttpServletResponse res) throws UnsupportedJwtException, MalformedJwtException, IllegalArgumentException, IOException {
		
		CookieBox CookieBox = new CookieBox(req);
		
		String nidInf = CookieBox.getValue("nid_inf");
		String nidHut = CookieBox.getValue("nid_hut");
		String nidBes = CookieBox.getValue("nid_bes");
		String nidFor = CookieBox.getValue("nid_for");
		
		String token = nidHut+"."+nidBes+"."+nidFor;
		
		CommonFn CommonFn = new CommonFn();
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
	    
		if(CommonFn.isEmpty(nidInf)){
			resultMap.put("code", false);
			resultMap.put("message", "error.");
		    return resultMap;
			
		}else{
			
			Encrypt Encrypt = new Encrypt();
			
			String email = "";
			try {
				email = Encrypt.decrypt(nidInf);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONObject resultJson = validToken(token);
			
			//String result = resultJson.get("result").toString();
			
			JSONObject resultJsonData = (JSONObject) resultJson.get("data");
			
			if(!CommonFn.isEmpty(resultJsonData)){
				
				String aud = resultJsonData.get("aud").toString();
				
				if(!aud.equals(email)){
					// id 변조 의심
					resultMap.put("code", false);
					resultMap.put("message", "error..");
				    return resultMap;
				}
				 
				Long ttl = (Long) resultJsonData.get("ttl");
				 
				/*1000*60 *60 * 24 * 3 --> 3일*/
				int updChk = (int) Math.floor((System.currentTimeMillis() - ttl) / (1000 * 60 * 60 * 24 * 3));
				
				if(updChk > 0){
					// 3일 토큰 타임 넘김
					resultMap.put("code", false);
					resultMap.put("message", "error..");
				    return resultMap;
				}else{
					
					String ix = resultJsonData.get("ix").toString();
					String gr = resultJsonData.get("gr").toString();
					
					SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMdd");
					String aDate = dayTime.format(ttl);
					String bDate = dayTime.format(System.currentTimeMillis());
					
					if(!aDate.equals(bDate)){
						resultMap.put("code", true);
						resultMap.put("message", nidInf);
						resultMap.put("ix", ix);
						resultMap.put("gr", gr);
						resultMap.put("ttl", ttl);
						resultMap.put("refresh", true);
					    return resultMap;
					}
					
					String refreshToken = createToken( email, ix, gr, ttl);
					
					String[] array;
					array = refreshToken.split("\\.");
					
					//쿠키 유지 4시간
//					Cookie cookieFirst = CookieBox.createCookie("nid_inf", nidInf, "", "/", 60*60*4);
//					Cookie cookieFirst2 = CookieBox.createCookie("nid_hut", array[0], "", "/", 60*60*4);
//					Cookie cookieFirst3 = CookieBox.createCookie("nid_bes", array[1], "", "/", 60*60*4);
//					Cookie cookieFirst4 = CookieBox.createCookie("nid_for", array[2], "", "/", 60*60*4);
					Cookie cookieFirst = CookieBox.createCookie("nid_inf", nidInf,  "/", 60*60*4);
					Cookie cookieFirst2 = CookieBox.createCookie("nid_hut", array[0],  "/", 60*60*4);
					Cookie cookieFirst3 = CookieBox.createCookie("nid_bes", array[1],  "/", 60*60*4);
					Cookie cookieFirst4 = CookieBox.createCookie("nid_for", array[2],  "/", 60*60*4);
					
					res.addCookie(cookieFirst);
					res.addCookie(cookieFirst2);
					res.addCookie(cookieFirst3);
					res.addCookie(cookieFirst4);
					
					//토큰 refresh
					resultMap.put("code", true);
					resultMap.put("message", nidInf);
					resultMap.put("ix", ix);
					resultMap.put("gr", gr);
					resultMap.put("ttl", ttl);
					resultMap.put("refresh", false);
				    return resultMap;
				}
			}else{
				resultMap.put("code", false);
				resultMap.put("message", "error...");
			    return resultMap;
			}
		}
	}
	
}
