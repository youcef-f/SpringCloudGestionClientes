package com.billingservice.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.billingservice.entities.ProductItem;
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


@Entity
@NoArgsConstructor 
@AllArgsConstructor
@ToString 
@Data
public class Billing {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date billingDate;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long custumerId;
	
	@Transient // car Customer n'est pas persistant localement. Ingorer au moment de la persistance local
	private Customer customer ; 
	
	@OneToMany(mappedBy = "billing")
	private Collection<ProductItem> productItems ;
	
}
