package com.AcmeFresh.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AcmeFresh.modelEntity.ListOfInfrastructureProvidedForCustomer;
import com.AcmeFresh.services.serviceImplementation.CustomerInterestedInInfrastructureServiceImpl;
import com.AcmeFresh.modelEntity.CustomerInterestedInInfrastructure;
@RestController
@RequestMapping("/homepage")
public class HomepageController {
	
	@Autowired
	private CustomerInterestedInInfrastructureServiceImpl customerInterestedInInfrastructureServiceImpl;
	
	@GetMapping("/products")
	public ResponseEntity<List<ListOfInfrastructureProvidedForCustomer>> listOfInfrastructueProvidedForCustomer(){
		List<ListOfInfrastructureProvidedForCustomer> values=Arrays.asList(ListOfInfrastructureProvidedForCustomer.values());
		return new ResponseEntity<>(values, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/")
	public ResponseEntity<String> welcome(){
		return new ResponseEntity<>("Welcome to AcmeFresh, We Provide services for Hydroponic Infrastructure and Produce from Hydroponic Farms.", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/products/newrequest")
	public ResponseEntity<String> usersResponseForInfrastructure(@RequestBody CustomerInterestedInInfrastructure newRequest){
		customerInterestedInInfrastructureServiceImpl.savingRequest(newRequest);
		return new ResponseEntity<>("Your response have been Submitted, Our Team will reach you shortly..", HttpStatus.CREATED);
	}
	
	
	
	

}
