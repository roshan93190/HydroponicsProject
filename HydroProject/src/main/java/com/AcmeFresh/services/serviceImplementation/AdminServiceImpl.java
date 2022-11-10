package com.AcmeFresh.services.serviceImplementation;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.DTO.RegisteringDTO;
import com.AcmeFresh.exceptions.LoginException;
import com.AcmeFresh.exceptions.UsernameNotFoundException;
import com.AcmeFresh.modelEntity.AcmeFreshProduce;
import com.AcmeFresh.modelEntity.Admin;
import com.AcmeFresh.modelEntity.AdminSession;
import com.AcmeFresh.repository.AcmeFreshProduceDao;
import com.AcmeFresh.repository.AdminDao;
import com.AcmeFresh.repository.AdminSessionDAO;
import com.AcmeFresh.services.serviceInterfaces.AdminService;


@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;

	@Autowired
	private AdminSessionDAO adminSessionDao;
	
	@Autowired
	private AcmeFreshProduceDao productDao;

	@Override
	public Admin adminRegister(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.save(admin);
	}

	@Override
	public Admin updatePassword(LoginDTO dto, String username, String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		Admin updated = null;
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		Optional<Admin> opt = adminDao.findByUsername(username);
		if (opt.isEmpty())
			throw new UsernameNotFoundException("Username not found");
		else {
			Admin toUpdate = opt.get();
			toUpdate.setPassword(dto.getPassword());
			updated = adminDao.save(toUpdate);
		}
		return updated;
	}

	@Override
	public Admin update(Admin admin, String Username, String key) {
		Admin updated = null;
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);

		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		else {
			Optional<Admin> opt = adminDao.findByUsername(Username);
			if (opt.isEmpty())
				throw new UsernameNotFoundException("Username not found. Please provide proper username");
			else {
				Admin toUpdate = opt.get();
				updated = adminDao.save(toUpdate);
			}
		}
		return updated;

	}

	@Override
	public String deleteByUsername(LoginDTO dto, String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		else {
			Optional<Admin> opt = adminDao.findByUsername(dto.getUsername());
			if (opt.isEmpty())
				throw new UsernameNotFoundException("Username not found");
			else {
				Admin toBeDelete = opt.get();
				adminDao.delete(toBeDelete);
			}
		}
		return "Your Id with Username " + dto.getUsername() + " is Deleted.";
	}

	@Override
	public String logoutAdmin(String key) throws LoginException {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");

		adminSessionDao.delete(otp.get());
		return "Admin has succefully logged out.";
	}

	@Override
	public String addInhouseProducts(AcmeFreshProduce produce, String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		
		productDao.save(produce);
		return "Product has been added..";

	}
	
	public String updatePriceOfProductById(Integer id, Double newPrice, String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		
		Optional<AcmeFreshProduce> opt=productDao.findById(id);
		if(opt.isEmpty()) throw new LoginException("Product not found with this Id..");
		
		AcmeFreshProduce old =opt.get();
		old.setPrice(newPrice);
		productDao.save(old);
		return "Price have been updated..";
		
	}
	
	public String updateProduct(AcmeFreshProduce produce, String key) {
		Optional<AdminSession> otp = adminSessionDao.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		
		Optional<AcmeFreshProduce> opt=productDao.findById(produce.getProductId());
		if(opt.isEmpty()) throw new LoginException("Product not found with this Id..");
		
		
		productDao.save(produce);
		return "Product have been updated..";
	}

}
