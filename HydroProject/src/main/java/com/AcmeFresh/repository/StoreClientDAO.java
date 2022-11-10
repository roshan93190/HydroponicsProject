package com.AcmeFresh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AcmeFresh.modelEntity.StoreClient;

public interface StoreClientDAO extends JpaRepository<StoreClient, Integer>{
	
public Optional<StoreClient> findByUsername(String username) ;
	
	public Optional<StoreClient> findByMobile(String username) ;

}
