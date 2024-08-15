package org.therapazes.luisaoproject.config.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
//Formato da resposta enviada pra o controller AuthenticationController
@Data
@Builder
@AllArgsConstructor
public class AuthenticationRequest {
	
	private String email;
	private String password;
}