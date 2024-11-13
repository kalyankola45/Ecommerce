package com.example.ecommerce.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Cartitem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Users;
import com.example.ecommerce.repository.Cartitemrepo;
import com.example.ecommerce.repository.Cartrepo;
import com.example.ecommerce.repository.Productrepo;
import com.example.ecommerce.repository.Userrepo;
import com.example.ecommerce.secruity.Tokentoemail;
//import com.example.ecommerce.secruity.Jwtfilter;
//import com.example.ecommerce.secruity.Jwtprovider;


import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/cart")
public class Cartscontroller {
	
	
	
	private Userrepo userrepo;
	
	private Cartrepo cartrepo;
	
	private Cartitemrepo cartitemrepo;
	
	
	private Productrepo productrepo;

	private Tokentoemail tokentoemail;

	

	public Cartscontroller() {
		super();
	}

	
       
     @Autowired
	public Cartscontroller(Userrepo userrepo, Cartrepo cartrepo, Cartitemrepo cartitemrepo, Productrepo productrepo,
			Tokentoemail tokentoemail) {
		super();
		this.userrepo = userrepo;
		this.cartrepo = cartrepo;
		this.cartitemrepo = cartitemrepo;
		this.productrepo = productrepo;
		this.tokentoemail = tokentoemail;
	}




	@PostMapping(value = "/save/", consumes = "application/json")
	public void additem(@RequestBody Cartitem cartitem, HttpServletRequest request) throws Exception {
		
		
		int total = 0;
		int tax =0;
		
	    Users existuser = tokentoemail.getuserfromtoken(request);
	    System.out.println("Authenticated User: " + existuser);
       Cart cart = existuser.getCart();
	    if (cart == null) {
	        cart = new Cart();
	        cart.setUsers(existuser);
	        cartrepo.save(cart);
	    }
	    
	    Product isproduct = productrepo.findById(cartitem.getProduct().getId()).orElseThrow(() -> new Exception("Product not found"));
	    
	    
	   updatecart(cart, cartitem, isproduct);
	   
	
	   
	    
	   updatecarttotal(cart);
	    
	 
	}
   
	  public void updatecarttotal(Cart cart) {
		  
		  int total = 0;
		  int tax = 0;
		  List<Cartitem> totaList = cartitemrepo.findByCart(cart);
		  
		  for(Cartitem list:totaList) {
			  total += list.getTotalprice();
			  
		  }
		  cart.setTotal(total);
		   int t = (int) ((total)*(0.15));
		   cart.setTax(t);
		  System.out.print("total price"+total);
		  cartrepo.save(cart);
	  }
	  public void updatecart(Cart cart, Cartitem newCartitem, Product product) {
		    Cartitem existitemCartitem = cartitemrepo.findByCartAndProduct(cart, product);
		    if (existitemCartitem != null) {
		        existitemCartitem.setQuantity(existitemCartitem.getQuantity() + newCartitem.getQuantity());
		        existitemCartitem.setTotalprice(existitemCartitem.getQuantity() * product.getPrice());
		        cartitemrepo.save(existitemCartitem);
		    } else {
		        newCartitem.setProduct(product);
		        newCartitem.setCart(cart);
		        newCartitem.setProductprice(product.getPrice());
		        newCartitem.setTotalprice(newCartitem.getQuantity() * product.getPrice());
		        cartitemrepo.save(newCartitem);
		    }
		}

	@GetMapping("/getall/{id}")
     public List<Cartitem> cgetallbyid(@PathVariable("id") int id){

		 System.out.print("checking before");
		    Users users3 = userrepo.findById(id).orElseThrow(null);
		    Cart cart = cartrepo.findByUsers(users3);
		    if (cart == null) {
		        throw new RuntimeException("No cart found for user with ID: " + id);
		    }
		    System.out.print("checking after");
		   List<Cartitem> list = cartitemrepo.findByCart(cart);
		    return list;
		
	
    	 
     }
	
	  
	@DeleteMapping("delete/{pid}")
	public void cdelete(@RequestBody Product product) {
		
		Cartitem cartitem = cartitemrepo.findByProduct(product);
		cartitemrepo.deleteById(cartitem.getId());
	}
}

