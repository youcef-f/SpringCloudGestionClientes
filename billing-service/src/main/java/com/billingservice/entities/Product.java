package com.billingservice.entities;

import lombok.Data;
import lombok.ToString;


// Product est un objet distant du service inventory-service
@Data @ToString
public class Product {
	
	private Long id;
	private String name;
	private double price;

}
