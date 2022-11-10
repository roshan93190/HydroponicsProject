package com.HydroPonics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HydroPonics.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

	public Optional<Admin> findByUsername(String username) ;
	
	public Optional<Admin> findByMobile(String username) ;

}
