package com.HydroPonics.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CustomerSession {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sessionId;
	private Integer customerId;
	private String uuid;
	private LocalDateTime sessionStartTime;
}
