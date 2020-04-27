package com.inventoryservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.inventoryservice.entities.Product;

@RepositoryRestResource
public interface IProductRepository extends JpaRepository<Product, Long> {

}
