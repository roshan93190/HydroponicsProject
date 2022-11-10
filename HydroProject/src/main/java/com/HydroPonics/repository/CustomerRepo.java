package com.HydroPonics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HydroPonics.entity.Customers;

public interface CustomerRepo extends JpaRepository<Customers, Integer> {

	public Optional<Customers> findByUsername(String username) ;
	
	public Optional<Customers> findByMobile(String username) ;
	
}
