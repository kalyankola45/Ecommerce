package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration;

@SpringBootApplication(exclude = {Neo4jRepositoriesAutoConfiguration.class})
public class EcommercebackendApplication {

	public static void main(String[] args) {
		try {
	        SpringApplication.run(EcommercebackendApplication.class, args);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
