package com.example.ecommerce.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.Users;

@Repository
public interface Userrepo extends JpaRepository<Users,Integer> {

	 Optional<Users> findByEmail(String email);

}
