package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.Product;

@Repository
public interface Productrepo extends JpaRepository<Product , Integer> {



}
