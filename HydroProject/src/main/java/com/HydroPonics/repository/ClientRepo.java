package com.HydroPonics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HydroPonics.entity.Client;


public interface ClientRepo extends JpaRepository<Client, Integer>{

public Optional<Client> findByUsername(String username) ;
	
	public Optional<Client> findByMobile(String username) ;


}
