package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.Productrepo;


@RestController
@RequestMapping("/api/product")
public class Productcontroller {
 
             @Autowired
            private Productrepo productrepo;

       @PostMapping("/create")
       private void Createproduct(@RequestBody Product product){

              productrepo.save(product);
              
       }

       @GetMapping("/all")
       private List<Product> getallproducts(){
           List<Product> allprooducts = productrepo.findAll();
           
           return allprooducts;
       }

       @GetMapping("/all/{id}")
       public  Product getbyid(@PathVariable ("id") int id) throws NotFoundException {
              
                     Product getted = productrepo.findById(id).orElseThrow(() -> new NotFoundException());
                       return  getted;
                
                 
       }

       @PutMapping("/update/{id}")
       public void pupdate(@RequestBody Product product, @PathVariable("id") int id) throws Exception{

              if (!productrepo.existsById(id)) {
                     throw new NotFoundException();
                 };
                 product.setId(id);
                 productrepo.save(product);
       }
       @DeleteMapping("/delete/{id}")
       public void pdelere(@PathVariable("id") int id){
              productrepo.deleteById(id);
       }
}
