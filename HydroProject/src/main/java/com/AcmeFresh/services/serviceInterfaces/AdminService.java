package com.AcmeFresh.services.serviceInterfaces;

import javax.validation.Valid;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.modelEntity.AcmeFreshProduce;
import com.AcmeFresh.modelEntity.Admin;
import com.AcmeFresh.repository.AcmeFreshProduceDao;

public interface AdminService {
	
	public Admin adminRegister(Admin admin);

	public Admin updatePassword(LoginDTO dto, String username, String key);

	public Admin update(Admin admin, String Username, String key);

	public String deleteByUsername(LoginDTO dto, String key);

//	public String logoutAdmin(String key) throws LoginException;
	
	public String addInhouseProducts(AcmeFreshProduce produce, String key);
	
	public String updateProduct(AcmeFreshProduceDao produce, String key);
	
	public String updatePriceOfProductById(Integer id, Double newPrice, String key);

}
