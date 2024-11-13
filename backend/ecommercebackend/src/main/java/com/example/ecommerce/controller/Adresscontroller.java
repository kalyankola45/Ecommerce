package com.example.ecommerce.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.Adress;
import com.example.ecommerce.model.Users;
import com.example.ecommerce.repository.Adressrepo;
import com.example.ecommerce.repository.Userrepo;

@RestController
@RequestMapping("/api/adrees")
public class Adresscontroller  {

    
      private final Adressrepo adressrepo;
    private final Userrepo userrepo;

    public Adresscontroller(Adressrepo adressrepo, Userrepo userrepo) {
        this.adressrepo = adressrepo;
        this.userrepo = userrepo;
    }
  
   
    @PostMapping("/save/{userid}")
    public void saveadress(@RequestBody Adress adress , @PathVariable("userid") int id){
           
        Users user =  userrepo.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + id);
        }
            adress.setUsers(user);
            adressrepo.save(adress);
        
    }
    
    
    

     @GetMapping("/getall/{userid}")
    public List<Adress> agetallAdress(@PathVariable("userid") int id){
          List<Adress> allAdress = adressrepo.findByUsersId(id);
          return allAdress;
    }

    @GetMapping("/getbyid/{id}")
    public Adress agetbyid(@PathVariable("id") int id) throws NotFoundException   {
               
        Adress singleuser =adressrepo.findById(id).orElseThrow(() -> new NotFoundException());

        return singleuser;
    }
   
    @PutMapping("/update/{id}")
    public void aupdatebyd(@RequestBody Adress adress ,@PathVariable("id") int id)throws NotFoundException{
      
        
        Adress existAdress = adressrepo.findById(id).orElseThrow(null);
       if(existAdress != null) {
    	   existAdress.setCity(adress.getCity());
       
        existAdress.setEmail(adress.getEmail());
        existAdress.setName(adress.getName());
        existAdress.setState(adress.getState());
        existAdress.setPostalcode(adress.getPostalcode());
        existAdress.setStreetadress(adress.getStreetadress());
        adressrepo.save(existAdress);
       }
       else {
    	   System.out.print("not working");
       }
    }
    
    @DeleteMapping("/delete/{id}")
    public void adeleteby(@PathVariable int id){
         adressrepo.deleteById(id);
    }
    

    
}
