package com.revature.dkwte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.dkwte.utility.ValidateUtil;

@Configuration
public class Config {

	@Bean
	public ValidateUtil valideateUtil() {
		return new ValidateUtil();
	}
}
