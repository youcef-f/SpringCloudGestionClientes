package com.costumerservice.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.Projection;

import com.costumerservice.entities.Customer;

@Configuration
@Projection (name = "projection1", types = Customer.class)
public interface CustomerJPAPojection {

	public  Long getId();
	public String getName();
	
}
