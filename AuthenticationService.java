package com.in28minutes.springBoot.myFirstWebApp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
      public boolean authenticate(String name,String password) {
    	  boolean usernam=name.equalsIgnoreCase("In28minutes");
    	  boolean passwor=password.equalsIgnoreCase("Vit1002");

    	  return usernam && passwor;
      }
}
