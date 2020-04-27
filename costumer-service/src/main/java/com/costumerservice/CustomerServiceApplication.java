package com.costumerservice;

import org.aspectj.weaver.patterns.ArgsAnnotationPointcut;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.costumerservice.dao.ICustomerRepository;
import com.costumerservice.entities.Customer;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);

	}



	 

	
	
	@Bean
	CommandLineRunner start(ICustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
		return args -> {
			
		
			repositoryRestConfiguration.exposeIdsFor(Customer.class);
			
			customerRepository.save(new Customer(null, "youcef", "youcef@gmail.com"));
			customerRepository.save(new Customer(null, "fatima", "fatima@gmail.com"));
			customerRepository.save(new Customer(null, "mihoub", "mihoub@gmail.com"));
			customerRepository.save(new Customer(null, "mohamed", "mohamed@gmail.com"));
			customerRepository.findAll().forEach(System.out::println);

		};
	};

}


