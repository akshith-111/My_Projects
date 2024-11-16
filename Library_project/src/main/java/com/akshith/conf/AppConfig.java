package com.akshith.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.akshith.service.UserService;

@Configuration
public class AppConfig {

	@Autowired
	private UserService service;
	
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(encoder());
		return provider; 
	}
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf->csrf.disable())
			.authorizeHttpRequests(auth->{
				auth.requestMatchers("/login").permitAll()
				.anyRequest().authenticated();
			})
			.httpBasic(Customizer.withDefaults())
			.formLogin(login->login.loginPage("/login"))
			.logout(Customizer.withDefaults());
		return http.build();
	}
	
	
	
	
	
	
}
