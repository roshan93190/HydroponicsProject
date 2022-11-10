package com.HydroPonics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HydroPonics.entity.CustomerServices;
import com.HydroPonics.entity.CustomerSession;
import com.HydroPonics.entity.Customers;

public interface CustomerSessionRepo extends JpaRepository<CustomerServices, Integer> {

	public Optional<CustomerSession> findByCustomerId(Integer userId) ;
	
	public Optional<CustomerSession> findByUuid(String  uuid);
}
