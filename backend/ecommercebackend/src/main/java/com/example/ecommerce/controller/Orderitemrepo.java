package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.Orderitem;
import com.example.ecommerce.model.Orders;

@Repository
public interface Orderitemrepo  extends JpaRepository<Orderitem,Integer>{

	List<Orderitem> findByOrder(Orders order);

}
