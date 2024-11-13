package com.example.ecommerce.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orders_no")
	private int Orderid;
	
	
	private String Paymenttype;
	
	private String Orderstatus;
  
	@OneToOne
	 private Adress adress;
	 
	 
	private Date ExpectedDate;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private Users users;

     
	
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getOrderid() {
		return Orderid;
	}

	public void setOrderid(int orderid) {
		Orderid = orderid;
	}

	public String getPaymenttype() {
		return Paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		Paymenttype = paymenttype;
	}

	public String getOrderstatus() {
		return Orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		Orderstatus = orderstatus;
	}

	
	
	

	
	
	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Date getExpectedDate() {
		return ExpectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		ExpectedDate = expectedDate;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	
    
	
}
