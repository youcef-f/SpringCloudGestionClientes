package com.billingservice.entities;

import lombok.Data;
import lombok.ToString;


// Customer est un objet sur le service distant customer-service
@Data @ToString
public class Customer {

	private Long id;
	private String name;
	private String email;
	
}
