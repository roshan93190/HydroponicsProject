package com.HydroPonics.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.HydroPonics.DTO.LoginDTO;
import com.HydroPonics.entity.AdminSession;
import com.HydroPonics.entity.Client;
import com.HydroPonics.entity.ClientSession;
import com.HydroPonics.entity.CustomerSession;
import com.HydroPonics.entity.Customers;
import com.HydroPonics.exception.CustomerException;
import com.HydroPonics.exception.LoginException;
import com.HydroPonics.repository.AdminRepo;
import com.HydroPonics.repository.AdminSessionRepo;
import com.HydroPonics.repository.ClientRepo;
import com.HydroPonics.repository.ClientSessionRepo;
import com.HydroPonics.repository.CustomerRepo;
import com.HydroPonics.repository.CustomerSessionRepo;

public class LoginServiceImpl implements LoginService {

	
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CustomerSessionRepo customerSessionRepo;
	
	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private ClientSessionRepo clientSessionRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private AdminSessionRepo adminSessionRepo;

	@Override
	public CustomerSession loginCustomer(LoginDTO customer) {
		Optional<Customers> res = customerRepo.findByMobile(customer.getMobile());

		if (!res.isPresent()) {
			System.out.println(res + " Data is empty");
			throw new CustomerException("Customer does not exist with the given mobile number");
		}

		Customers existingCustomer = res.get();
		Optional<CustomerSession> opt = customerSessionRepo.findByCustomerId(existingCustomer.getCustomerId());

		if (opt.isPresent())
			throw new LoginException("User already logged in");

		if (existingCustomer.getPassword().equals(customer.getPassword())) {

			CustomerSession newSession = new CustomerSession();

			newSession.setCustomerId(existingCustomer.getCustomerId());
			newSession.setSessionStartTime(LocalDateTime.now());

			UUID uuid = UUID.randomUUID();
			String token = uuid.toString().split("-")[0];

			newSession.setUuid(token);

			return customerSessionRepo.save(newSession);
		} else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	
	}

	@Override
	public ClientSession loginClient(LoginDTO client) {
		Optional<Client> res = clientRepo.findByMobile(client.getMobile());

		if (!res.isPresent()) {
			System.out.println(res + " Data is empty");
			throw new CustomerException("Client does not exist with the given mobile number");
		}

		Client existingClient = res.get();
		Optional<ClientSession> opt = clientSessionRepo.findByClientId(existingClient.getClientId());

		if (opt.isPresent())
			throw new LoginException("User already logged in");

		
		if (existingClient.getPassword().equals(client.getPassword())) {

			StoreClientSession newSession = new StoreClientSession();

			newSession.setClientId(existingClient.getClientId());
			newSession.setSessionStartTime(LocalDateTime.now());

			UUID uuid = UUID.randomUUID();
			String token = uuid.toString().split("-")[0];

			newSession.setUuid(token);

			return clientSessionDao.save(newSession);
		} else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	}

	@Override
	public AdminSession loginAdmin(LoginDTO admin) {
		// TODO Auto-generated method stub
		return null;
	}


	
}