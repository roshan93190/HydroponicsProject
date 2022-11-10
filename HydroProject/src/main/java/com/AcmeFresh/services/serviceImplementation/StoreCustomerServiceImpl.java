package com.AcmeFresh.services.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.modelEntity.AcmeFreshProduce;
import com.AcmeFresh.modelEntity.StoreCustomerSession;
import com.AcmeFresh.modelEntity.StoreCustomers;
import com.AcmeFresh.repository.AcmeFreshProduceDao;
import com.AcmeFresh.repository.StoreCustomerDAO;
import com.AcmeFresh.repository.StoreCustomerSessionDAO;
import com.AcmeFresh.services.serviceInterfaces.StoreCustomerService;


@Service
public class StoreCustomerServiceImpl implements StoreCustomerService {
	@Autowired
	private StoreCustomerDAO scs;
	
	@Autowired
	private StoreCustomerSessionDAO scsDAO;
	
	@Autowired
	private AcmeFreshProduceDao productDao;
	
	@Override
	public StoreCustomers storeCustomerRegister(StoreCustomers newuser) {
		return scs.save(newuser);
	}

	@Override
	public StoreCustomers update(StoreCustomers update, String Username, String key) {
		StoreCustomers updated = null;
		Optional<StoreCustomerSession> otp = scsDAO.findByUuid(key);

		if (otp.isEmpty())
			throw new com.AcmeFresh.exceptions.LoginException("User is not logged in, Please login first!");
		else {
			Optional<StoreCustomers> opt = scs.findByUsername(Username);
			if (opt.isEmpty())
				throw new com.AcmeFresh.exceptions.UsernameNotFoundException("Username not found. Please provide proper username");
			else {
				updated = scs.save(update);
			}
		}
		return updated;

	}

	@Override
	public String deleteByUsername(LoginDTO dto, String key) {
		Optional<StoreCustomerSession> otp = scsDAO.findByUuid(key);
		if (otp.isEmpty())
			throw new com.AcmeFresh.exceptions.LoginException("User is not logged in, Please login first!");
		else {
			Optional<StoreCustomers> opt = scs.findByUsername(dto.getUsername());
			if (opt.isEmpty())
				throw new com.AcmeFresh.exceptions.UsernameNotFoundException("Username not found");
			else {
				StoreCustomers toBeDelete = opt.get();
				scs.delete(toBeDelete);
			}
		}
		return "Your Id with Username " + dto.getUsername() + " is Deleted.";
	}

	
	@Override
	public String logoutCustomer(String key) {
		Optional<StoreCustomerSession> otp = scsDAO.findByUuid(key);
		if (otp.isEmpty())
			throw new com.AcmeFresh.exceptions.LoginException("User is not logged in, Please login first!");

	scsDAO.delete(otp.get());
		return "You have been succefully logged out.";
	}

	@Override
	public List<AcmeFreshProduce> getAllProduct(String key) {
		Optional<StoreCustomerSession> otp = scsDAO.findByUuid(key);
		if (otp.isEmpty())
			throw new com.AcmeFresh.exceptions.LoginException("User is not logged in, Please login first!");
		return productDao.findAll();

	}
	
	

}
