package com.facebookc.spring.cron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.facebookc.spring.common.CommonFn;
import com.facebookc.spring.service.CronService;
import com.facebookc.spring.service.MainService;

@Component 
public class Scheduler { 
	
	@Resource(name="cronService")
	private CronService cronService;
	
	@Resource(name="mainService")
	private MainService mainService;
	
//	@Scheduled(cron = "0 */3 * * * *") 
//	public void cronTest1() {
//		System.out.println("3분마다");
//	}
	
//	@Scheduled(cron = "0 */2 * * * *")
//	public void fbLinkStart() throws Exception {
//		//System.out.println("fbLinkStart ");
//		 //cronService.fbLinkStart();
//		asyncService.fbLinkStart();
//	}
//	
//	@Scheduled(cron = "0 */10 * * * *")
//	public void fbUserUrlGetLink() throws Exception {
//		//System.out.println("fbUserUrlGetLink");
//		//cronService.fbUserUrlGetLink();
//		asyncService.fbUserUrlGetLink();
//	}
//	
//	@Scheduled(cron = "0 */2 * * * *")
////	@Scheduled(fixedDelay = 50000)
//	public void fbLinkStartServer() throws Exception {
//		//System.out.println("fbLinkStartServer");
//		
//        String flag = "1";
//        
//        Map<String,Object> mapa = new HashMap<String,Object>();
//        mapa.put("flag", flag);
//        
//        flag = null;
//        
//        //cronService.fbLinkStartServer(mapa);
//        asyncService.fbLinkStartServer(mapa);
//        
//	}
//	
//	@Scheduled(cron = "0 */2 * * * *")
//	public void fbLinkStartCookieServer() throws Exception {
//		//System.out.println("fbLinkStartCookieServer");
//		
//		String flag = "1";
//        
//        Map<String,Object> mapa = new HashMap<String,Object>();
//        mapa.put("flag", flag);
//        
//        //cronService.fbLinkStartCookieServer(mapa);
//        asyncService.fbLinkStartCookieServer(mapa);
//        
//	}
	
//	@Scheduled(fixedDelay = 3600000)
//	public void realSendEmail() throws Exception{
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		Map<String, Object> map = new HashMap<String,Object>();
//		String context ="";
//		String sName = null;
//		String sNum = null;
//		String sflag = null;
//		String sRegDate = null;
//		list = mainService.selectUser();
//		boolean success = false;
//		
//		for(int i = 0; i<list.size(); i++) {
//			map = (Map<String, Object>)list.get(i);
//			sName =map.get("wg_name").toString();
//			sNum =map.get("wg_tel").toString();
//			sflag = map.get("wg_flag").toString();
//			sRegDate = map.get("wg_regdate").toString();
//			
//			if(sflag.equals("N")){
//				context += (i+1) + " 번째" + "이름 : " + sName + "\n 전화번호 : " + sNum + "\n 신청시간 : "+ sRegDate+"\n\n";
//				mainService.updateUserFlag(map);
//				success = true;
//			} else if(sflag.equals("Y")){
//				//System.out.println("신청자가 없습니다.");
//				return;
//			}
//		}
//		if(success == true){
//			mainService.sendEmail(context);
//		}
//	}
}

