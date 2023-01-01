package com.ensa.mscustomer.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers") // localhost:8080/customers
public class CustomerController {
	
	@GetMapping
	public String getCustomer() {
		return "get customer was called";
	}
	
	@PostMapping
	public String createCustomer(@RequestBody CustomerRequest customerRequest) {
		return "create custemer was called";
	}
	
	@PutMapping
	public String updateCustomer() {
		return "update customer was called";
	}
	
	@DeleteMapping
	public String deleteCustomer() {
		return "delete customer was called";
	}

}
