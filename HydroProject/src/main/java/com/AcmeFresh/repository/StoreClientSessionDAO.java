package com.AcmeFresh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AcmeFresh.modelEntity.StoreClientSession;

public interface StoreClientSessionDAO extends JpaRepository<StoreClientSession, Integer>{

	public Optional<StoreClientSession> findByClientId(Integer userId) ;
	
	public Optional<StoreClientSession> findByUuid(String  uuid);
	
}
