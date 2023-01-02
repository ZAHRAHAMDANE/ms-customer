package com.ensa.mscustomer.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.mscustomer.entities.CustomerEntity;
import com.ensa.mscustomer.repositories.CustomerRepository;
import com.ensa.mscustomer.services.CustomerService;
import com.ensa.mscustomer.shared.Utils;
import com.ensa.mscustomer.shared.dto.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	Utils util;
	
	@Override
	public CustomerDto createCustomer(CustomerDto customer) {
		// TODO Auto-generated method stub
		
		CustomerEntity checkCustomer = customerRepository.findByEmail(customer.getEmail());
		
		if(checkCustomer != null) throw new RuntimeException("Customer alrady exists !");
		
		CustomerEntity customerEntity = new CustomerEntity();
		
		BeanUtils.copyProperties(customer, customerEntity);
		
		customerEntity.setCustomerId(util.generateCustomerId(32)); 
		
		CustomerEntity newCutomer = customerRepository.save(customerEntity);
		
		CustomerDto customerDto = new CustomerDto();
		
		BeanUtils.copyProperties(newCutomer, customerDto);
		
		return customerDto;
	}

	@Override
	public CustomerDto getCustomerByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		CustomerEntity customerEntity = customerRepository.findByCustomerId(customerId);
		
		if(customerEntity == null) throw new RuntimeException("Customer not found !");
		
		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(customerEntity, customerDto);
		
		return customerDto;
	}

	@Override
	public CustomerDto updateCustomer(String customerId, CustomerDto customerDto) {

		CustomerEntity customerEntity = customerRepository.findByCustomerId(customerId);
		
		if(customerEntity == null) throw new RuntimeException("Customer not found !");
		
		customerEntity.setFirstName(customerDto.getFirstName());
		customerEntity.setLastName(customerDto.getLastName());
		customerEntity.setAdress(customerDto.getAdress());
		customerEntity.setCity(customerDto.getCity());
		customerEntity.setEmail(customerDto.getEmail());
		
		CustomerEntity customerUpdated = customerRepository.save(customerEntity);
		
		CustomerDto customer = new CustomerDto();
		BeanUtils.copyProperties(customerUpdated, customer);
		
		return customer;
	}

}
