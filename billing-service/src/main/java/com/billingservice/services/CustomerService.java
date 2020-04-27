package com.billingservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.billingservice.entities.Customer;



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

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerService {

	@GetMapping("/customers/{id}")  // renvoi un Customer Objet issue du microService CUSTOMER-SERVER
	public Customer findCustomerById(@PathVariable(name="id") Long id);

}
