package com.ensa.mscustomer.services;

import java.util.List;

import com.ensa.mscustomer.shared.dto.CustomerDto;

public interface CustomerService {
	
	CustomerDto createCustomer(CustomerDto customerDto);
	
	CustomerDto getCustomerByCustomerId(String customerId);
	
	CustomerDto updateCustomer(String id, CustomerDto customerDto);
	
	void deleteCustomer(String customerId);
	
	List<CustomerDto> getCustomers(int page, int limit);
}
