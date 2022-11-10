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
public class StoreClientSession {
	
	@Id
	@SequenceGenerator(name="client_Session_generator", sequenceName = "client_Session_seq", allocationSize=50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_Session_generator")
	private Integer sessionId;
	private Integer clientId;
	private String uuid;
	private LocalDateTime sessionStartTime;
	
}
