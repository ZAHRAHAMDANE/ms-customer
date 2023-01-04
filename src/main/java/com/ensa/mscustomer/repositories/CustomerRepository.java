package com.ensa.mscustomer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ensa.mscustomer.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	CustomerEntity findByEmail(String email);
	
	CustomerEntity findByCustomerId(String customerId);

}
