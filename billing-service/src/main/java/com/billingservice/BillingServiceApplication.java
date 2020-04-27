package com.billingservice;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import com.billingservice.dao.IBillingRepository;
import com.billingservice.dao.IProductItemsRepository;
import com.billingservice.entities.Billing;
import com.billingservice.entities.Customer;
import com.billingservice.entities.Product;
import com.billingservice.entities.ProductItem;
import com.billingservice.services.CustomerService;
import com.billingservice.services.InventoryService;


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

@EnableFeignClients
@SpringBootApplication
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start( //
			IBillingRepository billingRepository, //
			IProductItemsRepository productItemsRepository, //
			CustomerService custormerService, //
			InventoryService inventoryService ) {
		
		return args -> {

			// retrieve customer from remote customer service
			Customer customer = custormerService.findCustomerById(1L);

			System.out.println("------------------------------------------------------");
			System.out.println(customer.toString());
			
			// Create billing for customer
			Billing billing1 = billingRepository.save(new Billing(null, new Date(), 1L,null, null));

			
			// retrieve product from remote inventory service
			Product product1 = inventoryService.findProductById(1L);
			Product product2 = inventoryService.findProductById(2L);
			Product product3 = inventoryService.findProductById(3L);
			
			System.out.println(product1.toString());
			System.out.println(product2.toString());
			System.out.println(product3.toString());

			// add product to billing
			productItemsRepository.save((new ProductItem(null, product1.getId(),null, product1.getPrice(), 30, billing1)));
			productItemsRepository.save((new ProductItem(null, product2.getId(),null, product2.getPrice(), 30, billing1)));
			productItemsRepository.save((new ProductItem(null, product3.getId(),null, product3.getPrice(), 30, billing1)));

			
			// retrieve all product from remote inventory service
			PagedModel<Product>  products = (PagedModel<Product>) inventoryService.findAll();
			products.getContent().forEach(product -> {
				System.out.println(product.toString());
			});
			
		};

	}
}
