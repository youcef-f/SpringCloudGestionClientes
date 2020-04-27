package com.billingservice.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



/*
                          BILLING SERVICE
CUSTOMER-SERVICE          +--------------------------+
+----------------+        | +---------------------+  |
|   Costumer     |1..1    | |    Billing          |  |
| id:    long    |<---------| id: Long            |  |
|  name:  String |        | | BillingDate: Date   |  |
|  email: String |        | | CustomerID: String  |  |
+----------------+        | +----------+----------+  |
                          |            |             |
                          |            |             |
INVENTORY-SERVICE         |            |1..*         |
+-----------------+       |  +---------v----------+  |
|   Product       | 1..1  |  |   ProductItem      |  |
| - id : long     |<---------| id : Long          |  |
|   name: String  |       |  | productId: String  |  |
|   price : double|       |  | price: Long        |  |
+-+---------------+       |  | quantité: int      |  |
                          |  |--------------------+  |
                          +--------------------------+
*/


@Entity @NoArgsConstructor @AllArgsConstructor @ToString @Data
public class ProductItem {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty(access = Access.WRITE_ONLY)  
	private Long productId;
	
	@Transient   // Product n'est pas persistent 
	private Product product;
	private double price;
	private double quantity;	
	
	@JsonProperty(access = Access.WRITE_ONLY) // evite le probleme de recursivité lors de la deserialisation 
	@ManyToOne
	private Billing billing;
	
}
