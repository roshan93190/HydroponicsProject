
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HydroPonics.DTO.LoginDTO;
import com.HydroPonics.entity.AcmeProduction;
import com.HydroPonics.entity.Admin;
import com.HydroPonics.entity.AdminSession;
import com.HydroPonics.exception.LoginException;
import com.HydroPonics.exception.UsernameNotFoundException;
import com.HydroPonics.repository.AcmeProductionRepo;
import com.HydroPonics.repository.AdminRepo;
import com.HydroPonics.repository.AdminSessionRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private AdminSessionRepo adminSessionRepo;
	
	@Autowired
	private AcmeProductionRepo productRepo;

	@Override
	public Admin registerAdmin(Admin admin) {
		
		Admin newUser = adminRepo.save(admin);
		
		return newUser;
	}

	@Override
	public Admin updatePassword(LoginDTO dto, String username, String key) {
	
		Optional<AdminSession> otp = adminSessionRepo.findByUuid(key);
		Admin updated = null;
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		Optional<Admin> opt = adminRepo.findByUsername(username);
		if (opt.isEmpty())
			throw new UsernameNotFoundException("Username not found");
		else {
			Admin toUpdate = opt.get();
			toUpdate.setPassword(dto.getPassword());
			updated = adminRepo.save(toUpdate);
		}
		return updated;
	}

	@Override
	public Admin updateAdmin(Admin admin, String Username, String key) {
		Admin updated = null;
		Optional<AdminSession> otp = adminSessionRepo.findByUuid(key);

		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		else {
			Optional<Admin> opt = adminRepo.findByUsername(Username);
			if (opt.isEmpty())
				throw new UsernameNotFoundException("Username not found. Please provide proper username");
			else {
				Admin toUpdate = opt.get();
				updated = adminRepo.save(toUpdate);
			}
		}
		return updated;
	}

	@Override
	public String deleteByUsername(LoginDTO dto, String key) {
		Optional<AdminSession> otp = adminSessionRepo.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		else {
			Optional<Admin> opt = adminRepo.findByUsername(dto.getUsername());
			if (opt.isEmpty())
				throw new UsernameNotFoundException("Username not found");
			else {
				Admin toBeDelete = opt.get();
				adminRepo.delete(toBeDelete);
			}
		}
		return "Your Id with Username " + dto.getUsername() + " is Deleted.";
	}

	@Override
	public String logoutAdmin(String key) throws LoginException {

		Optional<AdminSession> otp = adminSessionRepo.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");

		adminSessionRepo.delete(otp.get());
		return "Admin has succefully logged out.";
	}

	@Override
	public String addInhouseProducts(AcmeProduction produce, String key) {
		Optional<AdminSession> otp = adminSessionRepo.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		
		productRepo.save(produce);
		return "Product has been added..";
	}

	@Override
	public String updateProduct(AcmeProduction produce, String key) {
	}

	@Override
	public String updatePriceOfProductById(Integer id, Double newPrice, String key) {
		Optional<AdminSession> otp = adminSessionRepo.findByUuid(key);
		if (otp.isEmpty())
			throw new LoginException("Admin is not logged in, Please login first!");
		
		Optional<AcmeProduction> opt = productRepo.findById(id);
		if(opt.isEmpty()) throw new LoginException("Product not found with this Id..");
		
		AcmeProduction old =opt.get();
		old.setPrice(newPrice);
		productRepo.save(old);
		return "Price have been updated..";

	}

	
	 

	
}
