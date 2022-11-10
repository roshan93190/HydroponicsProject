package com.HydroPonics.service;

import com.HydroPonics.DTO.LoginDTO;

import com.HydroPonics.entity.AcmeProduction;
import com.HydroPonics.entity.Admin;
import com.HydroPonics.exception.LoginException;

public interface AdminService {

	public Admin registerAdmin(Admin admin);

	public Admin updatePassword(LoginDTO dto, String username, String key);

	public Admin updateAdmin(Admin admin, String Username, String key);

	public String deleteByUsername(LoginDTO dto, String key);

	public String logoutAdmin(String key) throws LoginException;
	
	public String addInhouseProducts(AcmeProduction produce, String key);
	
	public String updateProduct(AcmeProduction produce, String key);
	
	public String updatePriceOfProductById(Integer id, Double newPrice, String key);
}
