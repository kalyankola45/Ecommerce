package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Cartitem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    //relationship with other tables

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id")
    private Cart cart;
 
    @ManyToOne
    private Product product;
   
	  private long totalprice;
    
	  private long productprice;
	  
	    private int quantity;


	    private String size;


	

		public long getTotalprice() {
			return totalprice;
		}


		public void setTotalprice(long totalprice) {
			this.totalprice = totalprice;
		}


		public long getProductprice() {
			return productprice;
		}


		public void setProductprice(long productprice) {
			this.productprice = productprice;
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public Cart getCart() {
			return cart;
		}


		public void setCart(Cart cart) {
			this.cart = cart;
		}


		


		public Product getProduct() {
			return product;
		}


		public void setProduct(Product product) {
			this.product = product;
		}




		public int getQuantity() {
			return quantity;
		}


		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}


		public String getSize() {
			return size;
		}


		public void setSize(String size) {
			this.size = size;
		}


	

    

    
}
