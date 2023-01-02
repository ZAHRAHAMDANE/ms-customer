package com.ensa.mscustomer.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensa.mscustomer.requests.CustomerRequest;
import com.ensa.mscustomer.responses.CustomerResponse;
import com.ensa.mscustomer.services.CustomerService;
import com.ensa.mscustomer.shared.dto.CustomerDto;

@RestController
@RequestMapping("/customers") // localhost:8080/customers
public class CustomerController {
	
	@Autowired
	CustomerService customerService; // l'injection de depondance 
	
	@GetMapping(path="/{id}")
	public CustomerResponse getCustomer(@PathVariable String id) {
		
		CustomerDto customerDto = customerService.getCustomerByCustomerId(id);
		
		CustomerResponse customerResponse = new CustomerResponse();
		
		BeanUtils.copyProperties(customerDto, customerResponse);
		
		return customerResponse;
	}
	
	@PostMapping
	public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
		
		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(customerRequest, customerDto); // la liaison entre la couche presentation et la couche service
		
		
		CustomerDto createCustomer = customerService.createCustomer(customerDto); //passer l'information vers la couche service
		
		CustomerResponse customerResponse = new CustomerResponse();
		
		BeanUtils.copyProperties(createCustomer, customerResponse);
		
		return customerResponse;
		
		
	}
	
	@PutMapping(path="{id}")
	public CustomerResponse updateCustomer(@PathVariable String id, @RequestBody CustomerRequest customerRequest) {

		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(customerRequest, customerDto); // la liaison entre la couche presentation et la couche service
		
		
		CustomerDto updateCustomer = customerService.updateCustomer(id, customerDto); //passer l'information vers la couche service
		
		CustomerResponse customerResponse = new CustomerResponse();
		
		BeanUtils.copyProperties(updateCustomer, customerResponse);
		
		return customerResponse;
	}
	
	@DeleteMapping
	public String deleteCustomer() {
		return "delete customer was called";
	}

}
