package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Cartitem;
import com.example.ecommerce.model.Product;

@Repository
public interface Cartitemrepo extends  JpaRepository<Cartitem, Integer> {

	
	List<Cartitem> findByCart(Cart cart);

	Cartitem findByProduct(Product product);
	
	Cartitem findByCartAndProduct(Cart cart, Product product);

}
