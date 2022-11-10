package com.HydroPonics.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {
	
	private String username;
	
	private String mobile;
	
	private String password;

}
