package com.example.Webapp.Authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncryption {

	@Bean
	public BCryptPasswordEncoder passwordEncode()
	{
		return new BCryptPasswordEncoder();
	}
	
	
}
