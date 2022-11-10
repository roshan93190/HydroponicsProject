package com.HydroPonics.service;

import com.HydroPonics.DTO.LoginDTO;
import com.HydroPonics.entity.AdminSession;
import com.HydroPonics.entity.ClientSession;
import com.HydroPonics.entity.CustomerSession;

public interface LoginService {

	public CustomerSession loginCustomer(LoginDTO customer);

	public ClientSession loginClient(LoginDTO client);
	
	public AdminSession loginAdmin(LoginDTO admin);
	
}
