package com.HydroPonics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HydroPonics.entity.ClientSession;

public interface ClientSessionRepo extends JpaRepository<ClientSession, Integer> {

public Optional<ClientSession> findByClientId(Integer userId) ;
	
	public Optional<ClientSession> findByUuid(String  uuid);
	
	 
	
}
