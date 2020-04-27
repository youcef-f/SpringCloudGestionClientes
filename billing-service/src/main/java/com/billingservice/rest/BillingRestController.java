package com.billingservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.billingservice.dao.IBillingRepository;
import com.billingservice.dao.IProductItemsRepository;
import com.billingservice.entities.Billing;
import com.billingservice.entities.ProductItem;
import com.billingservice.services.CustomerService;
import com.billingservice.services.InventoryService;

@RestController
public class BillingRestController {

	
	@Autowired
	private IBillingRepository billingRepository;

	@Autowired
	private IProductItemsRepository productItemsRepository;

	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private InventoryService inventoryService;

	
	@GetMapping ("/fullbilling/{id}")
	public Billing getBilling(@PathVariable(name="id")Long id) {

		Billing billing = billingRepository.findById(id).orElse(null);
		billing.setCustomer(customerService.findCustomerById(billing.getCustumerId()));
		
		billing.getProductItems().forEach(productItem->{
			productItem.setProduct(inventoryService.findProductById(productItem.getId()));
		});
		
		return billing;

	}
	
	
}
