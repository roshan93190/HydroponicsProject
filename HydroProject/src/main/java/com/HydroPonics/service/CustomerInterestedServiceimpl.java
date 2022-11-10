package com.HydroPonics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HydroPonics.entity.CustomerInterestedService;
import com.HydroPonics.repository.CustomerInterestedRepo;

@Service
public class CustomerInterestedServiceimpl implements CustomerInterestedServices{
	
	@Autowired
	private CustomerInterestedRepo customerInterestedRepo;

	@Override
	public CustomerInterestedService savingRequest(CustomerInterestedService newRequest) {
		return customerInterestedRepo.save(newRequest);
	}

	
	
	
}
