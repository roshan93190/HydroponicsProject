package com.AcmeFresh.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.modelEntity.AcmeFreshProduce;
import com.AcmeFresh.modelEntity.StoreCustomerSession;
import com.AcmeFresh.modelEntity.StoreCustomers;
import com.AcmeFresh.services.serviceImplementation.LoginServiceImpl;
import com.AcmeFresh.services.serviceImplementation.StoreCustomerServiceImpl;


@RestController
@RequestMapping("/homepage/store/customer")
public class CustomerController {
	@Autowired
	private LoginServiceImpl loginService;
	
	@Autowired
	private StoreCustomerServiceImpl storeCustomersService;
	
	@PostMapping("/login")
	public ResponseEntity<StoreCustomerSession> loginAdmin(@RequestBody LoginDTO dto) {
		return new ResponseEntity<>(loginService.loginCustomer(dto), HttpStatus.ACCEPTED);
	}

	@PostMapping("/register")
	public ResponseEntity<StoreCustomers> registerCustomer(@RequestBody StoreCustomers newuser) {
		return new ResponseEntity<>(storeCustomersService.storeCustomerRegister(newuser), HttpStatus.CREATED);

	}


	@PutMapping("/update/{username}")
	public ResponseEntity<StoreCustomers> updateCustomer(@RequestBody StoreCustomers update, @PathVariable("username") String username,
			@RequestParam String key) {
		return new ResponseEntity<>(storeCustomersService.update(update, username, key), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteaccount")
	public ResponseEntity<String> deleteCustomer(@RequestBody LoginDTO dto, @RequestParam String key) {
		return new ResponseEntity<>(storeCustomersService.deleteByUsername(dto, key), HttpStatus.ACCEPTED);

	}

	@GetMapping("/logout")
	public ResponseEntity<String> logoutCustomer(@RequestParam String key) {

		return new ResponseEntity<>(storeCustomersService.logoutCustomer(key), HttpStatus.ACCEPTED);

	}
	@GetMapping("/products")
	public ResponseEntity<List<AcmeFreshProduce>> getAllProducts(@RequestParam String key) {

		return new ResponseEntity<>(storeCustomersService.getAllProduct(key), HttpStatus.ACCEPTED);

	}

}
