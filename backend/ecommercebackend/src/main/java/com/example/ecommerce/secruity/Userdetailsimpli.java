package com.example.ecommerce.secruity;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ecommerce.model.Users;

public class Userdetailsimpli implements UserDetails {

      private Users users;
    


     
    public Userdetailsimpli(Users users) {
            this.users = users;
      }

@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
          SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(users.getRole());

          return Arrays.asList(simpleGrantedAuthority);
        
    }

    @Override
    public String getPassword() {
          return users.getPassword();
        
    }

    @Override
    public String getUsername() {
         return users.getEmail();
       
    }

}
