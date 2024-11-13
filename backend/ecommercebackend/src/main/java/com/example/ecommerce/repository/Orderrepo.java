package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.Adress;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Orders;
import com.example.ecommerce.model.Users;

public interface Orderrepo  extends JpaRepository<Orders, Integer> {

	Orders findByUsersAndCartAndAdress(Users users, Cart cart, Adress adress);

	List<Orders> findByUsers(Users user);

}
