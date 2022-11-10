package com.HydroPonics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.HydroPonics.DTO.LoginDTO;
import com.HydroPonics.entity.AcmeProduction;
import com.HydroPonics.entity.Client;
import com.HydroPonics.entity.ClientSession;
import com.HydroPonics.exception.LoginException;
import com.HydroPonics.exception.UsernameNotFoundException;
import com.HydroPonics.repository.ClientRepo;
import com.HydroPonics.repository.ClientSessionRepo;

public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private ClientSessionRepo sessionRepo;
	
	@Autowired
	private AcmeProduction productRepo;
	
	@Override
	public Client update(Client update, String Username, String key) {
		Client updated = null;
		Optional<ClientSessionRepo> otp = sessionRepo.findByUuid(key);

		if (otp.isEmpty())
			throw new LoginException("User is not logged in, Please login first!");
		else {
			Optional<Client> opt = clientRepo.findByUsername(Username);
			if (opt.isEmpty())
				throw new UsernameNotFoundException("Username not found. Please provide proper username");
			else {
				updated = clientRepo.save(update);
			}
		}
		return updated;	}

	@Override
	public String deleteByUsername(LoginDTO dto, String key) {
		String otp = sessionRepo.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("User is not logged in, Please login first!");
		else {
			Optional<Client> opt = clientRepo.findByUsername(dto.getUsername());
			if (opt.isEmpty())
				throw new UsernameNotFoundException("Username not found");
			else {
				Client toBeDelete = opt.get();
				clientRepo.delete(toBeDelete);
			}
		}
		return "Your Id with Username " + dto.getUsername() + " is Deleted.";
}

	@Override
	public String logoutClient(String key) {
		Optional<ClientSession> otp = sessionRepo.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("User is not logged in, Please login first!");

	sessionRepo.delete(otp.get());
		return "You have been succefully logged out.";

	}

	@Override
	public Client storeClientRegister(Client newuser) {
		// TODO Auto-generated method stub
		return clientRepo.save(newuser);
	}

	@Override
	public String addInhouseProducts(AcmeProduction produce, String key) {
		Optional<ClientSession> otp = Optional.empty();

		if (otp.isEmpty())
			throw new LoginException("User is not logged in, Please login first!");
		
		productRepo.save(produce);
		return "Product has been added..";

	}

	@Override
	public String updateProduct(AcmeProduction produce, String key) {
		Optional<ClientSession> otp = sessionDao.findByUuid(key);

		if (otp.isEmpty())
			throw new LoginException("User is not logged in, Please login first!");
		
		Optional<AcmeProduction> opt=productRepo.f1indById(produce.getProductId());
		if(opt.isEmpty()) throw new LoginException("Product not found with this Id..");
		
		
		productRepo.save(produce);
		return "Product have been updated..";}

	@Override
	public String updatePriceOfProductById(Integer id, Double newPrice, String key) {
	return null;
	}

}
