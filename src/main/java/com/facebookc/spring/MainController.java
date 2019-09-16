package com.facebookc.spring;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.facebookc.spring.common.CommonFn;
import com.facebookc.spring.service.MainService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {

	@Resource(name = "mainService")
	private MainService mainService;

	@RequestMapping("")
	public ModelAndView main(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();

		String viewName = "main/content/chrandinghome";

		// layout
		mv.setViewName(viewName);
		return mv;
	}

	@RequestMapping("/brand")
	public ModelAndView brand(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();

		String viewName = "main/content/chrandingbrand";

		// layout
		mv.setViewName(viewName);

		return mv;
	}

	@RequestMapping("/price")
	public ModelAndView price(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();

		String viewName = "main/content/chrandingprice";

		// layout
		mv.setViewName(viewName);

		return mv;
	}

	@RequestMapping("/question")
	public ModelAndView question(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();

		String viewName = "main/content/chrandingquestion";

		// layout
		mv.setViewName(viewName);

		return mv;
	}

	@RequestMapping("/event")
	public ModelAndView event(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();

		String viewName = "main/content/chrandingevent";

		// layout
		mv.setViewName(viewName);

		return mv;
	}

	@RequestMapping("/start")
	public ModelAndView start(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();

		String viewName = "main/content/chrandingstart";

		// layout
		mv.setViewName(viewName);

		return mv;
	}

	@RequestMapping("/trial")
	public ModelAndView trial(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();

		String viewName = "main/content/chrandingtrial";

		// layout
		mv.setViewName(viewName);

		return mv;
	}

	@RequestMapping("/contact")
	public ModelAndView contact(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();

		String viewName = "main/content/chrandingcontact";

		// layout
		mv.setViewName(viewName);

		return mv;
	}

	@RequestMapping("/tospage")
	public ModelAndView tospage(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();

		String viewName = "main/content/tospage";

		// layout
		mv.setViewName(viewName);

		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/post/startsave")
	public void startsave(HttpServletRequest req, HttpServletResponse res) throws Exception {
		CommonFn commonFn = new CommonFn();
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out = res.getWriter();
		JSONObject obj = new JSONObject();

		String num = commonFn.isEmptyR(req.getParameter("num"), "0").toString();
		String name = commonFn.isEmptyR(req.getParameter("name"), "").toString();
		String money = commonFn.isEmptyR(req.getParameter("money"), "0").toString();
		String ref = commonFn.isEmptyR(req.getHeader("referer"), "").toString();
		
		if (num == "0") {
			obj.put("errorMessage", "전화번호는 필수 입력입니다.");
			out.println(obj);
			out = null;
			return;
		}

		String hPattern = "^[가-힣]*$";
		String pPattern = "(010|011|016)\\d{3,4}\\d{4}$";
		String nPattern = "(^[0-9]*$)";

		Map<String, Object> map = new HashMap<String, Object>();
		boolean pSuccess = Pattern.matches(pPattern, num);
		boolean nSuccess = Pattern.matches(nPattern, money);
		boolean hSuccess = Pattern.matches(hPattern, name);

		if (nSuccess == true && pSuccess == true && hSuccess == true) {

			map.put("num", num);
			map.put("name", name);
			map.put("money", money);
			map.put("ref", ref);
			
			int result = mainService.userduplicationCheck(map);
			if (result == 1) {
				obj.put("message", "중복 신청입니다.");
				out.println(obj);
				out = null;
				return;
			} else {
				mainService.saveUser(map);
				obj.put("code", "신청이 완료되었습니다.");
				out.println(obj);
				out = null;
				return;
			}
		} else {
			obj.put("errorMessage", "규칙이 일치하지 않습니다.");
			out.println(obj);
			out = null;
			return;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/post/trialsave")
	public void trialsave(HttpServletRequest req, HttpServletResponse res) throws Exception {
		CommonFn commonFn = new CommonFn();
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out = res.getWriter();
		JSONObject obj = new JSONObject();

		String num = commonFn.isEmptyR(req.getParameter("num"), "0").toString();
		String content = commonFn.isEmptyR(req.getParameter("content"), "").toString();
		String ref = commonFn.isEmptyR(req.getHeader("referer"), "").toString();

		String pPattern = "(010|011|016)\\d{3,4}\\d{4}$";

		Map<String, Object> map = new HashMap<String, Object>();
		boolean nSuccess = Pattern.matches(pPattern, num);

		if (nSuccess == true) {

			map.put("num", num);
			map.put("content", content);
			map.put("ref", ref);

			int result = mainService.userduplicationCheck(map);
			if (result == 1) {
				obj.put("message", "중복 신청입니다.");
				out.println(obj);
				out = null;
				return;
			} else {
				mainService.saveUser(map);
				obj.put("code", "신청이 완료되었습니다.");
				out.println(obj);
				out = null;
				return;
			}
		} else {
			obj.put("errorMessage", "규칙이 일치하지 않습니다.");
			out.println(obj);
			out = null;
			return;
		}
	}

}
