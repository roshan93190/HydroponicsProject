package com.AcmeFresh.services.serviceInterfaces;

import java.util.List;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.modelEntity.AcmeFreshProduce;
import com.AcmeFresh.modelEntity.StoreCustomers;

public interface StoreCustomerService {


	public StoreCustomers  update(StoreCustomers  update, String Username, String key);

	public String deleteByUsername(LoginDTO  dto, String key);

	public String logoutCustomer(String key);

	public StoreCustomers storeCustomerRegister(StoreCustomers newuser);
	
	public List<AcmeFreshProduce> getAllProduct(String key);
}
