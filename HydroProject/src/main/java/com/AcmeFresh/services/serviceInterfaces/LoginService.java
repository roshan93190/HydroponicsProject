package com.AcmeFresh.services.serviceInterfaces;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.modelEntity.AdminSession;
import com.AcmeFresh.modelEntity.StoreClientSession;
import com.AcmeFresh.modelEntity.StoreCustomerSession;

public interface LoginService {


	public StoreCustomerSession loginCustomer(LoginDTO customer);

	public StoreClientSession loginClient(LoginDTO client);
	
	public AdminSession loginAdmin(LoginDTO admin);

}
