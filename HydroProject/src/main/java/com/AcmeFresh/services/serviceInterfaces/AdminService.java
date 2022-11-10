package com.AcmeFresh.services.serviceInterfaces;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.modelEntity.AcmeFreshProduce;
import com.AcmeFresh.modelEntity.Admin;

public interface AdminService {
	
	public Admin adminRegister(Admin admin);

	public Admin updatePassword(LoginDTO dto, String username, String key);

	public Admin update(Admin admin, String Username, String key);

	public String deleteByUsername(LoginDTO dto, String key);

//	public String logoutAdmin(String key) throws LoginException;
	
	public String addInhouseProducts(AcmeFreshProduce produce, String key);
	
	public String updateProduct(AcmeFreshProduce produce, String key);
	
	public String updatePriceOfProductById(Integer id, Double newPrice, String key);

}
