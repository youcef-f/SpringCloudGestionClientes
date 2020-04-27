package com.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.inventoryservice.dao.IProductRepository;
import com.inventoryservice.entities.Product;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start( //
			IProductRepository productRepository, //
			RepositoryRestConfiguration repositoryRestConfiguration ) {
	
		return args -> {
			
			repositoryRestConfiguration.exposeIdsFor(Product.class) ; // expose Id product
			
				productRepository.save(new Product(null,"Computer  hp 100", 1201 ));
				productRepository.save(new Product(null,"Computer dell 203", 256 ));
				productRepository.save(new Product(null,"comupter samsung dfd", 24563 ));
				productRepository.save(new Product(null,"computer apple ddfl", 3562)); 
				productRepository.save(new Product(null,"computer microsoft ddfd", 2457));
				
				productRepository.findAll().forEach(System.out::println);
				
		};
	};
	

}
