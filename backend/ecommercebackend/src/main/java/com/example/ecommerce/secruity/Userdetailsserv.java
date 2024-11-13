package com.example.ecommerce.secruity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.Users;
import com.example.ecommerce.repository.Userrepo;

@Service
public class Userdetailsserv implements UserDetailsService {

   
    private Userrepo userrepo;

    
 
    @Autowired
    public Userdetailsserv(Userrepo userrepo) {
        this.userrepo = userrepo;
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
     {
        Optional<Users> optionalUser = userrepo.findByEmail(email);
        if (optionalUser.isEmpty()) {
            System.out.println("User not found with email: " + email);
            throw new UsernameNotFoundException("User not found");
        }
        
        Users users = optionalUser.get();
        System.out.println("User found: " + users.getEmail());
        return new Userdetailsimpli(users);
            
    }

}
