package com.billingservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.billingservice.entities.Billing;

@RepositoryRestResource
public interface IBillingRepository extends  JpaRepository<Billing, Long>{

}
