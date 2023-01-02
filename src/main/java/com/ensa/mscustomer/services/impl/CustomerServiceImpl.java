package com.ensa.mscustomer.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.mscustomer.entities.CustomerEntity;
import com.ensa.mscustomer.repositories.CustomerRepository;
import com.ensa.mscustomer.services.CustomerService;
import com.ensa.mscustomer.shared.dto.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public CustomerDto createCustomer(CustomerDto customer) {
		// TODO Auto-generated method stub
		
		CustomerEntity customerEntity = new CustomerEntity();
		
		BeanUtils.copyProperties(customer, customerEntity);
		
		CustomerEntity newCutomer = customerRepository.save(customerEntity);
		
		CustomerDto customerDto = new CustomerDto();
		
		BeanUtils.copyProperties(newCutomer, customerDto);
		
		return customerDto;
	}

}
