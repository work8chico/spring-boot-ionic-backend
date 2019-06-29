package com.chico.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.chico.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
