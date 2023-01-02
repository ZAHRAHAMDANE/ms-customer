package com.ensa.mscustomer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ensa.mscustomer.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
	
	CustomerEntity findByEmail(String email);
	
	CustomerEntity findByCustomerId(String customerId);

}
