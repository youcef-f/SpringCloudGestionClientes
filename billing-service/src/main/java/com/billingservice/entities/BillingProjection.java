package com.billingservice.entities;

import java.util.Collection;
import java.util.Date;


import org.springframework.data.rest.core.config.Projection;

import com.billingservice.entities.Billing;
import com.billingservice.entities.ProductItem;

@Projection(name="fullBill", types = Billing.class)
public interface BillingProjection {

	 Long getId();
	 Date getBillingDate();
	 Long getCustumerId();
	 Collection<ProductItem> getProductItems();
	

}
