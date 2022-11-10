package com.AcmeFresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AcmeFresh.modelEntity.CustomerInterestedInInfrastructure;

@Repository
public interface CustomerInterestedInInfrastructureDAO extends JpaRepository<CustomerInterestedInInfrastructure, Integer>{

}
