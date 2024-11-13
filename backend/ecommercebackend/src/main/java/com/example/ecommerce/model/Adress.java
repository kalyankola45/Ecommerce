package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Adress {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String Name;
	
	private String Email;
	
	private String Streetadress;
	
	private String City;
	
	private String State;
	
	private String Postalcode;
	
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Users users;



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}



	public String getStreetadress() {
		return Streetadress;
	}



	public void setStreetadress(String streetadress) {
		Streetadress = streetadress;
	}



	public String getCity() {
		return City;
	}



	public void setCity(String city) {
		City = city;
	}



	public String getState() {
		return State;
	}



	public void setState(String state) {
		State = state;
	}



	public String getPostalcode() {
		return Postalcode;
	}



	public void setPostalcode(String postalcode) {
		Postalcode = postalcode;
	}



	public Users getUsers() {
		return users;
	}



	public void setUsers(Users users) {
		this.users = users;
	}
	
	
	
	
	

}
