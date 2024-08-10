package com.in28minutes.springBoot.myFirstWebApp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



  @Configuration public class SpringSecurityConfiguration {
  
  @Bean public InMemoryUserDetailsManager createUserDetailsManager() {
  
  UserDetails userDetail = createNewUser("Aniket", "Vit1002"); 
  UserDetails userDetail2=createNewUser("ranga", "11810987"); 
  return new InMemoryUserDetailsManager(userDetail,userDetail2); 
  } 
  private UserDetails createNewUser(String username, String password) 
  { 
	  Function<String, String> passwordEncoder=input->passEncoder().encode(input); 
	  UserDetails userDetail=User.builder() .passwordEncoder(passwordEncoder).username(username) .password(password) .roles("USER","ADMIN") .build();
      return userDetail; }
  
  @Bean public PasswordEncoder passEncoder() {
	  return new BCryptPasswordEncoder(); 
  }
  
  @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
  Exception {
  
  http.authorizeHttpRequests( auth -> auth.anyRequest().authenticated());
  http.formLogin(withDefaults());
  
  http.csrf(csrf -> csrf.disable()); // OR //
  http.csrf(AbstractHttpConfigurer::disable);
  
  http.headers(headers ->
  headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)); //
  //Starting from SB 3.1.x
  
  return http.build(); } }
 
