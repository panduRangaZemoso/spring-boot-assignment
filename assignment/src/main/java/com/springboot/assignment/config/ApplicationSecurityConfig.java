package com.springboot.assignment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		// IN MEMORY USER ROLE INFO
		  
		auth.inMemoryAuthentication()
			  .withUser("pandu").password("{bcrypt}$2a$10$Exl/C.So6GYUyvknqLJUc.6GLYfQJ/.adudRWsRiZfGymtcVQbzfC").roles("ADMIN")
			  .and()
			  .withUser("kausik").password("{bcrypt}$2a$10$Exl/C.So6GYUyvknqLJUc.6GLYfQJ/.adudRWsRiZfGymtcVQbzfC").roles("ADMIN")
			  .and()
			  .withUser("kiran").password("{bcrypt}$2a$10$Exl/C.So6GYUyvknqLJUc.6GLYfQJ/.adudRWsRiZfGymtcVQbzfC").roles("ADMIN");
	}	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/item/**").hasRole("ADMIN")
			//.anyRequest().authenticated()
			.and()
		    .formLogin().permitAll()
		    .and()
		    .logout().permitAll();
	}
}
