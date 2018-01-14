package com.homefun.productor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;

/**
 * todo   	生产者端代码
 * author 	liangguohun
 * datetime 2017年12月25日 上午2:35:59
 */
@EnableBinding(Productor.class)
public class KafkaSender {
	 private final Logger logger = LoggerFactory.getLogger(KafkaSender.class);
	 @Autowired
	 private Productor productor;
	 
	 public void sendMessage(String message) {
		try {
			productor.output1().send(MessageBuilder.withPayload("message:"+message).build());
		} catch (Exception e) {
			logger.info(String.format("发送消息[%s]失败！", message));
			logger.info("消息发送失败，原因:"+e);
			e.printStackTrace();
		}
	 }
	 
}
