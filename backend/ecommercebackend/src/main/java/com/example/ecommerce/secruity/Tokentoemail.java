package com.example.ecommerce.secruity;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.Users;
import com.example.ecommerce.repository.Userrepo;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class Tokentoemail {
	
	
	private final Jwtprovider jwtprovider;
    private final Userrepo userrepo;
    
     
     
    public Tokentoemail(Jwtprovider jwtprovider, Userrepo userrepo) {
		super();
		this.jwtprovider = jwtprovider;
		this.userrepo = userrepo;
	}



	public Users getuserfromtoken(HttpServletRequest request) {
    	
    	 String token = request.getHeader("Authorization").substring(7);
    	 
    	 String username =jwtprovider.getemailfromtoken(token);
    	 
       return userrepo.findByEmail(username).orElseThrow(null);
    	
    }
     
}
