package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.Adress;

@Repository
public interface Adressrepo extends JpaRepository<Adress,Integer> {
    
	List<Adress> findByUsersId(int userid);
    
}  