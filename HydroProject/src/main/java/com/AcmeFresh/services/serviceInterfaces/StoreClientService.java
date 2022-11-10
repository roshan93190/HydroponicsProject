package com.AcmeFresh.services.serviceInterfaces;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.modelEntity.AcmeFreshProduce;
import com.AcmeFresh.modelEntity.StoreClient;

public interface StoreClientService {
	
	public StoreClient  update(StoreClient  update, String Username, String key);

	public String deleteByUsername(LoginDTO  dto, String key);

	public String logoutClient(String key);

	public StoreClient storeClientRegister(StoreClient newuser);
	
	public String addInhouseProducts(AcmeFreshProduce produce, String key);
	
	public String updateProduct(AcmeFreshProduce produce, String key);
	
	public String updatePriceOfProductById(Integer id, Double newPrice, String key);

}
