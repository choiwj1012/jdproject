package com.facebookc.spring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.facebookc.spring.common.CommonFn;
import com.facebookc.spring.common.Encrypt;
import com.facebookc.spring.common.Paging;
import com.facebookc.spring.service.AdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	// private final Logger logger =
	// LoggerFactory.getLogger(AdminController.class);

	@Resource(name = "adminService")
	private AdminService adminService;

	@RequestMapping("")
	public ModelAndView main(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();
		CommonFn commonFn = new CommonFn();
		
		int page = Integer.parseInt(commonFn.isEmptyR(req.getParameter("page"), "1").toString());
		String userId = req.getAttribute("nidInf").toString();
		/*String ix = req.getAttribute("ix").toString();*/

		String viewName = "admin/layout";
		String bodyName = "content/index.jsp";

		// layout
		mv.setViewName(viewName);
		mv.addObject("body", bodyName);

		// info
		mv.addObject("userId", userId);
		mv.addObject("menu", "Main");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// Paging Calculation
		int countList = 10; // 개시글 갯수
		int countPage = 10; // 페이징 갯수
		int totalCount = adminService.selectUserTotal();

		Paging paging = new Paging();
		String pagingStr = paging.pagingList(page, totalCount, countList, countPage);

		mv.addObject("pagingStr", pagingStr);

		map.put("page", (page - 1) * countList);
		map.put("countList", countList);

		List<Map<String, Object>> urlContentList = new ArrayList<Map<String, Object>>();
		// urlContentList = userService.selectContent(map);
		urlContentList = adminService.selectUser(map);

		mv.addObject("urlContentList", urlContentList);
			
		return mv;

	}

}
