package com.AcmeFresh.services.serviceImplementation;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.exceptions.CustomerException;
import com.AcmeFresh.exceptions.LoginException;
import com.AcmeFresh.modelEntity.Admin;
import com.AcmeFresh.modelEntity.AdminSession;
import com.AcmeFresh.modelEntity.StoreClient;
import com.AcmeFresh.modelEntity.StoreClientSession;
import com.AcmeFresh.modelEntity.StoreCustomerSession;
import com.AcmeFresh.modelEntity.StoreCustomers;
import com.AcmeFresh.repository.AdminDao;
import com.AcmeFresh.repository.AdminSessionDAO;
import com.AcmeFresh.repository.StoreClientDAO;
import com.AcmeFresh.repository.StoreClientSessionDAO;
import com.AcmeFresh.repository.StoreCustomerDAO;
import com.AcmeFresh.repository.StoreCustomerSessionDAO;
import com.AcmeFresh.services.serviceInterfaces.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private StoreCustomerDAO customerDAO;

	@Autowired
	private StoreCustomerSessionDAO customerSessionDAO;
	
	@Autowired
	private StoreClientDAO clientDao;
	
	@Autowired
	private StoreClientSessionDAO clientSessionDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminSessionDAO adminSessionDao;

	@Override
	public StoreCustomerSession loginCustomer(LoginDTO customer) {

		Optional<StoreCustomers> res = customerDAO.findByMobile(customer.getMobile());

		if (!res.isPresent()) {
			System.out.println(res + " Data is empty");
			throw new CustomerException("Customer does not exist with the given mobile number");
		}

		StoreCustomers existingCustomer = res.get();
		Optional<StoreCustomerSession> opt = customerSessionDAO.findByCustomerId(existingCustomer.getCustomerId());

		if (opt.isPresent())
			throw new LoginException("User already logged in");

		if (existingCustomer.getPassword().equals(customer.getPassword())) {

			StoreCustomerSession newSession = new StoreCustomerSession();

			newSession.setCustomerId(existingCustomer.getCustomerId());
			newSession.setSessionStartTime(LocalDateTime.now());

			UUID uuid = UUID.randomUUID();
			String token = uuid.toString().split("-")[0];

			newSession.setUuid(token);

			return customerSessionDAO.save(newSession);
		} else {
			throw new LoginException("Password Incorrect. Try again.");
		}

	}
	
	@Override
	public StoreClientSession loginClient(LoginDTO client) {

		Optional<StoreClient> res = clientDao.findByMobile(client.getMobile());

		if (!res.isPresent()) {
			System.out.println(res + " Data is empty");
			throw new CustomerException("Client does not exist with the given mobile number");
		}

		StoreClient existingClient = res.get();
		Optional<StoreClientSession> opt = clientSessionDao.findByClientId(existingClient.getClientId());

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
	public AdminSession loginAdmin(LoginDTO dto) {
		Optional<com.AcmeFresh.modelEntity.Admin> res = adminDao.findByMobile(dto.getMobile());

		if (res.isEmpty()) {
			System.out.println("No Admin exist");
			throw new CustomerException("Admin was not there in the data base");
		}

		Admin existingAdmin = res.get();
		Optional<AdminSession> opt = adminSessionDao.findByAdminId(existingAdmin.getAdminId());

		if (opt.isPresent())
			throw new LoginException("User already logged in");

		if (existingAdmin.getPassword().equals(dto.getPassword())) {

			AdminSession newSession = new AdminSession();
			newSession.setAdminId(existingAdmin.getAdminId());
			newSession.setSessionStartTime(LocalDateTime.now());
			UUID uuid = UUID.randomUUID();
			String token = uuid.toString().split("-")[0];

			newSession.setUuid(token);

			return adminSessionDao.save(newSession);
		} else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	}



}
