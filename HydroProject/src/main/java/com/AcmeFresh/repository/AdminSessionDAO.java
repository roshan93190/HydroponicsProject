package com.AcmeFresh.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.AcmeFresh.modelEntity.AdminSession;

public interface AdminSessionDAO extends JpaRepository<AdminSession, Integer>{
	
	public Optional<AdminSession> findByAdminId(Integer userId) ;
	
	public Optional<AdminSession> findByUuid(String  uuid);

}
