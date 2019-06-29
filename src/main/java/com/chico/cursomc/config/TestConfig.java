package com.chico.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.chico.cursomc.services.DBService;
import com.chico.cursomc.services.EmailService;
import com.chico.cursomc.services.MockMailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public Boolean instantiateTest() throws ParseException {
		dbService.instantiateTestDataBase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockMailService();
	}

}
