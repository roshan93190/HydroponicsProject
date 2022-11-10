package com.HydroPonics.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.HydroPonics.DTO.LoginDTO;
import com.HydroPonics.entity.AcmeProduction;
import com.HydroPonics.entity.CustomerSession;
import com.HydroPonics.entity.Customers;
import com.HydroPonics.exception.LoginException;
import com.HydroPonics.exception.UsernameNotFoundException;
import com.HydroPonics.repository.CustomerRepo;
import com.HydroPonics.repository.CustomerSessionRepo;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo scs;
	
	@Autowired
	private CustomerSessionRepo scsDAO;
	
	@Autowired
	private AcmeProduction productDao;
	
	
	@Override
	public Customers update(Customers update, String Username, String key) {

		Customers updated = null;
		Optional<CustomerSession> otp = scsDAO.findByUuid(key);

		if (otp.isEmpty())
			throw new LoginException("User is not logged in, Please login first!");
		else {
			Optional<Customers> opt = scs.findByUsername(Username);
			if (opt.isEmpty())
				throw new UsernameNotFoundException("Username not found. Please provide proper username");
			else {
				updated = scs.save(update);
			}
		}
		return updated;
		
	}

	@Override
	public String deleteByUsername(LoginDTO dto, String key) {
		Optional<CustomerSession> otp = scsDAO.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("User is not logged in, Please login first!");
		else {
			Optional<Customers> opt = scs.findByUsername(dto.getUsername());
			if (opt.isEmpty())
				throw new UsernameNotFoundException("Username not found");
			else {
				Customers toBeDelete = opt.get();
				scs.delete(toBeDelete);
			}
		}
		return "Your Id with Username " + dto.getUsername() + " is Deleted.";

	}

	@Override
	public String logoutCustomer(String key) {
		Optional<CustomerSession> otp = scsDAO.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("User is not logged in, Please login first!");

	    scsDAO.delete(otp.get());
		return "You have been succefully logged out.";
	}

	@Override
	public Customers storeCustomerRegister(Customers newuser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AcmeProduction> getAllProduct(String key) {
		Optional<CustomerSession> otp = scsDAO.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("User is not logged in, Please login first!");
		return productDao.findAll();
	}

}
