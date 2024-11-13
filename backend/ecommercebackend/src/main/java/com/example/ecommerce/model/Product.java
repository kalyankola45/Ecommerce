package com.example.ecommerce.model;

import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {
  

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

         private String title;


        private String discription;

          private long price;

         private int originalprice;


      private int quantity;

      private boolean status;
     

      @ElementCollection
      private Set<String> sizes;


      

 

      public String getTitle() {
        return title;
      }


      public void setTitle(String title) {
        this.title = title;
      }

      public void setId( int id) {
        this.id = id;
      }

      public int getId() {
      return id;
      }


      public String getDiscription() {
        return discription;
      }


      public void setDiscription(String discription) {
        this.discription = discription;
      }


      public long getPrice() {
        return price;
      }


      public void setPrice(long price) {
        this.price = price;
      }


      public int getOriginalprice() {
        return originalprice;
      }


      public void setOriginalprice(int originalprice) {
        this.originalprice = originalprice;
      }


      public int getQuantity() {
        return quantity;
      }


      public void setQuantity(int quantity) {
        this.quantity = quantity;
      }


      public boolean isStatus() {
        return status;
      }


      public void setStatus(boolean status) {
        this.status = status;
      }


      public Set<String> getSizes() {
        return sizes;
      }


      public void setSizes(Set<String> sizes) {
        this.sizes = sizes;
      }


     

    

      


      

}
