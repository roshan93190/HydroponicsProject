package com.HydroPonics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class CustomerInterestedService {
	
	@Id
	@SequenceGenerator(name="userSession_generator", sequenceName = "userSession_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSession_generator")
	Integer id;
	
	@NotNull(message = "Please enter the emaild id")
	@Column(unique = true)
	@Email
	String email;
	
	String name;
	
	@NotNull(message = "Please enter the mobile Number")
	@Column(unique = true)
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	String mobileNo;
	
	
	CustomerServices subject;

}
