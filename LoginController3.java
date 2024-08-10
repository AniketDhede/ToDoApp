package com.in28minutes.springBoot.myFirstWebApp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class LoginController3 {
	
   private Logger logger=LoggerFactory.getLogger(getClass());
   
   @RequestMapping("login3")
   public String goToLoginPage(@RequestParam String name,ModelMap m) {
	    System.out.println("Hii..."+name);
		
		  logger.debug("Request param is {}",name);
		  
		  logger.info("Info level"); logger.warn("warn level");
		  System.out.println("Hiiiii....");
		 
		 
	   m.put("naam", name); 
	   return "login";
   } 
} 
 