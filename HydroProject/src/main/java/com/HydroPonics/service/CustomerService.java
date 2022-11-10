package com.HydroPonics.service;

import java.util.List;

import com.HydroPonics.DTO.LoginDTO;
import com.HydroPonics.entity.AcmeProduction;
import com.HydroPonics.entity.Customers;

public interface CustomerService {

	public Customers  update(Customers  update, String Username, String key);

	public String deleteByUsername(LoginDTO dto, String key);

	public String logoutCustomer(String key);

	public Customers storeCustomerRegister(Customers newuser);
	
	public List<AcmeProduction> getAllProduct(String key);
}
