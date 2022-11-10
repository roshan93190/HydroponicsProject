package com.HydroPonics.service;

import com.HydroPonics.DTO.LoginDTO;
import com.HydroPonics.entity.AcmeProduction;
import com.HydroPonics.entity.Client;

public interface ClientService {

	public Client  update(Client  update, String Username, String key);

	public String deleteByUsername(LoginDTO  dto, String key);

	public String logoutClient(String key);

	public Client storeClientRegister(Client newuser);
	
	public String addInhouseProducts(AcmeProduction produce, String key);
	
	public String updateProduct(AcmeProduction produce, String key);
	
	public String updatePriceOfProductById(Integer id, Double newPrice, String key);
	
}
