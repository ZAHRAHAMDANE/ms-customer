package com.ensa.mscustomer.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensa.mscustomer.exceptions.CustomerException;
import com.ensa.mscustomer.requests.CustomerRequest;
import com.ensa.mscustomer.responses.CustomerResponse;
import com.ensa.mscustomer.responses.ErrorMessages;
import com.ensa.mscustomer.services.CustomerService;
import com.ensa.mscustomer.shared.dto.CustomerDto;

@RestController
@RequestMapping("/customers") // localhost:8088/customers
public class CustomerController {
	
	@Autowired
	CustomerService customerService; // l'injection de depondance 
	
	@GetMapping(path="/{id}")
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String id) {
		
		CustomerDto customerDto = customerService.getCustomerByCustomerId(id);
		
		CustomerResponse customerResponse = new CustomerResponse();
		
		BeanUtils.copyProperties(customerDto, customerResponse);
		
		return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.OK); 
	}
	
	@PostMapping
	public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) throws Exception{
		
		if(customerRequest.getFirstName().isEmpty()) throw new CustomerException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(customerRequest, customerDto); // la liaison entre la couche presentation et la couche service
		
		
		CustomerDto createCustomer = customerService.createCustomer(customerDto); //passer l'information vers la couche service
		
		CustomerResponse customerResponse = new CustomerResponse();
		
		BeanUtils.copyProperties(createCustomer, customerResponse);
		
		return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.CREATED);  
		
	}
	
	@PutMapping(path="{id}")
	public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String id, @RequestBody CustomerRequest customerRequest) {

		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(customerRequest, customerDto); // la liaison entre la couche presentation et la couche service
		
		
		CustomerDto updateCustomer = customerService.updateCustomer(id, customerDto); //passer l'information vers la couche service
		
		CustomerResponse customerResponse = new CustomerResponse();
		
		BeanUtils.copyProperties(updateCustomer, customerResponse);
		
		return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.ACCEPTED);  
	}
	
	@DeleteMapping(path="{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable String id) {

		customerService.deleteCustomer(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
