package com.AcmeFresh.services.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AcmeFresh.modelEntity.CustomerInterestedInInfrastructure;
import com.AcmeFresh.repository.CustomerInterestedInInfrastructureDAO;
import com.AcmeFresh.services.serviceInterfaces.CustomerInterestedInInfrastructureService;
@Service
public class CustomerInterestedInInfrastructureServiceImpl implements CustomerInterestedInInfrastructureService{
	@Autowired
	private CustomerInterestedInInfrastructureDAO customerInterestedInInfrastructureDAO;
	
	@Override
	public CustomerInterestedInInfrastructure savingRequest(CustomerInterestedInInfrastructure newRequest) {
		return customerInterestedInInfrastructureDAO.save(newRequest);
	}

}
