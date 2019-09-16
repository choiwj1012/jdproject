package com.facebookc.spring;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.facebookc.spring.aop.LoginJwt;
import com.facebookc.spring.common.CommonFn;
import com.facebookc.spring.common.CookieBox;
import com.facebookc.spring.common.Encrypt;
import com.facebookc.spring.service.AuthService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Resource(name = "authService")
	private AuthService authService;

	@RequestMapping("")
	public ModelAndView auth(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();
		LoginJwt LoginJwt = new LoginJwt();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = LoginJwt.validToken(req, res);

		if ((boolean) resultMap.get("code")) {

			Encrypt Encrypt = new Encrypt();
			String gr = Encrypt.decrypt(resultMap.get("gr").toString());
			String url = "";

			if ("999".equals(gr)) {
				url = "/admin";
			} else if ("888".equals(gr)) {
				url = "/partner";
			} else if ("111".equals(gr)) {
				url = "/manager";
			} else if ("0".equals(gr)) {
				url = "/user";
			}
			// logger.info("aaa");
			mv.setViewName("redirect:" + url);
		} else {
			mv.setViewName("auth/layout");
			mv.addObject("body", "login.jsp");
		}

		return mv;
	}

	@RequestMapping("register")
	public ModelAndView register(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();
		LoginJwt LoginJwt = new LoginJwt();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = LoginJwt.validToken(req, res);

		if ((boolean) resultMap.get("code")) {

			Encrypt Encrypt = new Encrypt();
			String gr = Encrypt.decrypt(resultMap.get("gr").toString());
			String url = "";

			if ("999".equals(gr)) {
				url = "/admin";
			} else if ("888".equals(gr)) {
				url = "/partner";
			} else if ("111".equals(gr)) {
				url = "/manager";
			} else if ("0".equals(gr)) {
				url = "/user";
			}
			// logger.info("aaa");
			mv.setViewName("redirect:" + url);
		} else {
			mv.setViewName("auth/layout");
			mv.addObject("body", "register.jsp");
		}

		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) throws Exception {
		CommonFn commonFn = new CommonFn();
		Encrypt Encrypt = new Encrypt();
		LoginJwt LoginJwt = new LoginJwt();

		res.setContentType("application/json; charset=utf-8");
		PrintWriter out = res.getWriter();
		JSONObject obj = new JSONObject();

		String userId = (String) commonFn.isEmptyR(req.getParameter("userId"), "");
		String pwd = (String) commonFn.isEmptyR(req.getParameter("pwd"), "");

		Map<String, Object> mapa = new HashMap<String, Object>();

		pwd = Encrypt.pwdEncrypt(pwd);

		mapa.put("userId", userId);
		mapa.put("pwd", pwd);

		Map<String, Object> returnMap = authService.selectUserLogin(mapa);

		if (returnMap.get("code").equals("0")) {

			String ix = Encrypt.encrypt(returnMap.get("idx").toString());
			String gr = Encrypt.encrypt(returnMap.get("grade").toString());
			String token = LoginJwt.createToken(userId, ix, gr);

			userId = Encrypt.encrypt(userId);

			String[] tokenArray;
			tokenArray = token.split("\\.");

			CookieBox CookieBox = new CookieBox(req);
			// 쿠키 유지 3시간
			Cookie cookieFirst = CookieBox.createCookie("nid_inf", userId, "/", 60 * 60 * 3);
			Cookie cookieFirst2 = CookieBox.createCookie("nid_hut", tokenArray[0], "/", 60 * 60 * 3);
			Cookie cookieFirst3 = CookieBox.createCookie("nid_bes", tokenArray[1], "/", 60 * 60 * 3);
			Cookie cookieFirst4 = CookieBox.createCookie("nid_for", tokenArray[2], "/", 60 * 60 * 3);

			res.addCookie(cookieFirst);
			res.addCookie(cookieFirst2);
			res.addCookie(cookieFirst3);
			res.addCookie(cookieFirst4);

		}

		/*
		 * JSONObject object =
		 * (JSONObject)JSONValue.parse(returnMap.get("message").toString());
		 * System.out.println(object.get("id").toString());
		 */

		obj.put("code", returnMap.get("code"));
		obj.put("message", returnMap.get("message"));
		out.println(obj);
		returnMap = null;
		out = null;

		return null;

	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest req, HttpServletResponse res) throws Exception {

		LoginJwt LoginJwt = new LoginJwt();

		// res.setContentType("application/json; charset=utf-8");
		// PrintWriter out = res.getWriter();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = LoginJwt.validToken(req, res);

		if ((boolean) resultMap.get("code")) {

			CookieBox cookieBox = new CookieBox(req);
			Cookie cookieFirst = cookieBox.deleteCookie("nid_inf");
			Cookie cookieFirst2 = cookieBox.deleteCookie("nid_hut");
			Cookie cookieFirst3 = cookieBox.deleteCookie("nid_bes");
			Cookie cookieFirst4 = cookieBox.deleteCookie("nid_for");

			res.addCookie(cookieFirst);
			res.addCookie(cookieFirst2);
			res.addCookie(cookieFirst3);
			res.addCookie(cookieFirst4);

			// res.setContentType("application/json; charset=utf-8");
			// obj.put( "code" , "0");
			// obj.put( "message" , "로그아웃");
			// out.println(obj);
			// out = null;
		}

		res.sendRedirect("/auth");
		return null;
	}

	@SuppressWarnings("unchecked")
	// @RequestMapping("/loginTest")
	public ModelAndView loginTest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		CommonFn commonFn = new CommonFn();
		Encrypt Encrypt = new Encrypt();
		LoginJwt LoginJwt = new LoginJwt();

		res.setContentType("application/json; charset=utf-8");
		PrintWriter out = res.getWriter();
		JSONObject obj = new JSONObject();

		String email = (String) commonFn.isEmptyR("chi1004kr@talklink.link", "");
		String pwd = (String) commonFn.isEmptyR("talktab0701", "");

		Map<String, Object> mapa = new HashMap<String, Object>();

		pwd = Encrypt.pwdEncrypt(pwd);

		mapa.put("email", email);
		mapa.put("pwd", pwd);

		Map<String, Object> returnMap = authService.selectUserLogin(mapa);

		if (returnMap.get("code").equals("0")) {

			String ix = Encrypt.encrypt(returnMap.get("idx").toString());
			String gr = Encrypt.encrypt(returnMap.get("grade").toString());
			String token = LoginJwt.createToken(email, ix, gr);

			email = Encrypt.encrypt(email);

			String[] tokenArray;
			tokenArray = token.split("\\.");

			CookieBox CookieBox = new CookieBox(req);
			// 쿠키 유지 3시간
			Cookie cookieFirst = CookieBox.createCookie("nid_inf", email, "", "/", 60 * 60 * 3);
			Cookie cookieFirst2 = CookieBox.createCookie("nid_hut", tokenArray[0], "", "/", 60 * 60 * 3);
			Cookie cookieFirst3 = CookieBox.createCookie("nid_bes", tokenArray[1], "", "/", 60 * 60 * 3);
			Cookie cookieFirst4 = CookieBox.createCookie("nid_for", tokenArray[2], "", "/", 60 * 60 * 3);

			res.addCookie(cookieFirst);
			res.addCookie(cookieFirst2);
			res.addCookie(cookieFirst3);
			res.addCookie(cookieFirst4);

		}

		obj.put("code", returnMap.get("code"));
		obj.put("message", returnMap.get("message"));
		out.println(obj);
		returnMap = null;
		out = null;

		return null;

	}

	/*@RequestMapping("/pwdSc")
	public ModelAndView pwdSc(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Encrypt Encrypt = new Encrypt();
		String pw = Encrypt.pwdEncrypt("talktab0701");

		System.out.println(pw);
		
		PrintWriter out = res.getWriter();
		out.println(pw);
		out = null;

		return null;
	}*/

}
