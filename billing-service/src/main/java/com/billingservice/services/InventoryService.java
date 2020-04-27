package com.billingservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.billingservice.entities.Product;


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

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryService {

	@GetMapping("/products/{id}")  // renvoi un Product Objet issue du microService INVENTORY-SERVER
	public Product findProductById(@PathVariable(name="id") Long id);

	
	/*  HATEOS pageModel<products>
	 *{
        "_embedded": {
          "products":
	 */
	@GetMapping("/products")  // renvoi tous les Product Objet issue du microService INVENTORY-SERVER
	public PagedModel<Product> findAll();

}
