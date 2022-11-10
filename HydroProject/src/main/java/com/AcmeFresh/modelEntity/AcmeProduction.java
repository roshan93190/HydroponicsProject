package com.AcmeFresh.modelEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class AcmeProduction {
	
	@Id
	Integer productId;
	
	@NotNull
	String productName;
	
	@NotNull
	String producerName;
	
	@NotNull
	Integer quantity_in_kgs;
	
	@NotNull
	Double Price;
	
	

}
