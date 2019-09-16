package com.facebookc.spring;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.facebookc.spring.service.CronService;
import com.facebookc.spring.service.MainService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/cron")
@EnableAsync
public class CronController {
	
//	private final Logger logger = LoggerFactory.getLogger(CronController.class);
	
	@Resource(name="mainService")
	private MainService mainService;
	
	@Resource(name="cronService")
	private CronService cronService;
	
	@RequestMapping("/a")
	public ModelAndView openSampleBoardList(HttpServletRequest req, HttpServletResponse res) throws Exception{
        
//        res.setContentType("text/html");
//        PrintWriter out = res.getWriter();
//    	
//        Encrypt Encrypt = new Encrypt();
//        String cc = Encrypt.encrypt("test");
//    	out.println(cc);
		
		
//		String fusToken = "EAAAAUaZA8jlABAEvK8pwWLizjOEzflEZBQSU9xZB4IHZADfRpKcy1Fc8oNTke5N2ZBOKhOu1y6SYS13F0S6bzk7M1BZAFsSecdbmkE8dZBfbFB2lhuJYrCHHkU8YElXHU01a4Mtczp7kjebFfEK4sWXSme1d424ctx4Lwiln8Kv6gZDZD";
//		String url = "https://graph.facebook.com/me?access_token="+fusToken;
//        
//        HttpGet http = new HttpGet(url);
//		String body = "";
//
//		try {
//			http.setHeader("Content-type", "application/x-www-form-urlencoded");
//			http.setHeader("Referer", "https://m.facebook.com");
//			http.setHeader("Accept", "*/*");
//			http.setHeader("User-Agent",
//					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//
//			HttpClient httpClient = HttpClientBuilder.create().build();
//
//			HttpResponse response = httpClient.execute(http);
//
//			body = EntityUtils.toString(response.getEntity(), "UTF-8");
//			System.out.println(body);
//			
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			http.releaseConnection();
//		}	
		
    	return null;
    }
	
}


