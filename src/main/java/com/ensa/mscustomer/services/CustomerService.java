package com.ensa.mscustomer.services;

import com.ensa.mscustomer.shared.dto.CustomerDto;

public interface CustomerService {
	
	CustomerDto createCustomer(CustomerDto customerDto);
	
	CustomerDto getCustomerByCustomerId(String customerId);
	
	CustomerDto updateCustomer(String id, CustomerDto customerDto);
}
