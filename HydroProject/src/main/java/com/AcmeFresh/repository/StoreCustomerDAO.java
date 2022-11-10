package com.AcmeFresh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AcmeFresh.modelEntity.StoreCustomers;
@Repository
public interface StoreCustomerDAO extends JpaRepository<StoreCustomers, Integer>{
	
	public Optional<StoreCustomers> findByUsername(String username) ;
	
	public Optional<StoreCustomers> findByMobile(String username) ;

}
