package com.example.ecommerce.secruity;

import java.security.Key;
import java.util.Currency;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class Jwtprovider {

	
	private final    SecretKey key =Keys.hmacShaKeyFor(Jwtconstant.SecretKey.getBytes());

      
	       public String generatetoken(Users users){
            String username = users.getName();
            return  generatetoken(username);
                    
        }
                     
	       public String generatetoken(String username) {
	    	    return Jwts.builder()
	    	               .setSubject(username)
	    	               .setIssuedAt(new Date())
	    	               .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Token valid for 1 hour
	    	               .signWith(SignatureAlgorithm.HS256, key)
	    	               .compact();
	    	}

         public String getemailfromtoken(String jwt){
        	 System.out.println("getemailtoken process start");
                          
                           System.out.println("jwt    "+jwt);

                       try {
                    	   Claims claims = Jwts.parser()
                       
                        		               .setSigningKey(key)
                        		               .parseClaimsJws(jwt)
                        		               .getBody();
                             
                           System.out.println("claims"+claims);
                           String email = claims.getSubject();
                           System.out.println("email"+email);
                           return email;
                       } catch(Exception e)
                       {
                    	   System.out.println("Error parsing JWT: " + e.getMessage());
                           throw new BadCredentialsException("Invalid token from JWT validator", e);
                       }
                    }
            
                    
         public boolean istokenvalid(String token,UserDetails userDetails){
                 
              // Extract the username (email) from the token
              String username = getemailfromtoken(token);
              
              
                
              boolean isnameisvalid = username.equals(userDetails.getUsername());


              boolean istokenexpired = istokenvalid(token);


            return isnameisvalid&& !istokenexpired;
                             }
                    
                            private boolean istokenvalid(String token) {
                               Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
                               Date date = (Date) claims.getExpiration();
                               return date.before(new Date());
                            }
}
