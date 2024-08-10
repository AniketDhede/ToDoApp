package com.in28minutes.springBoot.myFirstWebApp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
	  @RequestMapping(value="/", method=RequestMethod.GET) 
	  public String goWelcomePage(ModelMap model) {
	  model.put("name", getLoggedUsername()); 

	  return "Welcome"; }
	
	  private String getLoggedUsername() { Authentication authenication=
	  SecurityContextHolder.getContext().getAuthentication(); return
	  authenication.getName(); }
	 
	   
   
} 
