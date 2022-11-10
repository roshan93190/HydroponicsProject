package com.HydroPonics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HydroPonics.entity.AdminSession;

public interface AdminSessionRepo extends JpaRepository<AdminSession, Integer> {

	public Optional<AdminSession> findByAdminId(Integer userId) ;
	
	public Optional<AdminSession> findByUuid(String  uuid);
	
}
