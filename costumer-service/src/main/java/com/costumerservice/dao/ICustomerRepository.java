package com.costumerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.costumerservice.entities.Customer;

@RepositoryRestResource
public interface ICustomerRepository extends JpaRepository<Customer, Long> { 

	
}
