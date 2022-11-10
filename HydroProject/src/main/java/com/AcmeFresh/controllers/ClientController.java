package com.AcmeFresh.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.modelEntity.AcmeFreshProduce;
import com.AcmeFresh.modelEntity.StoreClient;
import com.AcmeFresh.modelEntity.StoreClientSession;
import com.AcmeFresh.services.serviceImplementation.LoginServiceImpl;
import com.AcmeFresh.services.serviceImplementation.StoreClientServiceImpl;

@RestController
@RequestMapping("/homepage/store/client")
public class ClientController {
	
	@Autowired
	private LoginServiceImpl loginService;
	
	@Autowired
	private StoreClientServiceImpl storeClientService;
	
	@PostMapping("/login")
	public ResponseEntity<StoreClientSession> loginClient(@RequestBody LoginDTO dto) {
		return new ResponseEntity<>(loginService.loginClient(dto), HttpStatus.ACCEPTED);
	}

	@PostMapping("/register")
	public ResponseEntity<StoreClient> registerCustomer(@RequestBody StoreClient newuser) {
		return new ResponseEntity<>(storeClientService.storeClientRegister(newuser), HttpStatus.CREATED);

	}


	@PutMapping("/update/{username}")
	public ResponseEntity<StoreClient> updateCustomer(@RequestBody StoreClient update, @PathVariable("username") String username,
			@RequestParam String key) {
		return new ResponseEntity<>(storeClientService.update(update, username, key), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteaccount")
	public ResponseEntity<String> deleteCustomer(@RequestBody LoginDTO dto, @RequestParam String key) {
		return new ResponseEntity<>(storeClientService.deleteByUsername(dto, key), HttpStatus.ACCEPTED);

	}

	@GetMapping("/logout")
	public ResponseEntity<String> logoutCustomer(@RequestParam String key) {
		return new ResponseEntity<>(storeClientService.logoutClient(key), HttpStatus.ACCEPTED);

	}
	
	@PostMapping("/addnewproduct")
	public ResponseEntity<String> addNewProduct(@Valid @RequestBody AcmeFreshProduce produce, String key) {
		return new ResponseEntity<>(storeClientService.addInhouseProducts(produce, key), HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/updatepriceofproduct/{id}")
	public ResponseEntity<String> updatePriceByProductId(@RequestParam Double price , @PathVariable("id") Integer id,
			@RequestParam String key) {
		return new ResponseEntity<>(storeClientService.updatePriceOfProductById(id, price, key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateproduct")
	public ResponseEntity<String> updateProduct(@Valid @RequestBody AcmeFreshProduce produce, @RequestParam String key) {
		return new ResponseEntity<>(storeClientService.updateProduct(produce, key), HttpStatus.ACCEPTED);
	}

}
