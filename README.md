# ToDoApp
Web Application with Spring Boot

Navigation - HTML + Bootstrap


<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
	     <a class="navbar-brand m-1" href="https://courses.in28minutes.com">in28Minutes</a>
	    <div class="collapse navbar-collapse">
		  <ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="/list-todos">Todos</a></li>
		  </ul>
	   </div>
	   <ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
	  </ul>	
</nav>

application.properties configuration


server.port=8081      
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
logging.level.com.in28minutes.springBoot.myFirstWebApp=trace  
spring.mvc.format.date=yyyy-MM-dd
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.defer-datasource-initialization=true   
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

  Spring Security


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
 




