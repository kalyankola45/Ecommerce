package com.example.ecomback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcombackApplication {

	public static void main(String[] args) {
		try {
			
		
		SpringApplication.run(EcombackApplication.class, args);}
		catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
