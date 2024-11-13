package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.Login;
import com.example.ecommerce.model.Register;
import com.example.ecommerce.model.Users;
import com.example.ecommerce.repository.Userrepo;
import com.example.ecommerce.secruity.Jwtprovider;

@RestController
@RequestMapping("/auth/users")
@CrossOrigin
public class Usercontroller {

  
    private Userrepo userrepo;
//    
     private PasswordEncoder passwordEncoder;
    

    private AuthenticationManager authenticationManager;
    
    private Jwtprovider jwtprovider;
  
    @Autowired
	public Usercontroller(Userrepo userrepo, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, Jwtprovider jwtprovider) {
		super();
		this.userrepo = userrepo;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtprovider = jwtprovider;
	}

    @PostMapping("/create")
    public ResponseEntity<?> createuser(@RequestBody Register register) {
        try {
            String password = register.getPassword();
            Users users = new Users();
            users.setPassword(passwordEncoder.encode(password));
            users.setEmail(register.getEmail());
            users.setName(register.getName());
            users.setRole("USER");
            userrepo.save(users);
            
            return ResponseEntity.status(200).body("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating user");
        }
    }

    public Usercontroller(Userrepo userrepo) {
		super();
		this.userrepo = userrepo;
	}
   
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Login login) {
        try {
            System.out.println("Attempting to authenticate user: " + login.getEmail());

            // Authenticate the user
            org.springframework.security.core.Authentication authentication = 
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        login.getEmail(), 
                        login.getPassword()
                    )
                );

            System.out.println("Authentication successful for user: " + login.getEmail());

            // Set authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token using the authenticated user's details
            String token = jwtprovider.generatetoken(authentication.getName());

            return ResponseEntity.ok(token);
        } catch (UsernameNotFoundException e) {
            System.out.println("Username not found: " + login.getEmail());
            return ResponseEntity.status(404).body("User not found");
        } catch (BadCredentialsException e) {
            System.out.println("Bad credentials for user: " + login.getEmail());
            return ResponseEntity.status(401).body("Invalid email or password");
        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

	@GetMapping("/getall")
    public List<Users> ugetallusers(){
          List<Users> allusers = userrepo.findAll();

          return allusers;
    }

    @GetMapping("/getbyid/{id}")
    public Users ugetbyid(@PathVariable("id") int id) throws NotFoundException   {
               
        Users singleuser = userrepo.findById(id).orElseThrow(() -> new NotFoundException());

        return singleuser;
    }
   
    @PutMapping("/update/{id}")
    public void uupdatebyd(@RequestBody Users users ,@PathVariable("id") int id)throws NotFoundException{
        if(!userrepo.existsById(id)){
              throw new NotFoundException();
        }
        users.setId(id);
        userrepo.save(users);
    }
    
    @DeleteMapping("/delete/{id}")
    public void udeleteby(@PathVariable int id){
          userrepo.deleteById(id);
    }
    
    
}
