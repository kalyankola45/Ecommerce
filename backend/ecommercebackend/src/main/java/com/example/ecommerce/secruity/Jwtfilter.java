 package com.example.ecommerce.secruity;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class Jwtfilter extends OncePerRequestFilter {

	private Jwtprovider jwtprovider;
	
	
	

	private Userdetailsserv userdetailsserv;
	@Autowired
	public Jwtfilter(Jwtprovider jwtprovider, Userdetailsserv userdetailsserv) {
		this.jwtprovider = jwtprovider;
		this.userdetailsserv = userdetailsserv;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    
	    String jwtString = request.getHeader(Jwtconstant.JWTHEADER);
	 
           System.out.println("receivetoken " + jwtString);
           System.out.println("afterremoving  " +  jwtString.substring(6));
           if(jwtString == null) {
        	   throw new NullPointerException("mg raa ungamma");
           }
	    if (jwtString != null) {
	        String jwt = jwtString.substring(6);
	        System.out.print("start    "  + jwt);

	        try {
	            String email = jwtprovider.getemailfromtoken(jwt);
	             System.out.println("email from token  "+email);

	            UserDetails userDetails = userdetailsserv.loadUserByUsername(email);
	           System.out.print("Loaded UserDetails: " + userDetails.getUsername());

	           if (jwtprovider.istokenvalid(jwt, userDetails)) {
	                UsernamePasswordAuthenticationToken authenticationToken = 
	                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            }
	        } catch (Exception e) {
	         
	            throw new BadCredentialsException("Invalid token from JWT validator");
	        }
	    }

	    filterChain.doFilter(request, response);
	}


}
