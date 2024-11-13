package com.example.ecommerce.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	 private int id;
	 
	 private String name;
	 
	  @Column(unique = true, nullable = false)
	 private String email;
	
	 private String password;
	 
	 private String role;
	 
	 private String mobile;
	 
	 
	 @OneToMany(mappedBy = "users")
	 private List<Orders> orders;

	 
	 
	 @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
	 private List<Adress> address = new ArrayList<>();
	 

	 
	 @Temporal(TemporalType.DATE)
	 private Date date;


	 @OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
	 private Cart cart;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public List<Adress> getAddress() {
		return address;
	}


	public void setAddress(List<Adress> address) {
		this.address = address;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}
	 
	 
	 


}
