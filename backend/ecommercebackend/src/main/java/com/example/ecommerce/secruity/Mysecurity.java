package com.example.ecommerce.secruity;

import java.util.Arrays;
import java.util.Collections;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@EnableWebSecurity
@Configuration
public class Mysecurity {

	
	
	private Jwtfilter jwtfilter;
	
	private Userdetailsserv userdetailsserv;
	
	   

         @Autowired
	    public Mysecurity(Jwtfilter jwtfilter, Userdetailsserv userdetailsserv) {
		super();
		this.jwtfilter = jwtfilter;
		this.userdetailsserv = userdetailsserv;
	}
		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf().disable()
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .authorizeHttpRequests(auth -> {
	                auth.requestMatchers("/auth/users/**").permitAll();
	                auth.requestMatchers("/api/").authenticated();
	            
	            })
	            
	            .cors().configurationSource(corsConfigurationSource())
	            .and()
	            .authenticationProvider(daoAuthenticationProvider())
	            .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        return request -> {
	            CorsConfiguration cors = new CorsConfiguration();
	            cors.setAllowCredentials(true);
	            cors.setAllowedOrigins(Arrays.asList(
	                    "http://localhost:8080",
	                    "http://localhost:3000"));
	            cors.setAllowedMethods(Collections.singletonList("*"));
	            cors.setAllowedHeaders(Collections.singletonList("*"));
	            cors.setExposedHeaders(Arrays.asList("Authorization"));
	            cors.setMaxAge(3000L);
	            return cors;
	        };
	    }
	 
		@Bean
	    public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	    

	
		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		    return authenticationConfiguration.getAuthenticationManager();
		}

	    
		@Bean
		public DaoAuthenticationProvider daoAuthenticationProvider(){
			DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
			daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
			daoAuthenticationProvider.setUserDetailsService(userdetailsserv);
			return daoAuthenticationProvider;
		}
	   
	    
	    
}
