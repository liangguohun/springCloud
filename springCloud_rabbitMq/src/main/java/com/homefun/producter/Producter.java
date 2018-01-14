package com.homefun.producter;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producter {
	@Autowired
	private AmqpTemplate rabbitTemplate;
	public void send(String msg) {
		String context = "fuck " + new Date() + msg; 
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("fuck", context);
	}
}
