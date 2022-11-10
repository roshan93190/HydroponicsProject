package com.AcmeFresh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AcmeFresh.modelEntity.StoreCustomerSession;
@Repository
public interface StoreCustomerSessionDAO extends JpaRepository<StoreCustomerSession, Integer>{
	
	public Optional<StoreCustomerSession> findByCustomerId(Integer userId) ;
	
	public Optional<StoreCustomerSession> findByUuid(String  uuid);

}
