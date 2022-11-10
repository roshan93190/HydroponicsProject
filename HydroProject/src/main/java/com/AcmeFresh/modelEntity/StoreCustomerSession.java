package com.AcmeFresh.modelEntity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
@Data
@Entity
public class StoreCustomerSession {
	
	@Id
	@SequenceGenerator(name="customerSession_generator", sequenceName = "customerSession_seq", allocationSize=50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSession_generator")
	private Integer sessionId;
	private Integer customerId;
	private String uuid;
	private LocalDateTime sessionStartTime;
}
