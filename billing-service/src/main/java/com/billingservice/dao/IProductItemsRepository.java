package com.billingservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.billingservice.entities.ProductItem;

@RepositoryRestResource
public interface IProductItemsRepository  extends JpaRepository<ProductItem, Long>{

}
