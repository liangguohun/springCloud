package com.homefun.comsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * todo   	消费者端代码
 * author 	liangguohun
 * datetime 2017年12月25日 上午2:53:26
 */
@EnableBinding(Customer.class)
public class KafkaReceiver {
	private final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);
	@StreamListener(Customer.INPUT_1)
	private void receive(String vote) {
		logger.info("receive message : " + vote);
	}
}
