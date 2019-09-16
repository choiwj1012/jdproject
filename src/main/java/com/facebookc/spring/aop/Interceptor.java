package com.facebookc.spring.aop;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.facebookc.spring.common.Encrypt;
import com.facebookc.spring.service.AuthService;

public class Interceptor extends HandlerInterceptorAdapter {

	@Resource(name = "authService")
	private AuthService authService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String url = request.getRequestURI().toString().trim();

		if (url.startsWith("/admin")) {

			LoginJwt LoginJwt = new LoginJwt();

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap = LoginJwt.validToken(request, response);

			if ((boolean) resultMap.get("code")) {

				Encrypt Encrypt = new Encrypt();
				String nidInf = Encrypt.decrypt(resultMap.get("message").toString());
				String ix = Encrypt.decrypt(resultMap.get("ix").toString());
				String gr = Encrypt.decrypt(resultMap.get("gr").toString());

				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("userId", nidInf);

				if (url.indexOf("/post/") == -1) {
					Map<String, Object> resultMapa = new HashMap<String, Object>();

					// user 정보
					resultMapa = authService.selectUserInfo(mapa);

					String license = resultMapa.get("license").toString();
					if ("N".equals(license)) {
						response.sendRedirect("/auth/logout");
						return false;
					}

					request.setAttribute("gr", resultMapa.get("grade").toString());
				}

				request.setAttribute("nidInf", nidInf);
				request.setAttribute("ix", ix);
				
				String urlPath = "";
				
				if("0".equals(gr)){
					urlPath = "/user";
				}else if("111".equals(gr)){
					urlPath = "/manager";
				}else if("888".equals(gr)){
					urlPath = "/partner";
				}else if("999".equals(gr)){
					urlPath = "/admin";
				}
				if (url.startsWith("/admin")) {
					// admin은 관리자만 접속가능
					if (!"999".equals(gr)) {
						response.sendRedirect(urlPath);
						return false;
					}
				} else if (url.startsWith("/partner")) {
					// partner는 중간관리자만 접속가능
					if (!"888".equals(gr)) {
						response.sendRedirect(urlPath);
						return false;
					}
				} else if (url.startsWith("/manager")) {
					// partner는 중간관리자만 접속가능
					if (!"111".equals(gr)) {
						response.sendRedirect(urlPath);
						return false;
					}
				} else if (url.startsWith("/user")) {
					// partner는 중간관리자만 접속가능
					if (!"0".equals(gr)) {
						response.sendRedirect(urlPath);
						return false;
					}
				} else {
					// admin은 일반사용자 모드접속 불가
					if ("999".equals(gr)) {
						response.sendRedirect("/admin");
						return false;
					} else if ("888".equals(gr)) {
						response.sendRedirect("/partner");
						return false;
					} else if ("111".equals(gr)) {
						response.sendRedirect("/manager");
						return false;
					}else if ("0".equals(gr)) {
						response.sendRedirect("/user");
						return false;
					}
				}
			} else {
				response.sendRedirect("/auth");
				return false;
			}
		}
		return true;
	}

}
