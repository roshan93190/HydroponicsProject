package com.AcmeFresh.services.serviceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.exceptions.LoginException;
import com.AcmeFresh.modelEntity.AcmeFreshProduce;
import com.AcmeFresh.modelEntity.StoreClient;
import com.AcmeFresh.modelEntity.StoreClientSession;
import com.AcmeFresh.repository.AcmeFreshProduceDao;
import com.AcmeFresh.repository.StoreClientDAO;
import com.AcmeFresh.repository.StoreClientSessionDAO;
import com.AcmeFresh.services.serviceInterfaces.StoreClientService;
@Service
public class StoreClientServiceImpl implements StoreClientService{
	
	@Autowired
	private StoreClientDAO clientDao;
	
	@Autowired
	private StoreClientSessionDAO sessionDao;
	
	@Autowired
	private AcmeFreshProduceDao productDao;

	@Override
	public StoreClient update(StoreClient update, String Username, String key) {
		StoreClient updated = null;
		Optional<StoreClientSession> otp = sessionDao.findByUuid(key);

		if (otp.isEmpty())
			throw new com.AcmeFresh.exceptions.LoginException("User is not logged in, Please login first!");
		else {
			Optional<StoreClient> opt = clientDao.findByUsername(Username);
			if (opt.isEmpty())
				throw new com.AcmeFresh.exceptions.UsernameNotFoundException("Username not found. Please provide proper username");
			else {
				updated = clientDao.save(update);
			}
		}
		return updated;
	}

	@Override
	public String deleteByUsername(LoginDTO dto, String key) {
		Optional<StoreClientSession> otp = sessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new com.AcmeFresh.exceptions.LoginException("User is not logged in, Please login first!");
		else {
			Optional<StoreClient> opt = clientDao.findByUsername(dto.getUsername());
			if (opt.isEmpty())
				throw new com.AcmeFresh.exceptions.UsernameNotFoundException("Username not found");
			else {
				StoreClient toBeDelete = opt.get();
				clientDao.delete(toBeDelete);
			}
		}
		return "Your Id with Username " + dto.getUsername() + " is Deleted.";
	}

	@Override
	public String logoutClient(String key) {
		Optional<StoreClientSession> otp = sessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new com.AcmeFresh.exceptions.LoginException("User is not logged in, Please login first!");

	sessionDao.delete(otp.get());
		return "You have been succefully logged out.";
	}

	@Override
	public StoreClient storeClientRegister(StoreClient newuser) {
		// TODO Auto-generated method stub
		return clientDao.save(newuser);
	}
	
	@Override
	public String addInhouseProducts(AcmeFreshProduce produce, String key) {
		Optional<StoreClientSession> otp = sessionDao.findByUuid(key);

		if (otp.isEmpty())
			throw new com.AcmeFresh.exceptions.LoginException("User is not logged in, Please login first!");
		
		productDao.save(produce);
		return "Product has been added..";

	}
	
	public String updatePriceOfProductById(Integer id, Double newPrice, String key) {
		Optional<StoreClientSession> otp = sessionDao.findByUuid(key);

		if (otp.isEmpty())
			throw new com.AcmeFresh.exceptions.LoginException("User is not logged in, Please login first!");
		
		Optional<AcmeFreshProduce> opt=productDao.findById(id);
		if(opt.isEmpty()) throw new LoginException("Product not found with this Id..");
		
		AcmeFreshProduce old =opt.get();
		old.setPrice(newPrice);
		productDao.save(old);
		return "Price have been updated..";
		
	}
	
	public String updateProduct(AcmeFreshProduce produce, String key) {
		Optional<StoreClientSession> otp = sessionDao.findByUuid(key);

		if (otp.isEmpty())
			throw new com.AcmeFresh.exceptions.LoginException("User is not logged in, Please login first!");
		
		Optional<AcmeFreshProduce> opt=productDao.findById(produce.getProductId());
		if(opt.isEmpty()) throw new LoginException("Product not found with this Id..");
		
		
		productDao.save(produce);
		return "Product have been updated..";
	}

}
