package com.homefun;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	@Bean
	public Queue helloQueue() {
		/**
		 * true 表示持久队列，服务器重启是存活
		 */
		return new Queue("fuck", true);
	}
}
